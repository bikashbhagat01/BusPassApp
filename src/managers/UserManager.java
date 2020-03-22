package managers;
import assets.User;
import dbTools.QueryExecutor;
import java.sql.SQLException;
import queryHelper.QueryBuilderManager;

/**Simplify Sql Query Creation**/
public class UserManager {
  private static String sqlQuery;
  public static void create(User user) throws SQLException, ClassNotFoundException {
  // Extracts Details from User Object, create InsertSQL and sends to Execute
    String quote = "\'";
    String userValues = quote + user.getEmployeeId() + quote + ","
            + quote + user.getFirstName() + quote + ","
            + quote + user.getLastName() + quote + "," + quote + user.getEmail() + quote + "," +
            quote + user.getContactNo() + quote + "," + quote + user.getEmergencyContactNo() + quote
            + "," + quote + user.getEmergencyContactName() + quote + "," +
            quote + user.getBloodGroup() + quote + "," +
            quote + user.getPassword() + quote;
    sqlQuery =  "insert into user (userid , fname, lname, email, " +
                "contactno, emergencycontactno, emergencycontactname, " +
                "bloodgroup, password) " +
                "values (" + userValues + ");";
    QueryExecutor.getInstance().executeSQL(sqlQuery);
  }

  public static void update(int employeeId, String field, String newValue)
          throws SQLException, ClassNotFoundException {
    /* Updates field with new value */
    sqlQuery = "update user set " + field + "= " + newValue + " where userid = " + employeeId;
//    sqlQuery = QueryBuilderManager
//            .getNewQueryBuilderInstance()
//            .Update()
//            .UpdateValue(field,newValue)
//            .WhereEq("userid",employeeId)
//            .FromTable("user")
//            .build();

    QueryExecutor.getInstance().executeSQL(sqlQuery);
  }

  public void read() {
    // No requirements found

  }

  public void delete() {
    // No requirements found
  }
}