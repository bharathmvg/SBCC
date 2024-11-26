package com.sbcc.dao;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class DBHandler {
    private static Connection con = null;
    private static Properties p = new Properties();

    public static Connection getConnection() {
        String fileName = "C:\\Users\\Bharath\\IdeaProjects\\SBCC\\src\\database.properties";

        try(FileReader fr = new FileReader(fileName)) {
            p.load(fr);
            String driver = p.getProperty("DB_DRIVER_CLASS");
            String url = p.getProperty("DB_URL");
            String username = p.getProperty("DB_USERNAME");
            String password = p.getProperty("DB_PASSWORD");

            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        } catch(IOException | SQLException | ClassNotFoundException e) {
            e.getStackTrace();
        }

        return con;
    }
}
