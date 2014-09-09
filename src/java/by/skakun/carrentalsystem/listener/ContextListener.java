
package by.skakun.carrentalsystem.listener;

import by.skakun.carrentalsystem.connectionpool.ConnectionPool;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 * 
 * releasing ConnectionPool when context is being destroyed
 */
public class ContextListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // todo some actions

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.releaseConnectionPool(); 
        LOG.info("ConnectionPool is released");
    }



}