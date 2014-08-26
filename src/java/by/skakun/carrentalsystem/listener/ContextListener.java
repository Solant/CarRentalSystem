
package by.skakun.carrentalsystem.listener;

import by.skakun.carrentalsystem.connectionpool.ConnectionPool;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 * 
 * releasing ConnectionPool on close
 */
public class ContextListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // серирализация???

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.releaseConnectionPool(); 
        LOG.info("ConnectionPool is released");
    }



}