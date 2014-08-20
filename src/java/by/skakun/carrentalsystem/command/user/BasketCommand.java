package by.skakun.carrentalsystem.command.user;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.connectionpool.ConnectionPool;
import by.skakun.carrentalsystem.dao.impl.OrderDaoImpl;
import by.skakun.carrentalsystem.entity.Order;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.manager.ConfigurationManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class BasketCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(BasketCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("BasketCommand");
        OrderDaoImpl orderDao = new OrderDaoImpl(ConnectionPool.getConnection());
        List<Order> order = null;
        List<Order> orderArch = null;
        List<Order> orderDenied = null;
        try {
            int id = (int) request.getSession().getAttribute("userId");
            order = orderDao.getUByUserId(id);
            orderArch = orderDao.getAByUserId(id);
            orderDenied = orderDao.getDByUserId(id); 
        } catch (DAOException ex) {
            LOG.info("DAOException while basket-command" + ex.getLocalizedMessage());
        }
        request.setAttribute("lst", order);
        request.setAttribute("lstA", orderArch);  //
        request.setAttribute("lstR", orderDenied);  //
        LOG.info("->basket");
        String page = ConfigurationManager.getProperty("path.page.basket");
        return page;

    }

}

/**
 *
 * @param request
 * @return content for page result/or redirects to login page with error message
 */
/*   @Override
 public String execute(HttpServletRequest request) {
 String page = ConfigurationManager.getProperty("path.page.register");
 return page;

 } */
