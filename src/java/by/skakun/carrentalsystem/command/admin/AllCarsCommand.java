package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.connectionpool.ConnectionPool;
import by.skakun.carrentalsystem.dao.impl.CarDaoImpl;
import by.skakun.carrentalsystem.entity.Car;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.manager.ConfigurationManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class AllCarsCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(AllCarsCommand.class);

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
            cars = carDao.getAll();
        } catch (DAOException ex) {
            LOG.info("DAOMistake while carDao.getAll()." + ex.getLocalizedMessage());
        }
        request.setAttribute("lst", cars);
        LOG.info("->cars");
        String page = ConfigurationManager.getProperty("path.page.allcars");
        return page;

    }

}

/**
 *
 * @param request
 * @return content for page result/or redirects to login page with error message
 */
/*   @Override
 public String execute(HttpServletRequest request) {
 String page = ConfigurationManager.getProperty("path.page.register");
 return page;

 } */
