package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class MainAdmCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(MainAdmCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("Перенаправляем на страницу admin main");
        String page = ConfigurationManager.getProperty("path.page.admin");
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
