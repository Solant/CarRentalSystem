package by.skakun.carrentalsystem.connectionpool;

import by.skakun.carrentalsystem.exception.DAOException;
import by.skakun.carrentalsystem.manager.ConfigurationManager;

import java.sql.*;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;

public final class ConnectionPool {

    private static final Logger LOG = Logger.getLogger(ConnectionPool.class);
    public static ConnectionPool INSTANCE;
    private static final BlockingQueue<Connection> connections = new LinkedBlockingQueue<>();
    private final static String DATABASE_URL = ConfigurationManager.getProperty("db.url");
    private final static String DATABASE_LOGIN = ConfigurationManager.getProperty("db.user");
    private final static String DATABASE_PASSWORD = ConfigurationManager.getProperty("db.password");
    private final static int CONNECTIONS_QUANTITY = Integer.parseInt(ConfigurationManager.getProperty("db.maxpool"));
    private final static String DATABASE_DRIVER = ConfigurationManager.getProperty("db.driver");
    private final static int DATABASE_WAIT = Integer.parseInt(ConfigurationManager.getProperty("db.idleTimeout"));
    private static boolean flag = true;

    private ConnectionPool() {
        LOG.info("We are in the ConnectionPool.");
        Connection connection;
        if (flag) {
            try {
                flag = false;
                Properties p = new Properties();
                p.setProperty("user", DATABASE_LOGIN);
                p.setProperty("password", DATABASE_PASSWORD);
                p.setProperty("useUnicode", "true");
                p.setProperty("characterEncoding", "utf-8");
                Class.forName(DATABASE_DRIVER).newInstance();
                for (int i = 0; i < CONNECTIONS_QUANTITY; i++) {
                    connection = DriverManager.getConnection(DATABASE_URL, p);
                    if (connection != null) {
                        connections.add(connection);
                        LOG.info("Connection #" + i + "  with the database established.");
                    }
                }
            } catch (ClassNotFoundException | SQLException ex) {
                LOG.warn("Couldn't establish the connection with the db while creating the  ConnectionPool. " + ex);
            } catch (InstantiationException | IllegalAccessException ex) {
                LOG.warn("Connection pool exception:" + ex);
            }
        }
    }

    public static ConnectionPool getInstance() {
        LOG.info("ConnectionPool.getInstance()");
        ReentrantLock lock = new ReentrantLock();
        if (INSTANCE == null) {
            lock.lock();
            try {
                if (INSTANCE == null) {
                    INSTANCE = new ConnectionPool();
                }
            } finally {
                lock.unlock();
            }
        }
        return INSTANCE;
    }

    public static Connection getConnection() throws DAOException {
        LOG.info("ConnectionPool.getConnection()");
        Connection connection = null;
        try {
            connection = getInstance().connections.poll(DATABASE_WAIT, TimeUnit.MILLISECONDS);
            if(connection==null) {
                throw new DAOException("There are no more available connections to the database");
            }
        } catch (InterruptedException ex) {
            LOG.error("Couldn't establish connection to the db. " + ex);
        }
        return connection;
    }

    public static void returnConnection(Connection connection) {
        try {
            connections.put(connection);
        } catch (InterruptedException ex) {
            LOG.info("Exception while returning the connection. " + ex);
        }
    }
}
