package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 * 
 * gets admin to page where they can choose the user to delete / not reccomended
 */
public class UsersDeleteCommand implements ActionCommand{
    private static final Logger LOG = Logger.getLogger(UsersDeleteCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("->deleteUsers");
        String page = ConfigurationManager.getProperty("path.page.deleteusers");
        return page; 
    }
    
}
