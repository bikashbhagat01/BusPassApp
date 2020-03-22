package dbTools;

import java.sql.*;

// Created Singleton, Only gateway to DB
// Returns Connection Object
public class ConnectDatabase {

  private static Connection con;

  public ConnectDatabase() { }

  public static Connection getConnection() throws SQLException, ClassNotFoundException {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/newDb?serverTimezone=UTC",
                                      "root", "");

      return con;
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }
}

