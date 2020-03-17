package dbTools;

import dbTools.QueryExecutor;
import java.sql.SQLException;

public class Validate {

  private static String sqlQuery;

  public static boolean validateUserWithPassword(int userName, String passsword) throws SQLException, ClassNotFoundException {
    // Returns true if User with mentioned password exists
    sqlQuery = "select userid from user where userid=" + userName + "and password =" + passsword;
    return QueryExecutor.getInstance().validateQuery(sqlQuery);
  }

  public static boolean validateUsername(int userName) throws SQLException, ClassNotFoundException {
    // Returns true if user with mentioned User exists
    sqlQuery = "select userid from user where userid=" + userName;
    return QueryExecutor.getInstance().validateQuery(sqlQuery);
  }

  public boolean  validateBusPassPresence(int userName) throws SQLException, ClassNotFoundException {
    // Returns true if user is linked to a BusPass or Not
    sqlQuery = "select buspassid from buspass where userid=" + userName;
    return QueryExecutor.getInstance().validateQuery(sqlQuery);
  }
}
