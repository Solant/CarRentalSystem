
package by.skakun.carrentalsystem.listener;

import by.skakun.carrentalsystem.connectionpool.ConnectionPool;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Skakun
 */
public class ContListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ContListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.releaseConnectionPool();
    }



}