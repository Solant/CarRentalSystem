package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.connectionpool.ConnectionPool;
import by.skakun.carrentalsystem.dao.impl.OrderDaoImpl;
import by.skakun.carrentalsystem.entity.Order;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.manager.ConfigurationManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class PaidOrdersCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(PaidOrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        OrderDaoImpl applDao = new OrderDaoImpl(ConnectionPool.getConnection());
        List<Order> appls = null;
        try {
            appls = applDao.getPaidOrders();
        } catch (DAOException ex) {
            LOG.info("DAOException after applicationDaoImpl.getPaidApplications()" + ex);
        }
        request.setAttribute("lst", appls);
        LOG.info("->paidorders");
        String page = ConfigurationManager.getProperty("path.page.paidorders");
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
