package managers;

import assets.Feedback;
import dbTools.QueryExecutor;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackManager {
  private static String sqlQuery;
  public static void create(Feedback feedback) throws SQLException, ClassNotFoundException {
    // Creates insert SQL Query from feedback Object details
    // Sends Query to QueryExecutor
    sqlQuery="insert into feedback"+feedback.getFeedbackId()+"comment"+feedback.getComment()+
            "user Id"+feedback.getUserId();
    QueryExecutor.getInstance().executeSQL(sqlQuery);
    System.out.println("Feedback is updated\n");
  }

  public void update(int feedbackId) {
    // Not requirements found
  }

  public void read() {
    /*Create Query to read all feedback
     * Create array of fields to be read
     * call queryExecutor(sqlQuery,fields[])
     * */
  }

  public void delete(int feedbackId) {
    // No requirements found
  }
}
