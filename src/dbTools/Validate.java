package dbTools;

import dbTools.QueryExecutor;
import java.sql.SQLException;

public class Validate {

  private static String sqlQuery;
  private static int PASSWORD_LENGTH = 8;
  public static boolean isValidUserPassword(int userName, String password) throws SQLException, ClassNotFoundException {
    // Returns true if User with mentioned password exist
    sqlQuery = "select userid from user where userid=" + userName + "and password =" + password;
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

  public static boolean isValidStopName(String stopName) throws SQLException, ClassNotFoundException {
    // Returns true if stopName is linked to a stopId or Not
    sqlQuery = "select stopid from stop where stopname=" + stopName;
    return QueryExecutor.getInstance().validateQuery(sqlQuery);
  }

  public static boolean isPresent(String tableName, String fieldName, int fieldValue)
          throws SQLException, ClassNotFoundException {
    // Returns true if the table contains a fieldValue for the mentioned column in the table
    sqlQuery = "select * from " + tableName + " where " + fieldName + "= " + fieldValue;
    return QueryExecutor.getInstance().validateQuery(sqlQuery);
  }

  public static boolean isPresent(String tableName, String fieldName, String fieldValue)
          throws SQLException, ClassNotFoundException {
    // Returns true if the table contains a fieldValue for the mentioned column in the table
    sqlQuery = "select * from " + tableName + " where " + fieldName + "= " + "\'" +
            fieldValue + "\'";
    return QueryExecutor.getInstance().validateQuery(sqlQuery);
  }
  public static boolean isValidPassword(String password) {

    if (password.length() < PASSWORD_LENGTH) return false;

    int charCount = 0;
    int numCount = 0;
    for (int i = 0; i < password.length(); i++) {

      char ch = password.charAt(i);

      if (is_Numeric(ch)) numCount++;
      else if (is_Letter(ch)) charCount++;
      else return false;
    }


    return (charCount >= 2 && numCount >= 2);
  }
  public static boolean is_Letter(char ch) {
    ch = Character.toUpperCase(ch);
    return (ch >= 'A' && ch <= 'Z');
  }


  public static boolean is_Numeric(char ch) {

    return (ch >= '0' && ch <= '9');
  }

}


/**  "1. A password must have at least eight characters.\n" +
     "2. A password consists of only letters and digits.\n" +
     "3. A password must contain at least two digits \n" +
     "Input a password (You are agreeing to the above Terms and Conditions.):
 */