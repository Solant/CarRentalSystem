package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.OrderDao;
import by.skakun.carrentalsystem.dao.impl.OrderDaoImpl;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 * marking the order as returned without any damage
 */
public class ReturnCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ReturnCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        OrderDao orderDao;
        try {
            orderDao = new OrderDaoImpl();
        } catch (DAOException ex) {
            LOG.fatal("Couldn't establish the connection to the database", ex);
            LOG.debug("->errorpage");
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        String appl = (String) request.getParameter("applId");
        int applId = Integer.parseInt(appl);
        try {
            orderDao.returnCar(applId);
            page = new PaidOrdersCommand().execute(request);
        } catch (DAOException ex) {
            LOG.error("DaoException while ReturnCommand: " + ex);
            page = new PaidOrdersCommand().execute(request);
        }
        LOG.debug("->paidorders.jsp");
        return page;

    }

}
