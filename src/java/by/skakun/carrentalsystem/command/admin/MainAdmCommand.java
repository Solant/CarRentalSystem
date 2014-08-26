package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.CarDao;
import by.skakun.carrentalsystem.dao.ClientDao;
import by.skakun.carrentalsystem.dao.OrderDao;
import by.skakun.carrentalsystem.dao.impl.CarDaoImpl;
import by.skakun.carrentalsystem.dao.impl.ClientDaoImpl;
import by.skakun.carrentalsystem.dao.impl.OrderDaoImpl;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 * presents main page to admin
 */
public class MainAdmCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(MainAdmCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("maincommand");
        OrderDao orderDao = null;
        CarDao carDao = null;
        ClientDao clientDao = null;
        List list = null;
        List listU = null;
        List listC = null;
        try {
            orderDao = new OrderDaoImpl();
            carDao = new CarDaoImpl();
            clientDao = new ClientDaoImpl();
        } catch (DAOException ex) {
            LOG.error(ex);
        }
        try {
            list =  orderDao.getNewOrders();
            listU = clientDao.getAll();
            listC = carDao.getAll();

        } catch (DAOException ex) {
            LOG.error("DAOException while MainAdmCommand", ex);
        }
        request.setAttribute("rw", list.size());
        request.setAttribute("us", listU.size());
        request.setAttribute("car", listC.size());
        String page = ConfigurationManager.getProperty("path.page.admin");
        LOG.debug("->main_admin.jsp");
        return page;

    }

}
