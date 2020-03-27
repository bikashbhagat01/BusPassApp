package managers;

import assets.Feedback;
import customExceptions.ApplicationException;
import queryHelper.QueryBuilder;

public class FeedbackManager extends BaseManager{

  private static FeedbackManager feedbackManager;

  public static FeedbackManager getInstance() {
    if(feedbackManager == null) {
      feedbackManager = new FeedbackManager();
    }
    return feedbackManager;
  }

  public boolean create(Feedback feedback) throws ApplicationException {
    QueryBuilder queryBuilder = this.getInsertInstance()
                                    .onTable("feedback")
                                    .insertValue("feedbackid", feedback.getFeedbackId())
                                    .insertValue("comment", feedback.getComment())
                                    .insertValue("userid", feedback.getUserId());

    String sqlQuery = this.buildQuery(queryBuilder);

    this.executeQuery(sqlQuery);

    return true;
  }
}
