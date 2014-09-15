package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 * returns page for adding a new car
 */
public class AddNewCarCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(AddNewCarCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.addnewcar");
        LOG.debug("->addnewcar.jsp");
        return page;
    }
}
