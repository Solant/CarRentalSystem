package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.connectionpool.ConnectionPool;
import by.skakun.carrentalsystem.dao.impl.RepairBillDaoImpl;
import by.skakun.carrentalsystem.entity.Order;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.manager.ConfigurationManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class RepairBillsCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(RepairBillsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        RepairBillDaoImpl billDao = new RepairBillDaoImpl(ConnectionPool.getConnection());
        List<Order> bills = null;
        try {
            bills = billDao.getAll();
        } catch (DAOException ex) {
            LOG.info("DAOException after applicationDaoImpl.getRepairBills()" + ex);
        }
        request.setAttribute("lst", bills);
        LOG.info("->repairbills");
        String page = ConfigurationManager.getProperty("path.page.repairbills");
        return page;
    }

}

