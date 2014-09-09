package by.skakun.carrentalsystem.dao;

import by.skakun.carrentalsystem.dao.impl.CarDaoImpl;
import by.skakun.carrentalsystem.dao.impl.ClientDaoImpl;
import by.skakun.carrentalsystem.dao.impl.OrderDaoImpl;
import by.skakun.carrentalsystem.dao.impl.RepairBillDaoImpl;
import by.skakun.carrentalsystem.exception.DAOException;


/**
 *
 * @author apple
 */
public class DaoFactory {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(DaoFactory.class);

    public static IDao getDao(DaoType daoType) {
        if (daoType.equals(DaoType.ORDER)) {
            OrderDao applDao = null;
            try {
                applDao = new OrderDaoImpl();
            } catch (DAOException ex) {
                LOG.fatal("Couldn't establish the connection to the database" + ex);
            }
            return applDao;
        } else if (daoType.equals(DaoType.CLIENT)) {
            ClientDao clientDao = null;
            try {
                clientDao = new ClientDaoImpl();
            } catch (DAOException ex) {
                LOG.fatal("Couldn't establish the connection to the database" + ex);
            }
            return clientDao;
        }
        else if (daoType.equals(DaoType.CAR)) {
            CarDao carDao = null;
            try {
                carDao = new CarDaoImpl();
            } catch (DAOException ex) {
                LOG.fatal("Couldn't establish the connection to the database" + ex);
            }
            return carDao;
        }
        else if (daoType.equals(DaoType.REPAIR_BILL)) {
            RepairBillDao billDao = null;
            try {
                billDao = new RepairBillDaoImpl();
            } catch (DAOException ex) {
                LOG.fatal("Couldn't establish the connection to the database" + ex);
            }
            return billDao;
        } else {
            return null;
        }

    }

}
