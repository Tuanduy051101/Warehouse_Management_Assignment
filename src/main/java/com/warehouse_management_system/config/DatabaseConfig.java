package com.warehouse_management_system.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL="jdbc:oracle:thin:@localhost:1521/WAREHOUSE_MANAGEMENT_SYSTEM";
    private static final String USER="WAREHOUSE_ASSIGNMENT";
    private static final String PASSWORD="abc";
    private static final String DRIVER_CLASS="oracle.jdbc.driver.OracleDriver";

    // tải driver oracle jdbc
    static {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load Oracle JDBC driver", e);
        }
    }

    // lấy kết nối đến database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
