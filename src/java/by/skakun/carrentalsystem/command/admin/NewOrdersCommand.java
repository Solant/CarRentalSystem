package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.impl.OrderDaoImpl;
import by.skakun.carrentalsystem.entity.Order;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun 
 * getting admin to page with all new applications
 */
public class NewOrdersCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(NewOrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        OrderDaoImpl applDao;
        try {
            applDao = new OrderDaoImpl();
        } catch (DAOException ex) {
            LOG.fatal("Couldn't establish the connection to the database", ex);
            LOG.info("->errorpage");
            String page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        List<Order> appls = null;
        try {
            appls = applDao.getNewOrders();
        } catch (DAOException ex) {
            LOG.error("DAOException after OrderDao.getNewApplications()" + ex);
        }
        request.setAttribute("lst", appls);
        LOG.info("->neworders");
        String page = ConfigurationManager.getProperty("path.page.neworders");
        return page;

    }

}
