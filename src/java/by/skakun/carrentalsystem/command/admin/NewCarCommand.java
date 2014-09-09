package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.CarDao;
import by.skakun.carrentalsystem.dao.DaoFactory;
import by.skakun.carrentalsystem.dao.DaoType;
import by.skakun.carrentalsystem.dao.impl.CarDaoImpl;
import by.skakun.carrentalsystem.entity.Car;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun returns page after trying to add new car to the database
 */
public class NewCarCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(NewCarCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        CarDao carDao;
        carDao = (CarDao) DaoFactory.getDao(DaoType.CAR);
        boolean flag;
        try {
            String carname = (String) request.getParameter("carname");
            int price = Integer.parseInt((String) request.getParameter("price"));
            String carimage = (String) request.getParameter("image");
            carimage = "img/car/".concat(carimage).concat(".jpg");
            Car car = new Car(carname, price, carimage, 1);
            flag = carDao.create(car);
        } catch (DAOException ex) {
            LOG.error("DAOException while CarDao.create()" + ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        if (flag) {
            request.setAttribute("csuccess", "1");
        } else {
            request.setAttribute("cfail", "1");
        }
        page = ConfigurationManager.getProperty("path.page.addnewcar");
        return page;

    }

}
