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
 * marking the order as returned without any damage
 */
public class ReturnCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ReturnCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        OrderDao orderDao;
        String order = (String) request.getParameter("applId");
        int orderId = Integer.parseInt(order);
        List<Order> orders;
        try {
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            orderDao.returnCar(orderId);
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            orders = orderDao.getPaidOrders();
            request.setAttribute("lst", orders);
            page = ConfigurationManager.getProperty("path.page.paidorders");
        } catch (DAOException ex) {
            LOG.error("DaoException while ReturnCommand: " + ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        LOG.debug("->paidorders.jsp");
        return page;

    }

}
