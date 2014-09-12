package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.CarDao;
import by.skakun.carrentalsystem.dao.DaoFactory;
import by.skakun.carrentalsystem.dao.DaoType;
import by.skakun.carrentalsystem.dao.impl.CarDaoImpl;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import by.skakun.carrentalsystem.util.EnteredInfoValidator;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun changes the image of car and returns him to the car info page
 */
public class ChangeCarImageCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ChangeCarImageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        boolean flag;
        String carid = (String) request.getParameter("carid");
        int id = Integer.parseInt(carid);
        String carimage = (String) request.getParameter("newimage");
        if (EnteredInfoValidator.dataLength(carimage)) { //checking entered info
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        carimage = "img/car/".concat(carimage).concat(".jpg"); //created an acceptable for database image address

        CarDao carDao;
        carDao = (CarDao) DaoFactory.getDao(DaoType.CAR);
        try {
            flag = carDao.changeCarimage(carimage, id);
            if (flag) {
                request.setAttribute("isuccess", "1");
                request.getSession().setAttribute("carimage", carimage);
            } else {
                request.setAttribute("ifail", "1");
            }
        } catch (DAOException ex) {
            LOG.error("DAOException while ChangeCarImageCommand", ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        page = ConfigurationManager.getProperty("path.page.carchange");
        return page;

    }

}
