package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.ClientDao;
import by.skakun.carrentalsystem.dao.DaoFactory;
import by.skakun.carrentalsystem.dao.DaoType;
import by.skakun.carrentalsystem.dao.impl.ClientDaoImpl;
import by.skakun.carrentalsystem.entity.Client;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import by.skakun.carrentalsystem.util.EnteredInfoValidator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 *
 *
 * deletes user / not reccomended
 */
public class DeleteUserCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(DeleteUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        int id = Integer.parseInt((String) request.getParameter("user_id"));
        int active = Integer.parseInt((String) request.getParameter("active"));
        ClientDao clientDao;
        List<Client> clients;
        try {
            clientDao = (ClientDao) DaoFactory.getDao(DaoType.CLIENT);
            clientDao.changeActive(id, active);
            clientDao = (ClientDao) DaoFactory.getDao(DaoType.CLIENT);
            clients = clientDao.getAllUsers();
            request.setAttribute("lst", clients);
            page = ConfigurationManager.getProperty("path.page.allusers");
        } catch (DAOException ex) {
            LOG.error("DAOException while ChangeActiveCommand", ex);
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;

    }

}
