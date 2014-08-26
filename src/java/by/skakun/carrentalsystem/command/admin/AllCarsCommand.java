package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.CarDao;
import by.skakun.carrentalsystem.dao.impl.CarDaoImpl;
import by.skakun.carrentalsystem.entity.Car;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 * returns page with all (active and inactive) cars for admin 
 */
public class AllCarsCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(AllCarsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        CarDao carDao;
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
            LOG.error("DAOException while carDao.getAll()." + ex.getLocalizedMessage());
        }
        request.setAttribute("lst", cars);
        LOG.info("->cars");
        String page = ConfigurationManager.getProperty("path.page.allcars");
        return page;

    }

}