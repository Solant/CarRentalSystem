package by.skakun.carrentalsystem.command.user;

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
 * deleting not wanted order
 */
public class DeleteOrderCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(DeleteOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        List<Order> orders;

        String orderIdS = (String) request.getParameter("applid");
        int orderId = Integer.parseInt(orderIdS);
        OrderDao orderDao;

        try {
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            orderDao.delete(orderId);
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            int id = (int) request.getSession().getAttribute("userId");
            orders = orderDao.getUByUserId(id);
            request.setAttribute("lst", orders);
            page = ConfigurationManager.getProperty("path.page.basket");
        } catch (DAOException ex) {
            LOG.error("DAOException while PayCommand: " + ex);
            page = ConfigurationManager.getProperty("path.page.error");
        }
        
        return page;
    }
}
