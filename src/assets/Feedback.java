package assets;

// POJO, Only holds data

public class Feedback {
  private int feedbackId;
  private String comment;
  private int userId;

  public Feedback(int feedbackId, String comment, int userId) {
    this.feedbackId = feedbackId;
    this.comment = comment;
    this.userId = userId;
  }

  //  Setters and Getters to be added
  //  Feedback can only be created with all 3 fields
}


