package managers;

import assets.Feedback;
import customExceptions.ApplicationException;
import dbTools.QueryExecutor;
import queryHelper.QueryBuilder;

public class FeedbackManager extends BaseManager{

  private static FeedbackManager feedbackManager;

  public static FeedbackManager getInstance() {
    if(feedbackManager == null) {
      feedbackManager = new FeedbackManager();
    }
    return feedbackManager;
  }

  public void create(Feedback feedback) throws ApplicationException {
    // Creates insert SQL Query from feedback Object details
    // Sends Query to QueryExecutor
//    sqlQuery="insert into feedback(feedbackid, comment, user) VALUES(" + feedback.getFeedbackId() +
//            "comment" + feedback.getComment() +
//            "userid" + feedback.getUserId() + ");";


    QueryBuilder queryBuilder = this.getInsertInstance()
                                    .insertValue("feedbackid", feedback.getFeedbackId())
                                    .insertValue("comment", feedback.getComment())
                                    .insertValue("userid", feedback.getUserId());

    String sqlQuery = this.buildQuery(queryBuilder);

    this.executeQuery(QueryExecutor.getInstance(),sqlQuery);

    System.out.println("Feedback is updated\n");
  }

  // User Exceptions - String tyoe input --> varchar - Limit cross : Sql Exception
                // EmployeeID - Integer --> abc - Exception,
                // Catch from where calling
  // System Exceptions - SQL Query Incorrect, sent to Query Executor or Validate
              // Catch from AppDriver

}
