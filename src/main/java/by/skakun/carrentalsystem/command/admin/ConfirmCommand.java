package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.DaoFactory;
import by.skakun.carrentalsystem.dao.DaoType;
import by.skakun.carrentalsystem.dao.OrderDao;
import by.skakun.carrentalsystem.entity.Order;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 *
 * sets an order as confirmed and returns admin to page with other orders
 */
public class ConfirmCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ConfirmCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        boolean flag;
        OrderDao orderDao;
        String orderIdS = (String) request.getParameter("applId");
        int orderId = Integer.parseInt(orderIdS);
        List<Order> orders;
        try {
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            flag = orderDao.confirm(orderId);
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            orders = orderDao.getNewOrders();
        } catch (DAOException ex) {
            LOG.error("DAOException while ConfirmCommand" + ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        if (flag) {
            request.setAttribute("csuccess", "1");
            request.setAttribute("lst", orders);
        }
        LOG.debug("->neworders");
        page = ConfigurationManager.getProperty("path.page.neworders");
        return page;
    }

}
