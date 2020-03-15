package dbTools;

import java.sql.*;

// Created Singleton, Only gateway to DB
// Returns Connection Object
public class ConnectDatabase {
  private static Connection con;
  public ConnectDatabase(){}

  public static Connection getConnection() throws SQLException, ClassNotFoundException {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_mgmt_db?serverTimezone=UTC", "root", "");
//      Statement statement = con.createStatement();
//      ResultSet rs = statement.executeQuery("show databases;");
//      System.out.println(rs);
      return con;
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }
}

