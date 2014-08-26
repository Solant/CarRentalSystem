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
 * getting admin to page with all paid orders
 */
public class PaidOrdersCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(PaidOrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        OrderDao orderDao;
        try {
            orderDao = new OrderDaoImpl();
        } catch (DAOException ex) {
            LOG.fatal("Couldn't establish the connection to the database", ex);
            LOG.debug("->errorpage");
            String page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        List<Order> orders = null;
        try {
            orders = orderDao.getPaidOrders();
        } catch (DAOException ex) {
            LOG.error("DAOException after OrderDao.getPaidApplications()" + ex);
        }
        request.setAttribute("lst", orders);
        LOG.debug("->paidorders");
        String page = ConfigurationManager.getProperty("path.page.paidorders");
        return page;

    }

}