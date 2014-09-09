package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.CarDao;
import by.skakun.carrentalsystem.dao.DaoFactory;
import by.skakun.carrentalsystem.dao.DaoType;
import by.skakun.carrentalsystem.dao.impl.CarDaoImpl;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 *
 * changes the state of car and returns admin to the car info page
 */
public class ChangeActiveCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ChangeActiveCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        boolean flag;
        String carid = (String) request.getParameter("carid");
        int id = Integer.parseInt(carid);
        String caractive = (String) request.getParameter("active");
        int active = Integer.parseInt(caractive);
        CarDao carDao;
        carDao = (CarDao) DaoFactory.getDao(DaoType.CAR);
        
        try {
            flag = carDao.changeActive(active, id);
            if (flag) {
                request.setAttribute("acsuccess", "1");
                request.setAttribute("active", Math.abs(active - 1));

            } else {
                request.setAttribute("acfail", "1");
            }
        } catch (DAOException ex) {
            LOG.debug("DAOException while ChangeActiveCommand", ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        page = ConfigurationManager.getProperty("path.page.carchange");
        return page;
    }

}
