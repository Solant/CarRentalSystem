package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class CarsAdmCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(CarsAdmCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("->cars");
        String page = ConfigurationManager.getProperty("path.page.carsadmin");
        return page;

    }

}
