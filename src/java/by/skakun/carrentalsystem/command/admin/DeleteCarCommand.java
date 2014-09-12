package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.CarDao;
import by.skakun.carrentalsystem.dao.DaoFactory;
import by.skakun.carrentalsystem.dao.DaoType;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 *
 * deletes car / not recommended
 */
public class DeleteCarCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(DeleteCarCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        CarDao carDao;
        boolean flag;
        try {
            carDao = (CarDao) DaoFactory.getDao(DaoType.CAR);
            int id = Integer.parseInt((String) request.getParameter("carid"));
            flag = carDao.deleteCar(id);
        } catch (DAOException ex) {
            LOG.error("DAOException after clientDao.deleteUser(id)." + ex);
            page = ConfigurationManager.getProperty("path.page.carchange");
            request.setAttribute("dfail", "1");
            return page;
        }
        if (flag) {
            request.setAttribute("dsuccess", "1");
        } else {
            request.setAttribute("dfail", "1");
        }
        page = ConfigurationManager.getProperty("path.page.carchange");
        return page;

    }

}
