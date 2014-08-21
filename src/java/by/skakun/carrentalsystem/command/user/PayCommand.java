package by.skakun.carrentalsystem.command.user;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.impl.OrderDaoImpl;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 * 
 * user paying for the order
 */
public class PayCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(PayCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        LOG.info("PayCommand");
        boolean flag = false;
       String idO = (String) request.getParameter("applid");
        String idS = (String) request.getParameter("userId");
        int id = Integer.parseInt(idS);
        int idOr = Integer.parseInt(idO);
        String sumO = (String) request.getParameter("sumToPay");
        int sum = Integer.parseInt(sumO);
        OrderDaoImpl orderDao;
        try {
            orderDao = new OrderDaoImpl();
        } catch (DAOException ex) {
            LOG.fatal("Couldn't establish the connection to the database", ex);
            LOG.info("->errorpage");
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        try {
            flag = orderDao.pay(id, idOr,sum);
        } catch (DAOException ex) {
            LOG.error("DAOException while PayCommand: " + ex);
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
