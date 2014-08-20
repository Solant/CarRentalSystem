package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.connectionpool.ConnectionPool;
import by.skakun.carrentalsystem.dao.impl.CarDaoImpl;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.manager.ConfigurationManager;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
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
        CarDaoImpl carDao;
        try {
            carDao = new CarDaoImpl();
        } catch (DAOException ex) {
            LOG.fatal("Couldn't establish the connection to the database", ex);
            LOG.info("->errorpage");
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        try {
            flag = carDao.changeActive(active, id);

            if (flag) {
                request.setAttribute("acsuccess", "1");
                request.setAttribute("active", Math.abs(active - 1));
                page = ConfigurationManager.getProperty("path.page.carchange");
                return page;
            } else {
                request.setAttribute("acfail", "1");
                page = ConfigurationManager.getProperty("path.page.carchange");
                return page;
            }
        } catch (DAOException ex) {
            LOG.info("DAOException while ChangeActiveCommand", ex);
            page = ConfigurationManager.getProperty("path.page.carchange");
            return page;
        }

    }

}
