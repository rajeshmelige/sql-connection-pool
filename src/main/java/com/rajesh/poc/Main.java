package com.rajesh.poc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws InterruptedException, SQLException {

        //executeWithRawDbConnection();
        executeWithConnectionPool();
    }

    private static void executeWithRawDbConnection() throws InterruptedException {
        long begin = System.currentTimeMillis();
        List<Thread> threadList = new ArrayList<>();
        for(int i=0; i<200; i++) {
            Thread thread = new Thread(() -> new DB().executeWithNewConnection());
            thread.start();
            threadList.add(thread);
        }
        for(Thread t: threadList) {
            t.join();
        }
        long end = System.currentTimeMillis();
        System.out.println("total time taken in ms: " +(end - begin));
    }

    private static void executeWithConnectionPool() throws InterruptedException, SQLException {
        Queue<Connection> connections = new LinkedList<>();
        ConnectionPool connectionPool = new ConnectionPool(connections);
        for(int i=0; i<100; i++) {
            connectionPool.addConnection();
        }
        List<Thread> threadList = new ArrayList<>();
        long begin = System.currentTimeMillis();
        for(int i=0; i<300; i++) {
            Thread thread = new Thread(() -> new DB(connectionPool).executeWithConnectionPool());
            thread.start();
            threadList.add(thread);
        }
        for(Thread t: threadList) {
            t.join();
        }
        long end = System.currentTimeMillis();
        System.out.println("total time taken in ms: " +(end - begin));
    }
}