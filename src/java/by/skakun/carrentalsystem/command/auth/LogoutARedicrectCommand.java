package by.skakun.carrentalsystem.command.auth;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 *
 * processing redirect to logouta.jsp
 */
public class LogoutARedicrectCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(LogoutARedicrectCommand.class);

    /**
     *
     * @param request
     * @return logouta.jsp page
     */
    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("->logouta.jsp");
        String page = ConfigurationManager.getProperty("path.page.logouta");
        return page;

    }

}
