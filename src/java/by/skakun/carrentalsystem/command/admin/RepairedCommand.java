package by.skakun.carrentalsystem.command.admin;

import by.skakun.carrentalsystem.command.ActionCommand;
import by.skakun.carrentalsystem.dao.RepairBillDao;
import by.skakun.carrentalsystem.dao.impl.RepairBillDaoImpl;
import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 *
 * marking the chosen repaibill as paid and getting admin to the page with the
 * rest of unpaid bills
 */
public class RepairedCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(RepairedCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        boolean flag = false;
        RepairBillDao billDao;
        try {
            billDao = new RepairBillDaoImpl();
        } catch (DAOException ex) {
            LOG.fatal("Couldn't establish the connection to the database", ex);
            LOG.debug("->errorpage");
            page = ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        String appl = (String) request.getParameter("applId");
        int applId = Integer.parseInt(appl);
        try {
            flag = billDao.repair(applId);
        } catch (DAOException ex) {
            LOG.error("DAOException while repairedComand: " + ex);
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
