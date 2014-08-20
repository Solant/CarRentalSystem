package by.skakun.carrentalsystem.command.user;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 * processing user's request to get to changepassword.jsp
 */
public class ChangePasswordCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ChangePasswordCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("->changepassword.jsp");
        String page = ConfigurationManager.getProperty("path.page.changepass");
        return page;

    }

}
