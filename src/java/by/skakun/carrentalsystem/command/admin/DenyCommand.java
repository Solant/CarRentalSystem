package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.impl.OrderDaoImpl;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 *
 * denies user's order
 */
public class DenyCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(DenyCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        Boolean flag = false;
        OrderDaoImpl applDao;
        try {
            applDao = new OrderDaoImpl();
        } catch (DAOException ex) {
            LOG.fatal("Couldn't establish the connection to the database");
            LOG.debug("->errorpage");
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        String appl = (String) request.getParameter("applId");
        String reason = (String) request.getParameter("reasonForRefusal");
        int applId = Integer.parseInt(appl);
        try {
            flag = applDao.deny(applId, reason);
        } catch (DAOException ex) {
            LOG.error("DAOException in denyComand: " + ex);
        }

        if (flag) {
            request.setAttribute("cfail", "1");
            page = new NewOrdersCommand().execute(request);

        } else {
            page = new NewOrdersCommand().execute(request);
        }
        return page;
    }

}
