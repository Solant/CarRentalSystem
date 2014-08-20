package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.connectionpool.ConnectionPool;
import by.skakun.carrentalsystem.dao.impl.CarDaoImpl;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 */
public class ChangeCarImageCommand implements ActionCommand {
    
    private static final Logger LOG = Logger.getLogger(ChangeCarImageCommand.class);
    
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        boolean flag;
        String carid = (String) request.getParameter("carid");
        int id = Integer.parseInt(carid);
        String carimage = (String) request.getParameter("newimage");
        carimage = "img/car/".concat(carimage).concat(".jpg");
        CarDaoImpl carDao = new CarDaoImpl(ConnectionPool.getConnection());
        try {
            flag = carDao.changeCarimage(carimage, id);
            
            if (flag) {
                request.setAttribute("isuccess", "1");
                page = ConfigurationManager.getProperty("path.page.carchange");
                return page;
            } else {
                request.setAttribute("ifail", "1");
                page = ConfigurationManager.getProperty("path.page.carchange");
                return page;
            }
        } catch (DAOException ex) {
            LOG.info("DAOException while ChangeCarImageCommand", ex);
            page = ConfigurationManager.getProperty("path.page.carchange");
            return page;
        }
        
    }
    
}
