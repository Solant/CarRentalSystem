package by.skakun.carrentalsystem.command.user;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.connectionpool.ConnectionPool;
import by.skakun.carrentalsystem.dao.OrderDao;
import by.skakun.carrentalsystem.dao.impl.OrderDaoImpl;
import by.skakun.carrentalsystem.entity.Order;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.manager.ConfigurationManager;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class OrderReCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(OrderReCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        LOG.info("OrderReCommand");
        int userId = (int) request.getSession().getAttribute("userId");
        String carId = (String) request.getSession().getAttribute("carId");
        int carid = Integer.parseInt(carId);
        int sumToPay;
        int k1 = Integer.parseInt((String) request.getSession().getAttribute("carPrice"));
        int k2 = Integer.parseInt((String) request.getParameter("period"));
        LOG.info(k1 + " " + k2);
        sumToPay = k1 * k2;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedate = null;
        try {
            parsedate = formatter.parse((String) request.getParameter("date"));
        } catch (ParseException ex) {
            LOG.info("ParseException while converting date: " + ex);
        }
        java.sql.Date sql = new java.sql.Date(parsedate.getTime());
        OrderDaoImpl orderDao;
        try {
            orderDao = new OrderDaoImpl();
        } catch (DAOException ex) {
            LOG.fatal("Couldn't establish the connection to the database", ex);
            LOG.info("->errorpage");
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        Order appl = new Order(userId, carid, sumToPay, k1, k2, sql);
        try {
            orderDao.create(appl);
            page = ConfigurationManager.getProperty("path.page.order.success");
            return page;
        } catch (DAOException ex) {
            LOG.info("Не получилось добавить заявку в базу данных" + ex);
            page = ConfigurationManager.getProperty("path.page.order.fail");
            return page;
        }

    }

}
