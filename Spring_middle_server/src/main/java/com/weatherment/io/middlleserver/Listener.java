package com.weatherment.io.middlleserver;

import org.postgresql.PGConnection;
import org.postgresql.jdbc.PgConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class Listener {
    @Value("${spring.datasource.url}")
    private String url = "jdbc:postgresql://localhost/concessionarie";

    @Value("${spring.datasource.username}")
    private String username = "postgres" ;

    @Value("${spring.datasource.password}")
    private String password = "Gunsacdc97";

    public Listener() {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            ListenerThread listener = new ListenerThread(connection);
            listener.start();
        } catch(SQLException e) {
            System.out.println("SYSTEM NOTIFICATIONE ERROR");
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("NOTIFICATION SYSTEM STARTED OK");
    }



    class ListenerThread extends Thread
    {
        private Connection conn;
        private org.postgresql.PGConnection pgconn;

        ListenerThread(Connection conn) throws SQLException
        {
            this.conn = conn;
            this.pgconn = conn.unwrap(org.postgresql.PGConnection.class);
            Statement stmt = conn.createStatement();
            stmt.execute("LISTEN mymessage");
            stmt.close();
        }

        public void run()
        {
            try
            {
                System.out.println("Waiting for notification...");
                while (true)
                {
                    org.postgresql.PGNotification notifications[] = pgconn.getNotifications();
                    if (notifications != null)
                    {
                        for (int i=0; i < notifications.length; i++)
                            System.out.println("Got notification: " + notifications[i].getName());
                    }
                    Thread.sleep(500);
                }
            }
            catch (SQLException sqle)
            {
                sqle.printStackTrace();
                System.out.println("Error in waiting for notification");
                System.exit(0);
            }
            catch (InterruptedException ie)
            {
                ie.printStackTrace();
                System.out.println("Error in waiting for notification");
                System.exit(0);
            }
        }
    }
}
