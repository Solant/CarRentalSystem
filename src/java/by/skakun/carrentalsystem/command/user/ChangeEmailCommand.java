package by.skakun.carrentalsystem.command.user;

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
 * processing request to change user's email
 */
public class ChangeEmailCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ChangeEmailCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        boolean flag;
        int id = (int) request.getSession().getAttribute("userId");
        String email = (String) request.getParameter("newemail");
        if(!EnteredInfoValidator.emailVal(email)){
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
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
            flag = clientDao.changeEmail(id, email);
            if (flag) {
                request.setAttribute("cpSuccess", "1");
                request.getSession().setAttribute("userEmail", email);
                page = ConfigurationManager.getProperty("path.page.account");
                return page;
            } else {
                request.setAttribute("cpError", "1");
                page = ConfigurationManager.getProperty("path.page.account");
                return page;
            }
        } catch (DAOException ex) {
            LOG.error("DAOException while ChangePasswordConfCommand", ex);
            page = ConfigurationManager.getProperty("path.page.changepass");
            return page;
        }

    }
}
