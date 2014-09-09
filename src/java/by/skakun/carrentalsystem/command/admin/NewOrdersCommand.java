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
 * getting admin to the page with all new applications
 */
public class NewOrdersCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(NewOrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        OrderDao applDao;
        String page;
        List<Order> orders;
        try {
            applDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            orders = applDao.getNewOrders();
        } catch (DAOException ex) {
            LOG.error("DAOException after OrderDao.getNewApplications()" + ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        request.setAttribute("lst", orders);
        LOG.debug("->neworders");
        page = ConfigurationManager.getProperty("path.page.neworders");
        return page;

    }

}
