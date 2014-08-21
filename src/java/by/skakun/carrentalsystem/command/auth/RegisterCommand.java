package by.skakun.carrentalsystem.command.auth;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.impl.ClientDaoImpl;
import by.skakun.carrentalsystem.entity.Client;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.logic.PasswordHashing;
import by.skakun.carrentalsystem.manager.ConfigurationManager;
import by.skakun.carrentalsystem.manager.MessageManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 * 
 * registering user in the system and returning him to login.jsp
 */
public class RegisterCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(RegisterCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        String page;
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
        int active = 1; //1 means active, 0 means inactive
        int credit = 1000; // srandart sum, placeholder for real billing
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
        try {
            boolean flag = clientDao.checkLogin(login);
            if(flag) {
            request.setAttribute("errorLogin", 1);
            page = ConfigurationManager.getProperty("path.page.register");
            return page;
            }
        } catch (DAOException ex) {
            LOG.error("DAOException while checkLogin()");
        }
        
        Client user = new Client(login, password, realname, surname, passport, type, email, active, credit);
        try {
            clientDao = new ClientDaoImpl();
            clientDao.create(user);
            page = ConfigurationManager.getProperty("path.page.index");
            return page;
        } catch (DAOException ex) {
            LOG.info("Couldn't add the user to the database" + ex);
            page = ConfigurationManager.getProperty("path.page.register");
            return page;
        }
    }
}
