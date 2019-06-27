package com.book.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection(){
        Connection connection = connections.get();
        if(connection == null){
            try {
                connections.set(C3P0Utils.getConnection());
                connection = connections.get();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void startTransaction(){
        if(connections.get() == null){
            try {
                connections.set(C3P0Utils.getConnection());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            connections.get().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commitTransaction() {
        try {
            connections.get().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollbackTransaction(){
        try {
            connections.get().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static ThreadLocal<Connection> connections = new ThreadLocal<>();
}
