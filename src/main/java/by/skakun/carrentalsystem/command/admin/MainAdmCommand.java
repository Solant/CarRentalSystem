package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.CarDao;
import by.skakun.carrentalsystem.dao.ClientDao;
import by.skakun.carrentalsystem.dao.DaoFactory;
import by.skakun.carrentalsystem.dao.DaoType;
import by.skakun.carrentalsystem.dao.OrderDao;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import by.skakun.carrentalsystem.util.StatisticsTagHandler;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun presents main page to admin
 */
public class MainAdmCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(MainAdmCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;

        /* 
        * StatisticsTagHandler methods return lists of requested information, e.g.
        * number of users or unpaid orders
        */

        request.setAttribute("rw", StatisticsTagHandler.getOrderStats().size());
        request.setAttribute("us", StatisticsTagHandler.getUserStats().size());
        request.setAttribute("car", StatisticsTagHandler.getCarsStats().size());
        page = ConfigurationManager.getProperty("path.page.admin");
        return page;

    }

}
