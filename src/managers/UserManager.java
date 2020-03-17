package managers;
import assets.User;
import dbTools.IdGenerator;

public class UserManager {
  public static void create(User user) {
  /* Create sqlQuery to insert user details from user object
  *  Send the sqlQuery to QueryExecutor,executeQuery(sqlQuery)
  * */
  }

  public void update(int userId, String[] fields, String[] newValues) {
    /* Create query to update the listed fields of the userId
    *  send the query to QueryExecutor.executeQuery(sqlQuery)
    * */
  }

  public void read() {
    // No requirements found

  }

  public void delete() {
    // No requirements found
  }
}
