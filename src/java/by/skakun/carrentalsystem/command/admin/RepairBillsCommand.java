package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.RepairBillDao;
import by.skakun.carrentalsystem.dao.impl.RepairBillDaoImpl;
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
 * gets admin to page with all unpaid repair bills
 */
public class RepairBillsCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(RepairBillsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        RepairBillDao billDao;
        try {
            billDao = new RepairBillDaoImpl();
        } catch (DAOException ex) {
            LOG.fatal("Couldn't establish the connection to the database", ex);
            LOG.debug("->errorpage");
            String page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        List<Order> bills = null;
        try {
            bills = billDao.getAll();
        } catch (DAOException ex) {
            LOG.error("DAOException after OrderDao.getRepairBills()" + ex);
        }
        request.setAttribute("lst", bills);
        LOG.debug("->repairbills");
        String page = ConfigurationManager.getProperty("path.page.repairbills");
        return page;
    }

}
