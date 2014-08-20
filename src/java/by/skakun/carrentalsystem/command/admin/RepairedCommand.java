package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.connectionpool.ConnectionPool;
import by.skakun.carrentalsystem.dao.impl.OrderDaoImpl;
import by.skakun.carrentalsystem.dao.impl.RepairBillDaoImpl;
import by.skakun.carrentalsystem.exception.DAOException;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author apple
 */
public class RepairedCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(RepairedCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        boolean flag=false;
        try {
            RepairBillDaoImpl billDao = new RepairBillDaoImpl(ConnectionPool.getConnection());
            String appl = (String) request.getParameter("applId");
            int applId = Integer.parseInt(appl);
            flag = billDao.repair(applId);
        } catch (DAOException ex) {
            LOG.info("DAOException while repairedComand: " + ex);
        }
        if (flag) {
            request.setAttribute("csuccess", "1");
            page = new RepairBillsCommand().execute(request);

        } else {
            request.setAttribute("cfail", "1");
            page = new RepairBillsCommand().execute(request);
        }
        LOG.info("->repairment");
        return page;

    }

}
