package managers;

import assets.Feedback;

public class FeedbackManager {
  public void create(Feedback feedback) {
    // Creates insert SQL Query from feedback Object details
    // Sends Query to QueryExecutor
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
