package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.ClientDao;
import by.skakun.carrentalsystem.dao.DaoFactory;
import by.skakun.carrentalsystem.dao.DaoType;
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
        ClientDao clientDao;
        String page;
        List<Client> clients;
        try {
            clientDao = (ClientDao) DaoFactory.getDao(DaoType.CLIENT);
            clients = clientDao.getAllUsers();
            request.setAttribute("lst", clients);
            page = ConfigurationManager.getProperty("path.page.allusers");
        } catch (DAOException ex) {
            LOG.error("DAOException while clientDao.getAll()" + ex);
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}
