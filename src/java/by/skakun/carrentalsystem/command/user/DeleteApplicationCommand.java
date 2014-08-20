package by.skakun.carrentalsystem.command.user;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.connectionpool.ConnectionPool;
import by.skakun.carrentalsystem.dao.impl.OrderDaoImpl;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.manager.ConfigurationManager;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class DeleteApplicationCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(DeleteApplicationCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;

        LOG.info("DeleteApplicationCommand");
        boolean flag = false;
        String idA = (String) request.getParameter("applid");
        int id = Integer.parseInt(idA);
        LOG.info("Application id" + id);
        OrderDaoImpl orderDao;
        try {
            orderDao = new OrderDaoImpl();
        } catch (DAOException ex) {
            LOG.fatal("Couldn't establish the connection to the database", ex);
            LOG.info("->errorpage");
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        try {
            flag = orderDao.delete(id);
        } catch (DAOException ex) {
            LOG.info("DAOException while PayCommand: " + ex);
        }
        if (flag) {
        //    LOG.info("True");
            //    request.setAttribute("success", "1");
            page = new BasketCommand().execute(request);
            return page;
        } else {
        //    LOG.info("False");
            //    request.setAttribute("fail", "1");
            page = new BasketCommand().execute(request);
            return page;
        }

    }
}
