package assets;

// POJO, Only holds data

public class Feedback {

  private int feedbackId;
  private String comment;
  private int userId;

  public Feedback(int feedbackId, int userId, String comment) {
    this.feedbackId = feedbackId;
    this.comment = comment;
    this.userId = userId;
  }

  public int getFeedbackId() {
    return feedbackId;
  }

  public String getComment() {
    return comment;
  }

  public int getUserId() {
    return userId;
  }
}


