package by.skakun.carrentalsystem.command.auth;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 *
 * logging the user out of the system
 */
public class LogoutCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        request.getSession().invalidate();
        LOG.debug("session invalidated");
        page = ConfigurationManager.getProperty("path.page.index");
        return page;

    }

}
