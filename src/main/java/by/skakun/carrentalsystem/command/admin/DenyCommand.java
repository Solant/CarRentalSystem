package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.DaoFactory;
import by.skakun.carrentalsystem.dao.DaoType;
import by.skakun.carrentalsystem.dao.impl.OrderDaoImpl;
import by.skakun.carrentalsystem.entity.Order;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import by.skakun.carrentalsystem.util.EnteredInfoValidator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 *
 * denies user's order
 */
public class DenyCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(DenyCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        boolean flag;
        OrderDaoImpl orderDao;
        String appl = (String) request.getParameter("applId");
        String reason = (String) request.getParameter("reasonForRefusal");
        if (EnteredInfoValidator.dataLength(reason)) {
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        List<Order> orders;

        int applId = Integer.parseInt(appl);
        try {
            orderDao = (OrderDaoImpl) DaoFactory.getDao(DaoType.ORDER);
            flag = orderDao.deny(applId, reason);
            
            orderDao = (OrderDaoImpl) DaoFactory.getDao(DaoType.ORDER);
            orders = orderDao.getNewOrders();
            request.setAttribute("lst", orders);

        } catch (DAOException ex) {
            LOG.error("DAOException in denyComand: " + ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        if (flag) {
            request.setAttribute("cfail", "1");
        }
        page = ConfigurationManager.getProperty("path.page.neworders");
        return page;
    }

}
