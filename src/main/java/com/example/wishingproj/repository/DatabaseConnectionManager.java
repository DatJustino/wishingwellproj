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

      setHostname("jdbc:mysql://keasqlian.mysql.database.azure.com/wishing_well");
      setUsername("justdon");
      setPassword("Gagagaga123");

      try {
        conn = DriverManager.getConnection(getHostname(), getUsername(), getPassword());
      } catch (SQLException e) {
        System.out.println("no connection to DB");
        throw new RuntimeException(e);
      }
      System.out.println("Connection to Database Sucessful.");
      return conn;
    }


  public static String getUsername() {
    return username;
  }

  public static void setUsername(String username) {
    DatabaseConnectionManager.username = username;
  }

  public static String getPassword() {
    return password;
  }

  public static void setPassword(String password) {
    DatabaseConnectionManager.password = password;
  }

  public static String getHostname() {
    return hostname;
  }

  public static void setHostname(String hostname) {
    DatabaseConnectionManager.hostname = hostname;
  }
}

