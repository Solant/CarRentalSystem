package by.skakun.carrentalsystem.dao;

import by.skakun.carrentalsystem.entity.Client;
import by.skakun.carrentalsystem.exception.DAOException;
import java.util.List;

public interface ClientDao extends IDao {

    Client read(int id) throws DAOException;

    @Override
    List<Client> getAll() throws DAOException;

    boolean checkLogin(String login) throws DAOException;

    void create(Client user) throws DAOException;
    
    boolean deleteUser(int id) throws DAOException;
    
    boolean changePassword(int id, String pass, String newPass) throws DAOException;
    
    boolean changeEmail(int id, String newemail) throws DAOException;
    
    boolean changeActive(int id, int active) throws DAOException;
    
    List<Client> getAllUsers() throws DAOException;

}
