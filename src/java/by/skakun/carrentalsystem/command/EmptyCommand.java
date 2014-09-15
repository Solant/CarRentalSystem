package by.skakun.carrentalsystem.command;

import by.skakun.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Skakun
 * in case the command wasn't specified
 */
public class EmptyCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.error");
        request.getSession().invalidate();
        return page;
    }
    
}
