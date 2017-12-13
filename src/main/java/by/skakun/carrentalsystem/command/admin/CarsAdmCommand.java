package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 * sends admin to cars.jsp
 */
public class CarsAdmCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(CarsAdmCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.debug("->cars");
        String page = ConfigurationManager.getProperty("path.page.carsadmin");
        return page;

    }

}
