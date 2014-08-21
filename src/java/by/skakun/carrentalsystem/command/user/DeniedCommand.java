package by.skakun.carrentalsystem.command.user;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.impl.OrderDaoImpl;
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
        LOG.info("DeniedCommand");
        OrderDaoImpl orderDao;
        List<Order> orderDenied;
        try {
            orderDao = new OrderDaoImpl();
            int id = (int) request.getSession().getAttribute("userId");
            orderDenied = orderDao.getDByUserId(id);
        } catch (DAOException ex) {
            LOG.fatal("Couldn't establish the connection to the database", ex);
            LOG.info("->errorpage");
            String page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        request.setAttribute("lstR", orderDenied); 
        LOG.info("->denied");
        String page = ConfigurationManager.getProperty("path.page.ordersdenied");
        return page;
    }
}
