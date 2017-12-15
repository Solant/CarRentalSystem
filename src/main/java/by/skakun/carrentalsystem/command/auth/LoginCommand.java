package by.skakun.carrentalsystem.command.auth;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.ClientDao;
import by.skakun.carrentalsystem.dao.DaoFactory;
import by.skakun.carrentalsystem.dao.DaoType;
import by.skakun.carrentalsystem.entity.Client;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import by.skakun.carrentalsystem.util.EnteredInfoValidator;
import by.skakun.carrentalsystem.util.LoginLogic;
import by.skakun.carrentalsystem.util.PasswordHashing;
import by.skakun.carrentalsystem.util.StatisticsTagHandler;
import java.util.*;
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
     * warning if login and/or password weren't correct
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
        clientDao = (ClientDao) DaoFactory.getDao(DaoType.CLIENT);
        List<Client> clients;

        try {
            clients = clientDao.getAll();
        } catch (DAOException ex) {
            LOG.error("DAOException after userDao.getAll()" + ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }

        switch (LoginLogic.checkLogin(clients, login, password, request)) {
            case 1:
                request.setAttribute("errorLogin", "1");
                page = ConfigurationManager.getProperty("path.page.index");
                return page;
            case 2:
                request.setAttribute("rw", StatisticsTagHandler.getOrderStats().size());
                request.setAttribute("us", StatisticsTagHandler.getUserStats().size());
                request.setAttribute("car", StatisticsTagHandler.getCarsStats().size());
                page = ConfigurationManager.getProperty("path.page.admin");
                return page;
            case 3:
                int id = (int) request.getSession().getAttribute("userId");
                int size = Optional.ofNullable(StatisticsTagHandler.getUsersOrders(id))
                        .orElseGet(Collections::emptyList)
                        .size();
                if (size > 0) {
                    request.setAttribute("flag", "1");
                    request.setAttribute("rw", size);
                }
                page = ConfigurationManager.getProperty("path.page.main");
                return page;
            default:
                request.setAttribute("errorPassword", "1");
                page = ConfigurationManager.getProperty("path.page.index");
                return page;
        }
    }
}
