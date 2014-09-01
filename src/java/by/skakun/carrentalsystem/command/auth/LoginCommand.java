package by.skakun.carrentalsystem.command.auth;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.command.admin.MainAdmCommand;
import by.skakun.carrentalsystem.command.user.MainRedirectCommand;
import by.skakun.carrentalsystem.dao.ClientDao;
import by.skakun.carrentalsystem.dao.impl.ClientDaoImpl;
import by.skakun.carrentalsystem.entity.Client;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import by.skakun.carrentalsystem.util.EnteredInfoValidator;
import by.skakun.carrentalsystem.util.LoginLogic;
import by.skakun.carrentalsystem.util.PasswordHashing;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 *
 * processing authorization request from user trying to log into the system
 */
public class LoginCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    /**
     *
     * @param request
     * @return either the main page for user or admin, or login page with
     * warning if login and/or password werent' correct
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (!EnteredInfoValidator.validateLoginInfo(login, password)) {
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        password = PasswordHashing.getHashValue(password);
        ClientDao clientDao;
        try {
            clientDao = new ClientDaoImpl();
        } catch (DAOException ex) {
            LOG.fatal("Couldn't establish the connection to the database", ex);
            LOG.info("->errorpage");
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        List<Client> clients = null;
        try {
            clients = clientDao.getAll();
        } catch (DAOException ex) {
            LOG.error("DAOException after userDao.getAll()" + ex);
        }

        switch (LoginLogic.checkLogin(clients, login, password, request)) {
            case 1:
                request.setAttribute("errorLogin", "1");
                page = ConfigurationManager.getProperty("path.page.index");
                return page;
            case 2:
                page = new MainAdmCommand().execute(request);
                return page;
            case 3:
                page = new MainRedirectCommand().execute(request);
                return page;
            default:
                request.setAttribute("errorPassword", "1");
                page = ConfigurationManager.getProperty("path.page.index");
                return page;
        }
    }
}
