package mytranslator.login;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import mytranslator.databasemanagement.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginListener implements ServletContextListener {
    /**
     * context listener class for database connection
     */

    private static final Logger LOGGER = LogManager.getLogger(LoginListener.class);
    Connection connection =null;
    ComboPooledDataSource comboPooledDataSource = Database.getDataSource();

    public void contextInitialized(ServletContextEvent event){

        LOGGER.trace("Opening connectin pool..");

        try {
            connection=comboPooledDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Database.connectDatabase();
        try {
            Database.getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent arg1) {

        LOGGER.trace("Closing connection pool..");
        Database.getDataSource().close();
    }

}