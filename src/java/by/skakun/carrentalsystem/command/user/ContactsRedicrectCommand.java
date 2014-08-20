package by.skakun.carrentalsystem.command.user;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class ContactsRedicrectCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ContactsRedicrectCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("Перенаправляем на страницу contacts");
        String page = ConfigurationManager.getProperty("path.page.contacts");
        LOG.info("page");
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
