package by.skakun.carrentalsystem.command.user;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.ClientDao;
import by.skakun.carrentalsystem.dao.impl.ClientDaoImpl;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import by.skakun.carrentalsystem.util.EnteredInfoValidator;
import by.skakun.carrentalsystem.util.PasswordHashing;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 *
 * processing request to change user's password
 */
public class ChangePasswordConfCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ChangePasswordConfCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        boolean flag;
        int id = (int) request.getSession().getAttribute("userId");
        String password = (String) request.getParameter("pass");
        String newpassword = (String) request.getParameter("newpass");
        if (!EnteredInfoValidator.passwordVal(newpassword)) {
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        password = PasswordHashing.getHashValue(password);
        newpassword = PasswordHashing.getHashValue(newpassword);
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
            flag = clientDao.changePassword(id, password, newpassword);
            if (flag) {
                request.setAttribute("cpSuccess", "1");
                page = ConfigurationManager.getProperty("path.page.changepass");
                return page;
            } else {
                request.setAttribute("cpError", "1");
                page = ConfigurationManager.getProperty("path.page.changepass");
                return page;
            }
        } catch (DAOException ex) {
            LOG.error("DAOException while ChangePasswordConfCommand", ex);
            page = ConfigurationManager.getProperty("path.page.changepass");
            return page;
        }

    }
}
