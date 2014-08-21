package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.connectionpool.ConnectionPool;
import by.skakun.carrentalsystem.dao.impl.ClientDaoImpl;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.manager.ConfigurationManager;
import by.skakun.carrentalsystem.manager.MessageManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 * 
 * deletes user / not reccomended
 */
public class DeleteUserCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(DeleteUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        ClientDaoImpl clientDao;
        try {
            clientDao = new ClientDaoImpl();
        } catch (DAOException ex) {
            LOG.fatal("Couldn't establish the connection to the database", ex);
            LOG.info("->errorpage");
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        boolean flag;
        int id = Integer.parseInt((String) request.getParameter("user_id"));
        try {
            flag = clientDao.deleteUser(id);
            if (flag) {
                request.setAttribute("deleteSuccess", MessageManager.getProperty("message.loginerror"));
                page = ConfigurationManager.getProperty("path.page.deleteusers");
                return page;

            } else {
                request.setAttribute("deleteError", MessageManager.getProperty("message.loginerror"));
                page = ConfigurationManager.getProperty("path.page.deleteusers");
                return page;

            }
        } catch (DAOException ex) {
            LOG.info("DAOException while DeleteUserCommand", ex);
            page = ConfigurationManager.getProperty("path.page.deleteusers");
            return page;
        }

    }

}
