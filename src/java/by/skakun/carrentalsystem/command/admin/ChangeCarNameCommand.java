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
 * @author Skakun processing the request of changing the car name
 */
public class ChangeCarNameCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ChangeCarNameCommand.class);

    /**
     *
     * @param request
     * @return page CarInfo.js after the attempt of changing car name has been
     * made
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        boolean flag;
        LOG.info("ChangeCarNameCommand");
        String carid = (String) request.getParameter("carid");
        int id = Integer.parseInt(carid);
        String carname = (String) request.getParameter("newcarname");
        CarDaoImpl carDao = new CarDaoImpl(ConnectionPool.getConnection());
        try {
            flag = carDao.changeCarname(carname, id);

            if (flag) {
                request.setAttribute("success", "1");
                page = ConfigurationManager.getProperty("path.page.carchange");
                return page;
            } else {
                request.setAttribute("fail", "1");
                page = ConfigurationManager.getProperty("path.page.carchange");
                return page;
            }
        } catch (DAOException ex) {
            LOG.info("DAOException while ChangeCarNameCommand", ex);
            page = ConfigurationManager.getProperty("path.page.carchange");
            return page;
        }

    }

}
