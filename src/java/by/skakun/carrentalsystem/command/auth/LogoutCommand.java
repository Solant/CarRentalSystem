
package by.skakun.carrentalsystem.command.auth;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.command.user.MainRedirectCommand;
import by.skakun.carrentalsystem.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class LogoutCommand implements ActionCommand{
    private static final Logger LOG = Logger.getLogger(MainRedirectCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
     String user = (String) request.getSession().getAttribute("userName");
     request.setAttribute("userName", "GUEST");
     String page = ConfigurationManager.getProperty("path.page.index");
     request.getSession().invalidate();
     return page;
    
    }
    
}
