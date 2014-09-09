package by.skakun.carrentalsystem.command.auth;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 *
 * getting user to logout.jsp
 */
public class LogoutRedirectCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(LogoutRedirectCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        LOG.debug("->logout.jsp");
        page = ConfigurationManager.getProperty("path.page.logout");
        return page;

    }

}
