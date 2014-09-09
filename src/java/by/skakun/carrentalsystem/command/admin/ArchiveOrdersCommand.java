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
 * @author Skakun sends admin to page with all archived orders
 */
public class ArchiveOrdersCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ArchiveOrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        OrderDao orderDao;
        String page;
        orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
        List<Order> orders;
        
        try {
            orders = orderDao.getArchiveOrders();
            request.setAttribute("lst", orders);
            page = ConfigurationManager.getProperty("path.page.archiveorders");
        } catch (DAOException ex) {
            LOG.error("DAOException while applicationDaoImpl.getAll()." + ex);
            page = ConfigurationManager.getProperty("path.page.error");
        }
        
        LOG.debug("-> archiveorders.jsp");
        return page;

    }

}
