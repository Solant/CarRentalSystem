package by.skakun.carrentalsystem.command.user;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.DaoFactory;
import by.skakun.carrentalsystem.dao.DaoType;
import by.skakun.carrentalsystem.dao.OrderDao;
import by.skakun.carrentalsystem.entity.Order;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import java.util.List;
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
        boolean flag;
        List<Order> orders;

        String idOrderS = (String) request.getParameter("applid");
        String idUserS = (String) request.getParameter("userId");
        int idUser = Integer.parseInt(idUserS);
        int idOrder = Integer.parseInt(idOrderS);
        String sumO = (String) request.getParameter("sumToPay");
        int sum = Integer.parseInt(sumO);
        OrderDao orderDao;

        try {
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            flag = orderDao.pay(idUser, idOrder, sum);
            if (flag) {
                request.setAttribute("success", "1");
            } else {
                request.setAttribute("fail", "1");
            }
            orderDao = (OrderDao) DaoFactory.getDao(DaoType.ORDER);
            orders = orderDao.getUByUserId(idUser);
            request.setAttribute("lst", orders);
            page = ConfigurationManager.getProperty("path.page.basket");
        } catch (DAOException ex) {
            LOG.error("DAOException while PayCommand: " + ex);
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}
