package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.impl.ClientDaoImpl;
import by.skakun.carrentalsystem.entity.Client;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 *
 * gets admin to the page with the list of all users
 */
public class UsersCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(UsersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        ClientDaoImpl clientDao;
        try {
            clientDao = new ClientDaoImpl();
        } catch (DAOException ex) {
            LOG.fatal("Couldn't establish the connection to the database", ex);
            LOG.info("->errorpage");
            String page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        List<Client> clients = null;
        try {
            clients = clientDao.getAll();
            LOG.info(clients.toString());
        } catch (DAOException ex) {
            LOG.info("DAOException while clientDao.getAll()" + ex);
        }
        request.setAttribute("lst", clients);
        LOG.info("->users");
        String page = ConfigurationManager.getProperty("path.page.allusers");
        return page;
    }
}
