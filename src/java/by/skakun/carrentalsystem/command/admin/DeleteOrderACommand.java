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
 * deletes unpaid orders which admin chose
 */
public class DeleteOrderACommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(DeleteOrderACommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;

        LOG.info("DeleteOrderACommand");
        boolean flag = false;
        String idA = (String) request.getParameter("applId");
        int id = Integer.parseInt(idA);
        OrderDao applDao;
        try {
            applDao = new OrderDaoImpl();
        } catch (DAOException ex) {
            LOG.fatal("Couldn't establish the connection to the database");
            LOG.debug("->errorpage");
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        try {
            flag = applDao.delete(id);
        } catch (DAOException ex) {
            LOG.error("DAOException while PayCommand: " + ex);
        }
        if (flag) {
            page = new UnpaidOrdersCommand().execute(request);
            return page;
        } else {
            page = new UnpaidOrdersCommand().execute(request);
            return page;
        }

    }
}
