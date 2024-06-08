package com.rajesh.poc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection getConnection() throws SQLException {
        try {
            String url = "jdbc:mysql://localhost:3306/connpool";
            String user = "pool";
            String password = "pool";
            return DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
