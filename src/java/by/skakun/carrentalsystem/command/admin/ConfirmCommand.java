package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.impl.OrderDaoImpl;
import by.skakun.carrentalsystem.exception.DAOException;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 * 
 * sets an order as confirmed and returns admin to page with other orders
 */
public class ConfirmCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ConfirmCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        boolean flag = false;
        try {
            OrderDaoImpl applDao = new OrderDaoImpl();
            String appl = (String) request.getParameter("applId");
            int applId = Integer.parseInt(appl);
            flag = applDao.confirm(applId);
        } catch (DAOException ex) {
            LOG.error("DAOException while confirmComand: " + ex);
        }
        if (flag) {
            request.setAttribute("csuccess", "1");
            page = new NewOrdersCommand().execute(request);

        } else {
            page = new NewOrdersCommand().execute(request);
        }
        LOG.info("->neworders");
        return page;

    }

}
