package by.skakun.carrentalsystem.command.auth;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.connectionpool.ConnectionPool;
import by.skakun.carrentalsystem.dao.impl.ClientDaoImpl;
import by.skakun.carrentalsystem.entity.Client;
import by.skakun.carrentalsystem.entity.ClientType;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.logic.PasswordHashing;
import by.skakun.carrentalsystem.manager.ConfigurationManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author apple processing authorization request from user trying to log into
 * the system
 */
public class LoginCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    /**
     *
     */
    public boolean flag = false;

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
        if (login.isEmpty() || password.isEmpty()) {
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        password = PasswordHashing.getHashValue(password);
        ClientDaoImpl clientDao;
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
            LOG.info("DAOException after userDao.getAll()" + ex.getLocalizedMessage());
        }

        String login2;
        String password2;

        if (clients != null) {
            for (Client client : clients) {
                if (client != null) {
                    login2 = client.getLogin();
                    password2 = client.getPassword();
                    if (login.equals(login2) && password.equals(password2)) {
                        HttpSession httpSession = request.getSession();
                        httpSession.setAttribute("user", client);
                        httpSession.setAttribute("userType", client.getType());
                        httpSession.setAttribute("userName", client.getLogin());
                        httpSession.setAttribute("userId", client.getId());
                        httpSession.setAttribute("userPassNum", client.getPassNum());
                        httpSession.setAttribute("userSurname", client.getSurname());
                        httpSession.setAttribute("userRealName", client.getName());
                        httpSession.setAttribute("credit", client.getCredit());
                        if (client.getType().equals(ClientType.ADMIN)) {
                            page = ConfigurationManager.getProperty("path.page.admin");
                            return page;
                        } else {
                            page = ConfigurationManager.getProperty("path.page.main");
                            return page;
                        }
                    }

                }
            }
        }

        request.setAttribute("errorPassword", "1");
        page = ConfigurationManager.getProperty("path.page.index");
        return page;

    }
}
