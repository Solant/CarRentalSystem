package by.skakun.carrentalsystem.command.auth;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.connectionpool.ConnectionPool;
import by.skakun.carrentalsystem.dao.ClientDao;
import by.skakun.carrentalsystem.dao.impl.ClientDaoImpl;
import by.skakun.carrentalsystem.entity.Client;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.logic.PasswordHashing;
import by.skakun.carrentalsystem.manager.ConfigurationManager;
import by.skakun.carrentalsystem.manager.MessageManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class RegisterCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(RegisterCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        String page ;
        String login = request.getParameter("login");
        String realname = request.getParameter("realname");
        String password = request.getParameter("password");
        String passwordRepeat = request.getParameter("passwordRepeat");
        if (!password.equals(passwordRepeat)) {
            request.setAttribute("errorPassword", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.register");
            return page;
        }
        String surname = request.getParameter("surname");
        String passport = request.getParameter("pass_num");
        String email = request.getParameter("email");
        String type = "USER";
        password = PasswordHashing.getHashValue(password);
        ClientDao userDao = (ClientDao) new ClientDaoImpl(ConnectionPool.getConnection());
        Client user = new Client(login, password, realname, surname, passport, type, email);
        try {
            userDao.create(user);
            page = ConfigurationManager.getProperty("path.page.index");
            return page;
        } catch (DAOException ex) {
            LOG.info("Couldn't add the user to the database" + ex);
            page = ConfigurationManager.getProperty("path.page.register");
            return page;
        }
    }
}