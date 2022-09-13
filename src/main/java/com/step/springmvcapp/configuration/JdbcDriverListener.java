package com.step.springmvcapp.configuration;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener 
public class JdbcDriverListener implements ServletContextListener { //ServletContextListener — увед-ние о жизненном цикле ServletContext 

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // nothing to do
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //Enumeration устаревший интерфейс был заменён Iterator.
        Enumeration<Driver> drivers = DriverManager.getDrivers();     

        Driver driver = null;
//Eсли веб-приложение имеет несколько подключений к базе данных, вы можете закрыть их вместе
        // clear drivers
        while(drivers.hasMoreElements()) {
            try {
                driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            } catch (SQLException ex) {
                // deregistration failed, might want to do something, log at the very least
            }
        }
        // MySQL driver leaves around a thread. This static method cleans it up.
            AbandonedConnectionCleanupThread.checkedShutdown();
    }
    
}
