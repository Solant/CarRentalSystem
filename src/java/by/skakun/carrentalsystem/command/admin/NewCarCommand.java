package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.impl.CarDaoImpl;
import by.skakun.carrentalsystem.entity.Car;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 * returns page after trying to add new car to the database
 */
public class NewCarCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(NewCarCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        CarDaoImpl carDao;
        try {
            carDao = new CarDaoImpl();
        } catch (DAOException ex) {
            LOG.fatal("Couldn't establish the connection to the database", ex);
            LOG.info("->errorpage");
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        boolean flag = false;
        try {
            String carname = (String) request.getParameter("carname");
            int price = Integer.parseInt((String) request.getParameter("price"));
            String carimage = (String) request.getParameter("image");
            carimage = "img/car/".concat(carimage).concat(".jpg");
            Car car = new Car(carname, price, carimage, 1);
            flag = carDao.create(car);
        } catch (DAOException ex) {
            LOG.info("DAOException while CarDao.create()" + ex);
        }
        if (flag) {
            request.setAttribute("csuccess", "1");
            page = ConfigurationManager.getProperty("path.page.addnewcar");
            return page;

        } else {
            request.setAttribute("cfail", "1");
            page = ConfigurationManager.getProperty("path.page.addnewcar");
            return page;
        }

    }

}
