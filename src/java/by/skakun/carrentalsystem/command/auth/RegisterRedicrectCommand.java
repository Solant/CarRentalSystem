package by.skakun.carrentalsystem.command.auth;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 * getting guest to the register.jsp
 */
public class RegisterRedicrectCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(RegisterRedicrectCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("->register.jsp");
        String page = ConfigurationManager.getProperty("path.page.register");
        return page;

    }

}