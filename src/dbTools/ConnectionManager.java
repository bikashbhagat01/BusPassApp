package dbTools;

import java.sql.*;

// Created Singleton, Only gateway to DB
// Returns Connection Object
public class ConnectionManager {

  private static Connection con;

  public ConnectionManager() { }

  public static Connection getConnection() throws SQLException, ClassNotFoundException {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");

      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/newDb?serverTimezone=UTC",
                                      "root", "");
      return con;
    } catch (Exception e) {
      System.out.println("Connection Issue Found");
      e.printStackTrace();
      throw e;
    }
  }
}

