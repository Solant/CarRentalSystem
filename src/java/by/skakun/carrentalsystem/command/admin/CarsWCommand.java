
package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.connectionpool.ConnectionPool;
import by.skakun.carrentalsystem.dao.ClientDao;
import by.skakun.carrentalsystem.dao.impl.ClientDaoImpl;
import by.skakun.carrentalsystem.entity.Client;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.manager.ConfigurationManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author apple
 */
public class CarsWCommand implements ActionCommand{
    private static final Logger LOG = Logger.getLogger(CarsWCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("->carsW");
        String page = ConfigurationManager.getProperty("path.page.carsadmin");
        return page;
    
    }
    
}
