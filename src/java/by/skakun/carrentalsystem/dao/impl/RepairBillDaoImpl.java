package by.skakun.carrentalsystem.dao.impl;

import by.skakun.carrentalsystem.connectionpool.ConnectionPool;
import by.skakun.carrentalsystem.dao.RepairBillDao;
import by.skakun.carrentalsystem.entity.Entity;
import by.skakun.carrentalsystem.entity.RepairBill;
import by.skakun.carrentalsystem.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun 
 * DAO implementation for RepairBillDao Interface
 */
public class RepairBillDaoImpl implements RepairBillDao {

    private Connection connection;
    private static final Logger LOG = Logger.getLogger(RepairBillDaoImpl.class);
    private static final String GET_ALL = "SELECT damagebill.`bill_id`, damagebill.`damage`, "
            + "damagebill.`sum`, car.`carname` FROM damagebill LEFT JOIN "
            + "`orderc` ON `damagebill`.`order_id`=orderc.`order_id` "
            + "LEFT JOIN car ON orderc.`car_id`=car.`car_id` WHERE damagebill.`repaired`=0;";
    private static final String REPAIR = "UPDATE DAMAGEBILL SET DAMAGEBILL.`repaired`=1"
            + " where DAMAGEBILL.`bill_id`=?;";

    /**
     *
     * @param connection from ConnectionPool
     */
    public RepairBillDaoImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     *
     * @return list of all repair bills
     * @throws DAOException
     */
    @Override
    public List getAll() throws DAOException {
        LOG.info("RepairBillDaoImpl.getAll()");
        try (Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(GET_ALL);
            List<RepairBill> list = new ArrayList<>();
            while (rs.next()) {
                RepairBill bill = new RepairBill();
                bill.setCarname(rs.getString("carname"));
                bill.setSum(rs.getInt("sum"));
                bill.setDamage(rs.getString("damage"));
                bill.setId(rs.getInt("bill_id"));
                list.add(bill);
            }
            return list;
        } catch (SQLException ex) {
            throw new DAOException("DAOException while RepairBillDaoImpl.getAll()", ex);
        } finally {
            ConnectionPool.returnConnection(connection);

        }
    }

    /**
     *
     * @param id of the repair bill
     * @return true if operation was successful, otherwise
     * @throws DAOException
     */
    @Override
    public boolean repair(int id) throws DAOException {
        LOG.info("RepairBillDaoImpl.repair()");
        try {
            PreparedStatement stm = connection.prepareStatement(REPAIR);
            stm.setInt(1, id);
            stm.executeUpdate();
            ConnectionPool.returnConnection(connection);
            return true;
        } catch (SQLException ex) {
            ConnectionPool.returnConnection(connection);
            throw new DAOException("DAOException while RepairBillDaoImpl.repair()", ex);
        }
    }

    /**
     * not yet implemented
     *
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public Entity read(int id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
