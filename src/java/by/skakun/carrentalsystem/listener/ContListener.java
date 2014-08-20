
package by.skakun.carrentalsystem.listener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 *
 * @author apple
 */
public class ContListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ContListener.class);

    private Driver driver = null;

    /**
     * Registers the Oracle JDBC driver
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            this.driver = new com.mysql.jdbc.Driver(); // load and instantiate the class
        } catch (SQLException ex) {
            LOG.info(ex);
        }
        boolean skipRegistration = false;
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            if (driver instanceof com.mysql.jdbc.Driver) {
                com.mysql.jdbc.Driver alreadyRegistered = (com.mysql.jdbc.Driver) driver;
                if (alreadyRegistered.getClass() == this.driver.getClass()) {
                    // same class in the VM already registered itself
                    skipRegistration = true;
                    this.driver = alreadyRegistered;
                    break;
                }
            }
        }

        try {
            if (!skipRegistration) {
                DriverManager.registerDriver(driver);
            } else {
                LOG.debug("driver was registered automatically");
            }
            LOG.info(String.format("registered jdbc driver: %s v%d.%d", driver,
                    driver.getMajorVersion(), driver.getMinorVersion()));
        } catch (SQLException e) {
            LOG.error(
                    "Error registering oracle driver: " + 
                            "database connectivity might be unavailable!",
                    e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Deregisters JDBC driver
     * 
     * Prevents Tomcat 7 from complaining about memory leaks.
     * @param servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (this.driver != null) {
            try {
                DriverManager.deregisterDriver(driver);
                LOG.info(String.format("deregistering jdbc driver: %s", driver));
            } catch (SQLException e) {
                LOG.warn(
                        String.format("Error deregistering driver %s", driver),
                        e);
            }
            this.driver = null;
        } else {
            LOG.warn("No driver to deregister");
        }

    }



}