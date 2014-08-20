package by.skakun.carrentalsystem.command.user;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.connectionpool.ConnectionPool;
import by.skakun.carrentalsystem.dao.impl.OrderDaoImpl;
import by.skakun.carrentalsystem.exception.DAOException;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class PayCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(PayCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        LOG.info("PayCommand");
        boolean flag = false;
        String idA = (String) request.getParameter("applid");
        int id = Integer.parseInt(idA);
        OrderDaoImpl applDao = new OrderDaoImpl(ConnectionPool.getConnection());
        try {
            flag = applDao.pay(id);
        } catch (DAOException ex) {
            LOG.info("DAOException while PayCommand: " + ex);
        }
        if (flag) {
            request.setAttribute("success", "1");

        } else {
            request.setAttribute("fail", "1");

        }
        page = new BasketCommand().execute(request);
        return page;
    }
}
