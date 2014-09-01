package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.ClientDao;
import by.skakun.carrentalsystem.dao.impl.ClientDaoImpl;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import by.skakun.carrentalsystem.util.EnteredInfoValidator;
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
        boolean flag;
        int id = Integer.parseInt((String) request.getParameter("user_id"));
        String type = (String) request.getParameter("type");
        if(type.equalsIgnoreCase("ADMIN")) {
            page = new UsersCommand().execute(request);
            return page;
        }
        int active = Integer.parseInt((String) request.getParameter("active"));
        ClientDao clientDao;
        try {
            clientDao = new ClientDaoImpl();
        } catch (DAOException ex) {
            LOG.fatal("Couldn't establish the connection to the database", ex);
            LOG.debug("->errorpage");
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        try {
            flag = clientDao.changeActive(id, active);
            if (flag) {
                page = new UsersCommand().execute(request);
                return page;
            } else {
                page = new UsersCommand().execute(request);
                return page;
            }
        } catch (DAOException ex) {
            LOG.error("DAOException while ChangeActiveCommand", ex);
            page = new UsersCommand().execute(request);
            return page;
        }

    }

}
