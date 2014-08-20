package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.connectionpool.ConnectionPool;
import by.skakun.carrentalsystem.dao.impl.OrderDaoImpl;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author apple
 */
public class ReturnDamageCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ReturnDamageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        LOG.info("ReturnDamageCommand");
        try {
            OrderDaoImpl applDao = new OrderDaoImpl(ConnectionPool.getConnection());
            String appl = (String) request.getParameter("applId");
            String damage = (String) request.getParameter("damage");
            LOG.info("damage" + damage);
            String damageCost = (String) request.getParameter("damagecost");
            LOG.info("damageCost" + damageCost);
            int applId = Integer.parseInt(appl);
            int dCost = Integer.parseInt(damageCost);
            applDao.returnDamage(applId, damage, dCost);
            page = new PaidOrdersCommand().execute(request);
        } catch (DAOException ex) {
            LOG.info("DAOException while confirmComand: " + ex);
            page = new PaidOrdersCommand().execute(request);

        }
        return page;

    }

}
