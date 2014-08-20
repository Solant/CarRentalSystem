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

public class ArchiveOrdersCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ArchiveOrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        OrderDaoImpl applDao;
        try {
            applDao = new OrderDaoImpl();
        } catch (DAOException ex) {
            LOG.fatal("Couldn't establish the connection to the database", ex);
            LOG.info("->errorpage");
            String page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        List<Order> appls = null;
        try {
            appls = applDao.getArchiveOrders();
            LOG.info(appls.toString());
        } catch (DAOException ex) {
            LOG.info("Ошибка ДАО после попытки applicationDaoImpl.getAll()." + ex.getLocalizedMessage());
        }
        request.setAttribute("lst", appls);
        LOG.info("Перенаправляем на страницу archiveorders");
        String page = ConfigurationManager.getProperty("path.page.archiveorders");
        LOG.info("page");
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
