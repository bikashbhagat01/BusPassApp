package managers;

import assets.Feedback;
import dbTools.QueryExecutor;
import queryHelper.QueryBuilder;

public class FeedbackManager {
  private static String sqlQuery;
  public static void create(Feedback feedback) throws Exception {
    // Creates insert SQL Query from feedback Object details
    // Sends Query to QueryExecutor
//    sqlQuery="insert into feedback(feedbackid, comment, user) VALUES(" + feedback.getFeedbackId() +
//            "comment" + feedback.getComment() +
//            "userid" + feedback.getUserId() + ");";

    sqlQuery = QueryBuilder
                .getInstance()
                .Insert()
                .InsertValue("feedbackid", feedback.getFeedbackId())
                .InsertValue("comment", feedback.getComment())
                .InsertValue("userid", feedback.getUserId())
                .FromTable("feedback")
                .build();

    QueryExecutor.getInstance().executeSQL(sqlQuery);

    System.out.println("Feedback is updated\n");
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
