package com.zdzimi.flashcards.data.common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtils {

    private static String jdbcUrl;
    private static String dbUser;
    private static String dbPassword;

    static {
        try{
            Properties properties = new Properties();
            properties.load(ClassLoader.getSystemResourceAsStream("db.properties"));
            jdbcUrl = properties.getProperty("JDBC_URL");
            dbUser = properties.getProperty("JDBC_USER");
            dbPassword = properties.getProperty("JDBC_PASSWORD");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl,dbUser,dbPassword);
    }
}
