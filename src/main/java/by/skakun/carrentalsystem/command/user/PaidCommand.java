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
 * shows user a list of already archived/paidfor orders
 */
public class PaidCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(PaidCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        OrderDao orderDao;
        String page;
        List<Order> orderArch;
        try {
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            int id = (int) request.getSession().getAttribute("userId");
            orderArch = orderDao.getAByUserId(id);
            request.setAttribute("lstA", orderArch);
            page = ConfigurationManager.getProperty("path.page.orderspaid");
        } catch (DAOException ex) {
            LOG.error("DaoException while getAByUserId(id)", ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        return page;
    }
}
