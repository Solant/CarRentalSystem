package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.DaoFactory;
import by.skakun.carrentalsystem.dao.DaoType;
import by.skakun.carrentalsystem.dao.OrderDao;
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
 * marking the order as returned with any damage and creating a damagebill
 */
public class ReturnDamageCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ReturnDamageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        OrderDao orderDao;

        String order = (String) request.getParameter("applId");
        String damage = (String) request.getParameter("damage");
        if (EnteredInfoValidator.dataLength(damage)) {
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        String damageCost = (String) request.getParameter("damagecost");
        int applId = Integer.parseInt(order);
        int dCost = Integer.parseInt(damageCost);
        if (!EnteredInfoValidator.rentPrice(dCost)) {
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        List<Order> orders;
        try {
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            orderDao.returnDamage(applId, damage, dCost);
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            orders = orderDao.getPaidOrders();
            request.setAttribute("lst", orders);
        } catch (DAOException ex) {
            LOG.error("DAOException while confirmComand: " + ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        page = ConfigurationManager.getProperty("path.page.paidorders");
        return page;

    }

}
