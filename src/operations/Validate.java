package operations;

import dbTools.QueryExecutor;
import java.sql.SQLException;

public class Validate {

  public static boolean validateUserPassword(int userName, String passsword) throws SQLException, ClassNotFoundException {
    /*Define query to find record with provided Username and password from usertable
    * pass query to QueryExecutor.validateQuery()
    * if record found return true
    * */
    String sql = "select userid from user where userid=" + userName + "and password =" + passsword;

    return QueryExecutor.getInstance().validateQuery(sql);
  }

  public static boolean validateUsername(int userName) throws SQLException, ClassNotFoundException {
    /*Define query to find Username and password from usertable
    * pass query to QueryExecutor.validateQuery()
     * if record found return true
     * */
    String sql = "select userid from user where userid=" + userName;

    return QueryExecutor.getInstance().validateQuery(sql);
  }
  public boolean  validateBusPassPresence(int userName){
    /* create query to find userId field value from busPass table
    * pass query to QueryExecutor.validateQuery()
    * if user found return true
    * */
    return false;
  }
}
