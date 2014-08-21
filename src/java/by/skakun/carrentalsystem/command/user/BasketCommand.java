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
 * showing user their basket with confirmed orders waiting to be paid for
 */
public class BasketCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(BasketCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("BasketCommand");
        OrderDaoImpl orderDao;
        OrderDaoImpl orderDao2;
        OrderDaoImpl orderDao3;
        List<Order> order;
        List<Order> orderArch;
        List<Order> orderDenied;
        try {
            orderDao = new OrderDaoImpl();
            orderDao2 = new OrderDaoImpl();
            orderDao3 = new OrderDaoImpl();
            int id = (int) request.getSession().getAttribute("userId");
            order = orderDao.getUByUserId(id);
            orderArch = orderDao2.getAByUserId(id);
            orderDenied = orderDao3.getDByUserId(id);
        } catch (DAOException ex) {
            LOG.fatal("Couldn't establish the connection to the database", ex);
            LOG.info("->errorpage");
            String page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        request.setAttribute("lst", order);
        request.setAttribute("lstA", orderArch);  
        request.setAttribute("lstR", orderDenied); 
        LOG.info("->basket");
        String page = ConfigurationManager.getProperty("path.page.basket");
        return page;
    }
}
