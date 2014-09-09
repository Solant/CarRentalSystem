package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.DaoFactory;
import by.skakun.carrentalsystem.dao.DaoType;
import by.skakun.carrentalsystem.dao.RepairBillDao;
import by.skakun.carrentalsystem.entity.RepairBill;
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
        String page;
        RepairBillDao billDao;
        List<RepairBill> bills;
        try {
            billDao = (RepairBillDao) DaoFactory.getDao(DaoType.REPAIR_BILL);
            bills = billDao.getAll();
        } catch (DAOException ex) {
            LOG.error("DAOException after OrderDao.getRepairBills()" + ex);
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        request.setAttribute("lst", bills);
        LOG.debug("->repairbills");
        page = ConfigurationManager.getProperty("path.page.repairbills");
        return page;
    }

}
