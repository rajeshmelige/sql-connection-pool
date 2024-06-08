package com.rajesh.poc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB {
    private ConnectionPool connectionPool;

    public DB() {
    }
    
    public DB(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
    public void executeWithNewConnection() {
        try {
            Connection connection = ConnectionUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT SLEEP(0.01)");
            while (resultSet.next()) {
                System.out.println("successfully executed query");
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            System.out.println("caught exception in executeWithNewConnection method");
            ex.printStackTrace();
        }
    }

    public void executeWithConnectionPool() {
        try {
            Connection connection = connectionPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT SLEEP(0.01)");
            while (resultSet.next()) {
                System.out.println("successfully executed query");
            }
            resultSet.close();
            statement.close();
            connectionPool.putConnection(connection);
        } catch (Exception ex) {
            System.out.println("caught exception in executeWithNewConnection method");
            ex.printStackTrace();
        }
    }
}
