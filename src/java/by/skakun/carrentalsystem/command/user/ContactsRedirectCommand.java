package by.skakun.carrentalsystem.command.user;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 * 
 * getting user to contacts page
 */
public class ContactsRedirectCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ContactsRedirectCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("->contacts");
        String page = ConfigurationManager.getProperty("path.page.contacts");
        return page;

    }

}

/**
 *
 * @param request
 * @return content for page result/or redirects to login page with error message
 */
/*   @Override
 public String execute(HttpServletRequest request) {
 String page = ConfigurationManager.getProperty("path.page.register");
 return page;

 } */
