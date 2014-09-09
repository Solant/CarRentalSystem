package by.skakun.carrentalsystem.command.user;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.DaoFactory;
import by.skakun.carrentalsystem.dao.DaoType;
import by.skakun.carrentalsystem.dao.OrderDao;
import by.skakun.carrentalsystem.dao.impl.OrderDaoImpl;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import by.skakun.carrentalsystem.util.StatisticsTagHandler;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 *
 * getting user to main.jsp
 */
public class MainRedirectCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(MainRedirectCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        int id = (int) request.getSession().getAttribute("userId");
        int size = StatisticsTagHandler.getUsersOrders(id).size();
        if (size > 0) {
            request.setAttribute("flag", "1");
            request.setAttribute("rw", size);
        }

        LOG.debug("->main.jsp");
        String page = ConfigurationManager.getProperty("path.page.main");
        return page;

    }

}
