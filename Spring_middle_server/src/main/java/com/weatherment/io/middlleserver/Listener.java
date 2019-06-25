package com.weatherment.io.middlleserver;

import com.weatherment.io.middlleserver.Parser.Parser;
import com.weatherment.io.middlleserver.Projections.Temperature;
import com.weatherment.io.middlleserver.Repository.TemperatureRepository;
import javassist.NotFoundException;
import org.json.JSONException;
import org.postgresql.PGNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class Listener {
    @Value("${spring.datasource.url}")
    private String url = "jdbc:postgresql://localhost/weatherment";

    @Autowired
    TemperatureRepository temperatureRepository;

    @Value("${spring.datasource.username}")
    private String username = "postgres";

    @Value("${spring.datasource.password}")
    private String password = "Gunsacdc97";

    // SINGLETON
    private static Listener instance;

    public static Listener getInstance() {
        if (instance == null) {
            instance = new Listener();
        }
        return instance;
    }

    protected Listener() {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            ListenerThread listener = new ListenerThread(connection);
            listener.start();
        } catch (SQLException e) {
            System.out.println("SYSTEM NOTIFICATIONE ERROR");
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("NOTIFICATION SYSTEM STARTED OK");
    }


    class ListenerThread extends Thread {
        private Connection conn;
        private org.postgresql.PGConnection pgconn;

        ListenerThread(Connection conn) throws SQLException {
            this.conn = conn;
            this.pgconn = conn.unwrap(org.postgresql.PGConnection.class);
            Statement stmt = conn.createStatement();
            stmt.execute("LISTEN wmiochannel");
            stmt.close();
        }

        public void run() {
            try {
                System.out.println("Waiting for notification...");
                while (true) {
                    org.postgresql.PGNotification notifications[] = pgconn.getNotifications();
                    if (notifications != null) {
                        for (int i = 0; i < notifications.length; i++)
                            parseNotification(notifications[i]);
                    }
                    Thread.sleep(500);
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                System.out.println("Error in waiting for notification");
                System.exit(0);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
                System.out.println("Error in waiting for notification");
                System.exit(0);
            }
        }
    }

    private void parseNotification(PGNotification notification) {
        System.out.println("Update on notification channel: " + notification.getName());
        System.out.println("Notification message: " + notification.getParameter());

        try {
            Object obj = Parser.getInstance().parse(notification.getParameter());
            if(obj instanceof Temperature) temperatureRepository.save((Temperature) obj);
            System.out.print("Saved new temperature to DB " + (Temperature) obj);
        } catch (NullPointerException e) {
            System.out.println("Unable to parse obj");
            e.printStackTrace();
        } catch (NotFoundException e) {
            System.out.println("Unable to parse obj");
            e.printStackTrace();
        } catch (JSONException e) {
            System.out.println("Unable to parse obj");
            e.printStackTrace();
        }
    }
}
