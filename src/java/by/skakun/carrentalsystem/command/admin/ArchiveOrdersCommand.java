package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.OrderDao;
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
 * sends admin to page with all archived orders
 */
public class ArchiveOrdersCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ArchiveOrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        OrderDao orderDao;
        try {
            orderDao = new OrderDaoImpl();
        } catch (DAOException ex) {
            LOG.fatal("Couldn't establish the connection to the database", ex);
            LOG.info("->errorpage");
            String page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        List<Order> appls = null;
        try {
            appls = orderDao.getArchiveOrders();
        } catch (DAOException ex) {
            LOG.error("DAOException while applicationDaoImpl.getAll()." + ex);
        }
        request.setAttribute("lst", appls);
        LOG.info("-> archiveorders.jsp");
        String page = ConfigurationManager.getProperty("path.page.archiveorders");
        return page;

    }

}