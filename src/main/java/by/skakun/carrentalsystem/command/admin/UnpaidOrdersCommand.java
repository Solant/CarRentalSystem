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
 * getting admin to page with all unpaid orders
 */
public class UnpaidOrdersCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(UnpaidOrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        OrderDao orderDao;
        String page;
        List<Order> orders;
        try {
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            orders = orderDao.getUnPaidOrders();
            request.setAttribute("lst", orders);
        } catch (DAOException ex) {
            LOG.error("DAOException after applicationDaoImpl.getUnPaidApplications()" + ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        LOG.debug("->unpaidorders");
        page = ConfigurationManager.getProperty("path.page.unpaidorders");
        return page;

    }

}
