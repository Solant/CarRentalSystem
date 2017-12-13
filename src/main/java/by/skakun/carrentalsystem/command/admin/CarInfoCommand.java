package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun 
 * 
 * sends admin to page with information about chosen car
 */
public class CarInfoCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(CarInfoCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String carname = (String) request.getParameter("carname");
        String carprice = (String) request.getParameter("carprice");
        String carid = (String) request.getParameter("carid");
        String active = (String) request.getParameter("active");
        String carimage = (String) request.getParameter("carimage");
        
        request.getSession().setAttribute("carid", carid);
        request.getSession().setAttribute("carname", carname);
        request.getSession().setAttribute("carimage", carimage);
        request.getSession().setAttribute("carprice", carprice);
        request.getSession().setAttribute("active", active);
        
        LOG.debug("->carinfo");
        String page = ConfigurationManager.getProperty("path.page.carchange");
        return page;

    }

}
