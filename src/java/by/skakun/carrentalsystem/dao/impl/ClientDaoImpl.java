package by.skakun.carrentalsystem.dao.impl;

import by.skakun.carrentalsystem.connectionpool.ConnectionPool;
import by.skakun.carrentalsystem.dao.ClientDao;
import static by.skakun.carrentalsystem.dao.IDao.closePS;
import by.skakun.carrentalsystem.entity.Client;
import by.skakun.carrentalsystem.exception.ClientException;
import by.skakun.carrentalsystem.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun 
 * DAO implementation for ClientDao Interface
 */
public class ClientDaoImpl implements ClientDao {

    private Connection connection;
    private ConnectionPool pool;
    private static final Logger LOG = Logger.getLogger(ClientDaoImpl.class);
    private static final String ADD_NEW_USER = "INSERT INTO CLIENT(USERNAME, "
            + "PASS, SURNAME,NAME, PASSPORT_NUMBER, CLIENT_TYPE, EMAIL) "
            + "VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM CLIENT WHERE ID = ?;";
    private static final String UPDATE_USER = "UPDATE USER SET LOGIN =?, PASSWORD=?,"
            + " NAME=?, SURNAME=?, PASSPORT_NUMBER=? WHERE ID = ?;";
    private static final String CHECK_LOGIN = "SELECT * FROM User where login= ?;";
    private static final String GET_ALL_USERS = "SELECT * FROM CLIENT;";
    private static final String DELETE_USER = "DELETE FROM CLIENT WHERE CLIENT.`user_id`=?;";
    private static final String CHANGE_PASSWORD = "UPDATE `client` SET client.`pass`=? "
            + "where client.`user_id`=?;";

    /**
     *
     * @param connection from ConnectionPool
     */
    public ClientDaoImpl() throws DAOException {
        this.pool = ConnectionPool.getInstance();
        this.connection = pool.getConnection();
    }

    /**
     *
     * @param user - new user entity made from request
     * @throws DAOException
     */
    @Override
    public void create(Client user) throws DAOException {
        LOG.info("ClientDaoImpl.createUser");
        PreparedStatement stm = null;

        try {
            String login = user.getLogin();
            String password = user.getPassword();
            String name = user.getName();
            String surname = user.getSurname();
            String passNum = user.getPassNum();
            String email = user.getEmail();

            stm = connection.prepareStatement(ADD_NEW_USER);

            stm.setString(1, login);
            stm.setString(2, password);
            stm.setString(3, surname);
            stm.setString(4, name);
            stm.setString(5, passNum);
            stm.setString(6, "USER");
            stm.setString(7, email);

            stm.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException("DAOException while ClientDaoImpl.create():", ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);

        }
    }

    /**
     *
     * @param id of the user
     * @return user, otherwise
     * @throws DAOException
     */
    @Override
    public Client read(int id) throws DAOException {
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(SELECT_USER_BY_ID);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            rs.next();
            Client user;
            user = new Client();
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setPassNum(rs.getString("passport_number"));
            user.setType(rs.getString("client_type"));
            user.setId(rs.getInt("user_id"));
            user.setLogin(rs.getString("username"));
            user.setPassword(rs.getString("pass"));
            return user;
        } catch (SQLException | ClientException ex) {
            throw new DAOException("DAOException while ClientDaoImpl.read():", ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
    }

    /**
     *
     * @param user from request
     * @throws DAOException
     */
    @Override
    public void update(Client user) throws DAOException {
        PreparedStatement stm = null;

        try {
            int id = user.getId();
            String login = user.getLogin();
            String password = user.getPassword();
            String name = ((Client) user).getName();
            String surname = ((Client) user).getSurname();
            String passNum = ((Client) user).getPassNum();
            stm = connection.prepareStatement(UPDATE_USER);
            stm.setString(1, login);
            stm.setString(2, password);
            stm.setString(3, name);
            stm.setString(4, surname);
            stm.setString(5, passNum);
            stm.setInt(6, id);

            stm.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DAOException while ClientDaoImpl.update():", e);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
    }

    /**
     *
     * @param login from the request
     * @return boolean true or false
     * @throws by.skakun.carrentalsystem.exception.DAOException
     */
    @Override
    public boolean checkLogin(String login) throws DAOException {
        LOG.info("CLientDaoImpl.checkLogin()");
        PreparedStatement stm = null;
        ResultSet rs;
        try {
            stm = connection.prepareStatement(CHECK_LOGIN);
            stm.setString(1, login);
            rs = stm.executeQuery();
            while (rs.next()) {
                return false;
            }
        } catch (SQLException ex) {
            throw new DAOException("DAOException while UserDaoImpl.checkLogin", ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
        return true;
    }

    /**
     *
     * @return list of all users
     * @throws DAOException
     *
     */
    @Override
    public List<Client> getAll() throws DAOException {
        LOG.info("ClientDaoImpl.getAll()");
        PreparedStatement stm = null;
        try {
            List<Client> list;
            stm = connection.prepareStatement(GET_ALL_USERS);
            ResultSet rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                Client user;
                user = new Client();
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setPassNum(rs.getString("passport_number"));
                user.setLogin(rs.getString("username"));
                user.setPassword(rs.getString("pass"));
                user.setType(rs.getString("client_type"));
                user.setId(rs.getInt("user_id"));
                list.add(user);
            }
            return list;
        } catch (SQLException | ClientException ex) {
            throw new DAOException("ClientException while ClientDaoImpl.getAll():", ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
    }

    /**
     *
     * @param id - id of the user to delete
     * @return boolean if the deleting was successful or not will return false
     * if there is no such user or if the user has applications
     * @throws DAOException
     */
    @Override
    public boolean deleteUser(int id) throws DAOException {
        LOG.info("ClientDaoImpl.deleteUser()");
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(SELECT_USER_BY_ID);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            rs.next();
            if (!rs.first()) {
                return false;
            } else {
                stm = connection.prepareStatement(DELETE_USER);
                stm.setInt(1, id);
                stm.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            throw new DAOException("DAOException while ClientDaoImpl.deleteUser()", ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
    }

    /**
     *
     * @param id of the user
     * @param pass - old password
     * @param newPass - new password
     * @return trie or false depending on whether the operation was successful,
     * otherwise
     * @throws DAOException
     *
     */
    @Override
    public boolean changePassword(int id, String pass, String newPass) throws
            DAOException {
        LOG.info("ClientDaoImpl.changePassword()");
        PreparedStatement stm = null;
        try {

            stm = connection.prepareStatement(SELECT_USER_BY_ID);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            rs.next();
            String password = rs.getString("pass");
            if (password.equals(pass)) {

                stm = connection.prepareStatement(CHANGE_PASSWORD);
                stm.setString(1, newPass);
                stm.setInt(2, id);
                stm.executeUpdate();
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            throw new DAOException("DAOException while ClientDaoImpl.changePassword()", ex);
        } finally {
            closePS(stm);
            pool.returnConnection(connection);
        }
    }

}
