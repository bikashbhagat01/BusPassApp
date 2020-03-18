package dbTools;

import dbTools.QueryExecutor;
import java.sql.SQLException;

public class Validate {

  private static String sqlQuery;

  public static boolean isValidUserPassword(int userName, String passsword) throws SQLException, ClassNotFoundException {
    // Returns true if User with mentioned password exists
    sqlQuery = "select userid from user where userid=" + userName + "and password =" + passsword;
    return QueryExecutor.getInstance().validateQuery(sqlQuery);
  }

  public static boolean isValidUser(int userName) throws SQLException, ClassNotFoundException {
    // Returns true if user with mentioned User exists
    sqlQuery = "select userid from user where userid=" + userName;
    return QueryExecutor.getInstance().validateQuery(sqlQuery);
  }

  public static boolean isValidBusPass(int userName) throws SQLException, ClassNotFoundException {
    // Returns true if user is linked to a BusPass or Not
    sqlQuery = "select buspassid from buspass where userid=" + userName;
    return QueryExecutor.getInstance().validateQuery(sqlQuery);
  }
}
