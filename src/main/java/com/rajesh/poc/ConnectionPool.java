package com.rajesh.poc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Queue;

public class ConnectionPool {

    private final Queue<Connection> connections;

    public ConnectionPool(Queue<Connection> connections) {
        this.connections = connections;
    }

    public void addConnection() throws SQLException {
        connections.add(ConnectionUtil.getConnection());
    }
    public Connection getConnection() throws InterruptedException {
        synchronized (this) {
            while (connections.isEmpty()) {
                wait();
            }
            return connections.poll();
        }
    }

    public void putConnection(Connection connection) {
        synchronized (this) {
            connections.add(connection);
            notify();
        }
    }
}
