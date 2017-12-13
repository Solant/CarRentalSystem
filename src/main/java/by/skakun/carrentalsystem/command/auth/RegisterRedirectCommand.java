package by.skakun.carrentalsystem.command.auth;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 * 
 * getting guest to the register.jsp
 */
public class RegisterRedirectCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(RegisterRedirectCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        LOG.debug("->register.jsp");
        page = ConfigurationManager.getProperty("path.page.register");
        return page;
    }

}