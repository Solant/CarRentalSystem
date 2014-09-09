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
 * showing user the list of denied orders (with reasons, why)
 */
public class DeniedCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(DeniedCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        OrderDao orderDao;
        List<Order> orderDenied;
        try {
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            int id = (int) request.getSession().getAttribute("userId");
            orderDenied = orderDao.getDByUserId(id);
            request.setAttribute("lstR", orderDenied);
            page = ConfigurationManager.getProperty("path.page.ordersdenied");
        } catch (DAOException ex) {
            LOG.error("DAOException while getDByUserId(id)" + ex);
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}
