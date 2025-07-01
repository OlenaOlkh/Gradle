package com.at.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnector {

    private static DataBaseConnector instance;
    private Connection connection;
    private static String userPassword = GetDataBasePassword();
    private String userName = "LB_DEV";

    public static final DataBaseConnector getInstance(String jdbcURL) {
        if (instance == null) {
            instance = new DataBaseConnector(jdbcURL);
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public DataBaseConnector(String jdbcURL) {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(jdbcURL, userName, userPassword);
            System.out.println("Connected: " + jdbcURL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet executeQuery(String queryString) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void examplePrintTopFiveEvents() {
        try {
            ResultSet resultSet = executeQuery("select * from event limit 5");
            int i = 0;
            while (resultSet.next()) {
                System.out.println("#" + i++
                        + " " + resultSet.getString("title")
                        + " " + resultSet.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String exampleGetOneEventIdByAdminEmail(String adminEmail) {
        String eventId = "";
        try {
            ResultSet resultSet = executeQuery(
                    "select events.id, events.title, users.email from event as events inner join \n"
                            + "\tpublic.users as users on events.admin_id = users.id\n"
                            + "where email = '" + adminEmail + "' limit 1");
            while (resultSet.next()) {
                eventId = resultSet.getString("id");
                System.out.println(""
                        + " " + resultSet.getString("title")
                        + " " + eventId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventId;
    }

    private static String GetDataBasePassword() {
        String pwd = System.getProperty("dbPassword");
        if (pwd == null) {
            throw new RuntimeException("You should set system variable -DdbPassword='some db password'");
        }
        return pwd;
    }

}
