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
 *
 * marking the order as returned with any damage and creating a damagebill
 */
public class ReturnDamageCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ReturnDamageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        LOG.info("ReturnDamageCommand");
        OrderDao orderDao;
        try {
            orderDao = new OrderDaoImpl();
        } catch (DAOException ex) {
            LOG.fatal("Couldn't establish the connection to the database", ex);
            LOG.debug("->errorpage");
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        String order = (String) request.getParameter("applId");
        String damage = (String) request.getParameter("damage");
        String damageCost = (String) request.getParameter("damagecost");
        int applId = Integer.parseInt(order);
        int dCost = Integer.parseInt(damageCost);
        try {
            orderDao.returnDamage(applId, damage, dCost);
            page = new PaidOrdersCommand().execute(request);
        } catch (DAOException ex) {
            LOG.error("DAOException while confirmComand: " + ex);
            page = new PaidOrdersCommand().execute(request);
        }
        return page;

    }

}
