package com.example.wishingproj.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    private static String username;
    private static String password;
    private static String hostname;
    private static Connection conn;

    public static Connection getConnection(){
      if (conn != null) {
        return conn;
      }

      hostname = "jdbc:mysql://keasqlian.mysql.database.azure.com/wishing_well";
      username = "justdon";
      password = "Gagagaga123";

      try {
        conn = DriverManager.getConnection(hostname, username, password);
      } catch (SQLException e) {
        System.out.println("no connection to DB");
        throw new RuntimeException(e);
      }
      System.out.println("Connection to Database Sucessful.");
      return conn;
    }



  }

