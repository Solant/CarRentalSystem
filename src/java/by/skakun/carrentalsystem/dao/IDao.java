package by.skakun.carrentalsystem.dao;

import by.skakun.carrentalsystem.entity.Entity;
import by.skakun.carrentalsystem.exception.DAOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 * @param <T>
 */
public interface IDao<T extends Entity> {

    /**
     * log4j logger
     */
    static final Logger LOG = Logger.getLogger(IDao.class);

    /**
     *
     * @param id
     * @return
     * @throws DAOException
     */
    T read(int id) throws DAOException;

    /**
     *
     * @return @throws DAOException
     */
    List<T> getAll() throws DAOException;

    /**
     *
     * @param stm closes prepared statement
     */
    public static void closePS(PreparedStatement stm) {
        try {
            if (stm != null) {
                stm.close();
            }
        } catch (SQLException ex) {
            LOG.error("SQLException while closing PreparedStatement in OrderDaoImpl", ex);
        }
    }

}
