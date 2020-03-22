package dbTools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class Validator {

  private static String sqlQuery;

  private static int PASSWORD_LENGTH = 8;

  // Returns true if User with mentioned password exist
  public static boolean isValidUserPassword(int userId, String password) throws SQLException, ClassNotFoundException {
    sqlQuery = "select userid from user where userid = " + userId + " and password = \'" + password + "\';";
    return QueryExecutor.getInstance().isValidQuery(sqlQuery);
  }

  // Returns true if user with mentioned User exists
  public static boolean isValidUser(int userName) throws SQLException, ClassNotFoundException {
    sqlQuery = "select userid from user where userid=" + userName;
    return QueryExecutor.getInstance().isValidQuery(sqlQuery);
  }

  // Returns true if user is linked to a BusPass or Not
  public static boolean isValidBusPass(int userName) throws SQLException, ClassNotFoundException {
    sqlQuery = "select buspassid from buspass where userid = " + userName + ";";
    return QueryExecutor.getInstance().isValidQuery(sqlQuery);
  }

  // Returns true if stopName is linked to a stopId or Not
  public static boolean isValidStopName(String stopName) throws SQLException, ClassNotFoundException {
    sqlQuery = "select stopid from stop where stopname=" + stopName;
    return QueryExecutor.getInstance().isValidQuery(sqlQuery);
  }

  // Returns true if the table contains a fieldValue for the mentioned column in the table
  public static boolean isPresent(String tableName, String fieldName, int fieldValue)
          throws SQLException, ClassNotFoundException {
    sqlQuery = "select * from " + tableName + " where " + fieldName + "= " + fieldValue;
    return QueryExecutor.getInstance().isValidQuery(sqlQuery);
  }

  // Returns true if the table contains a fieldValue for the mentioned column in the table
  public static boolean isPresent(String tableName, String fieldName, String fieldValue)
          throws SQLException, ClassNotFoundException {
    sqlQuery = "select * from " + tableName + " where " + fieldName + "= " + "\'" +
            fieldValue + "\'";
    return QueryExecutor.getInstance().isValidQuery(sqlQuery);
  }

  public static boolean isValidPassword(String password) {

    if (password.length() < PASSWORD_LENGTH) {
      return false;
    }

    int charCount = 0;
    int numCount = 0;

    for (int i = 0; i < password.length(); i++) {
      char ch = password.charAt(i);

      if (is_Numeric(ch)) {
        numCount++;
      } else if (is_Letter(ch)) {
        charCount++;
      } else {
        return false;
      }
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

  public static boolean isValidComment(String comment) {
    if (comment.length() <= 100 && comment.length() > 1) {
      return true;
    }
    return false;
  }

  public static boolean isCommentBlank(String comment) {
    if (comment.trim().equals("")) {
      return true;
    }
    return false;
  }

  public static boolean isBusAvailableForRoutesAndTiming(int[] routeIds, int timing)
          throws SQLException, ClassNotFoundException {
    if(routeIds.length == 0) {
      return false;
    }

    for(int routeId : routeIds) {
      sqlQuery = "select * from bus where routeId = " + routeId + " and timing = " + timing +
              " and availability <= bustype;";

      ResultSet resultSet = QueryExecutor.getInstance().getResultSet(sqlQuery);

      if(resultSet.next()) {
        return true;
      }
    }
    return false;
  }

  public static boolean isValidEmail(String emailId) {
    if (emailId == null) {
      return false;
    }

    String emailRegex = "^[a-zA-Z0-9]+" +
                        "([a-zA-Z0-9_+&-]+)@" +
                        "([a-zA-Z0-9-]+\\.)+[a-z" +
                        "A-Z]{2,7}$";

    Pattern pat = Pattern.compile(emailRegex);

    return pat.matcher(emailId).matches();
  }
}

/**  "1. A password must have at least eight characters.\n" +
     "2. A password consists of only letters and digits.\n" +
     "3. A password must contain at least two digits \n" +
     "Input a password (You are agreeing to the above Terms and Conditions.):
 */