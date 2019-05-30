package com.weatherment.io.middlleserver;

// NOTIFICATION LISTENER MIDDLE SERVER TEST

import java.sql.*;

public class TestNotificationListener {
    public static void main(String args[]) throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/test";

        // Create two distinct connections, one for the notifier
        // and another for the listener to show the communication
        // works across connections although this example would
        // work fine with just one connection.
        Connection lConn = DriverManager.getConnection(url, "test", "");
        Connection nConn = DriverManager.getConnection(url, "test", "");

        // Create two threads, one to issue notifications and
        // the other to receive them.
        ListenerTest listener = new ListenerTest(lConn);
        NotifierTest notifier = new NotifierTest(nConn);
        listener.start();
        notifier.start();
    }

}

class ListenerTest extends Thread {

    private Connection conn;
    private org.postgresql.PGConnection pgconn;

    ListenerTest(Connection conn) throws SQLException {
        this.conn = conn;
        this.pgconn = (org.postgresql.PGConnection) conn;
        Statement stmt = conn.createStatement();
        stmt.execute("LISTEN mymessage");
        stmt.close();
    }

    public void run() {
        while (true) {
            try {
                // issue a dummy query to contact the backend
                // and receive any pending notifications.
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT 1");
                rs.close();
                stmt.close();

                org.postgresql.PGNotification notifications[] = pgconn.getNotifications();
                if (notifications != null) {
                    for (int i = 0; i < notifications.length; i++) {
                        System.out.println("Got notification: " + notifications[i].getName());
                    }
                }

                // wait a while before checking again for new
                // notifications
                Thread.sleep(500);
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

}

class NotifierTest extends Thread {

    private Connection conn;

    public NotifierTest(Connection conn) {
        this.conn = conn;
    }

    public void run() {
        while (true) {
            try {
                Statement stmt = conn.createStatement();
                stmt.execute("NOTIFY mymessage");
                stmt.close();
                Thread.sleep(2000);
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

}