package com.book.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class C3P0Utils {

    private static DataSource dataSource = new ComboPooledDataSource();

    public static DataSource getDataSourse(){
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closeAll(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
        if(conn != null){
            conn.close();
        }
        if(stmt != null){
            stmt.close();
        }
        if(rs != null){
            rs.close();
        }
    }

}
