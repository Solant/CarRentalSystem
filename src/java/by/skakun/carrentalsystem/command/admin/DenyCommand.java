package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.connectionpool.ConnectionPool;
import by.skakun.carrentalsystem.dao.impl.OrderDaoImpl;
import by.skakun.carrentalsystem.exception.DAOException;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 */
public class DenyCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(DenyCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        Boolean flag = false;
        try {
            OrderDaoImpl applDao = new OrderDaoImpl(ConnectionPool.getConnection());
            String appl = (String) request.getParameter("applId");
            String reason = (String) request.getParameter("reasonForRefusal");
            int applId = Integer.parseInt(appl);
            flag = applDao.deny(applId, reason);
        } catch (DAOException ex) {
            LOG.info("DaoMistake in denyComand: " + ex);
        }
        if (flag) {
            request.setAttribute("cfail", "1");
            page = new NewOrdersCommand().execute(request);

        } else {
            page = new NewOrdersCommand().execute(request);
        }
        return page;
    }

}
