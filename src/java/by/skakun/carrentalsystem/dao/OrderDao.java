package by.skakun.carrentalsystem.dao;

import by.skakun.carrentalsystem.entity.Order;
import by.skakun.carrentalsystem.exception.DAOException;
import java.util.List;

/**
 *
 * @author Skakun
 * 
 */
public interface OrderDao extends IDao {

    
    void create(Order order) throws DAOException;

    @Override
    List<Order> getAll() throws DAOException;

    /**
     *
     * @param id
     * @return
     * @throws DAOException
     */
    List<Order> getUByUserId(int id) throws DAOException;

    /**
     *
     * @param id
     * @return
     * @throws DAOException
     */
    boolean confirm(int id) throws DAOException;

    /**
     *
     * @param id
     * @throws DAOException
     */
    void returnCar(int id) throws DAOException;

    /**
     *
     * @param id
     * @param reason
     * @return
     * @throws DAOException
     */
    boolean deny(int id, String reason) throws DAOException;

    /**
     *
     * @param id
     * @param idOr
     * @param sum
     * @return
     * @throws DAOException
     */
    boolean pay(int id, int idOr, int sum) throws DAOException;

    /**
     *
     * @param id
     * @return
     * @throws DAOException
     */
    boolean delete(int id) throws DAOException;

    /**
     *
     * @return @throws DAOException
     */
    List<Order> getNewOrders() throws DAOException;

    /**
     *
     * @return @throws DAOException
     */
    List<Order> getPaidOrders() throws DAOException;

    /**
     *
     * @return @throws DAOException
     */
    List<Order> getUnPaidOrders() throws DAOException;

    /**
     *
     * @return @throws DAOException
     */
    List<Order> getArchiveOrders() throws DAOException;

    /**
     *
     * @param id
     * @return
     * @throws DAOException
     */
    List<Order> getDByUserId(int id) throws DAOException;

    /**
     *
     * @param id
     * @return
     * @throws DAOException
     */
    List<Order> getAByUserId(int id) throws DAOException;

    /**
     *
     * @param id
     * @param damage
     * @param damageCost
     * @throws DAOException
     */
    void returnDamage(int id, String damage, int damageCost) throws DAOException;

}
