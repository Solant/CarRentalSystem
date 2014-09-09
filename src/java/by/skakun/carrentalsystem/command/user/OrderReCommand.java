package by.skakun.carrentalsystem.command.user;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.DaoFactory;
import by.skakun.carrentalsystem.dao.DaoType;
import by.skakun.carrentalsystem.dao.OrderDao;
import by.skakun.carrentalsystem.entity.Order;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import by.skakun.carrentalsystem.util.EnteredInfoValidator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 *
 * adding order to the database
 */
public class OrderReCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(OrderReCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        int userId = (int) request.getSession().getAttribute("userId");
        String carId = (String) request.getSession().getAttribute("carId");
        int carid = Integer.parseInt(carId);
        int sumToPay;
        int carPrice = Integer.parseInt((String) request.getSession().getAttribute("carPrice"));
        int period = Integer.parseInt((String) request.getParameter("period"));
        if (!EnteredInfoValidator.periodVal(period)) {
            request.setAttribute("cpError", 1);
            page = ConfigurationManager.getProperty("path.page.define");
            return page;
        }
        sumToPay = carPrice * period;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedate;
        java.sql.Date sql = null;
        try {
            parsedate = formatter.parse((String) request.getParameter("date"));
            sql = new java.sql.Date(parsedate.getTime());
        } catch (ParseException ex) {
            LOG.error("ParseException while converting date: " + ex);
        }
        
        OrderDao orderDao;
        Order appl = new Order(userId, carid, sumToPay, carPrice, period, sql);
        
        try {
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            orderDao.create(appl);
            page = ConfigurationManager.getProperty("path.page.order.success");
        } catch (DAOException ex) {
            LOG.error("DAOException while orderDao.create()" + ex);
            request.setAttribute("cpError", 1);
            page = ConfigurationManager.getProperty("path.page.define");
        }
        
        return page;
    }

}
