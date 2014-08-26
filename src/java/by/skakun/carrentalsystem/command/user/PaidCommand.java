package by.skakun.carrentalsystem.command.user;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.OrderDao;
import by.skakun.carrentalsystem.dao.impl.OrderDaoImpl;
import by.skakun.carrentalsystem.entity.Order;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class PaidCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(PaidCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        OrderDao orderDao;
        List<Order> orderArch;
        try {
            orderDao = new OrderDaoImpl();

            int id = (int) request.getSession().getAttribute("userId");
            orderArch = orderDao.getAByUserId(id);

        } catch (DAOException ex) {
            LOG.fatal("Couldn't establish the connection to the database", ex);
            LOG.debug("->errorpage");
            String page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        request.setAttribute("lstA", orderArch);
        LOG.debug("->basket");
        String page = ConfigurationManager.getProperty("path.page.orderspaid");
        return page;
    }
}
