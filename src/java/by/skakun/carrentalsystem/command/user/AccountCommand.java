package by.skakun.carrentalsystem.command.user;


import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 * 
 * processing redirect to account.jsp
 */
public class AccountCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(AccountCommand.class);

    /**
     * Redirecting to the page My Account with info about the user
     * @param request from user
     * @return page address
     */
    @Override
    public String execute(HttpServletRequest request) {
        LOG.debug("->account.jsp");
        String page;
        page = ConfigurationManager.getProperty("path.page.account");
        return page;
    }

}
