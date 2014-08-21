package by.skakun.carrentalsystem.command.user;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.connectionpool.ConnectionPool;
import by.skakun.carrentalsystem.dao.CarDao;
import by.skakun.carrentalsystem.dao.impl.CarDaoImpl;
import by.skakun.carrentalsystem.entity.Car;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.manager.ConfigurationManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 * 
 * @author Skakun
 * 
 * processing request to get to cars.jsp with list of all available to user cars
 */
public class CarsRedirectCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(CarsRedirectCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        CarDaoImpl carDao;
        try {
            carDao = new CarDaoImpl();
        } catch (DAOException ex) {
            LOG.fatal("Couldn't establish the connection to the database", ex);
            LOG.info("->errorpage");
            String page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        List<Car> cars = null;
        try {
            cars = carDao.getAllForUser();
        } catch (DAOException ex) {
            LOG.info("DAOException while carDao.getAll()." + ex);
        }
        request.setAttribute("lst", cars);
        LOG.info("->cars");
        String page = ConfigurationManager.getProperty("path.page.cars");
        return page;

    }

}
