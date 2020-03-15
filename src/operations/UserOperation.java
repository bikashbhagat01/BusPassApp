package operations;

public class UserOperation {

  public void showMenu(String userId) {
    // Switch Case menu which calls user functions
  }

  private void viewRoute() {
    // Reads from Route Manager
  }

  private void updateProfile() {
    /*
    * Get the Fields from console to be updated and create an array
    * Get the Values from Console to be updated and create an array
    * send the values to UserManager().update(userId, fileds[],newValues[])
    * */
  }

  private void requestForBusPass() {
    // --- > Writes to Assets.BusPass Manager
    // Take user details for buspass - Route ID, Timing
    // Validate BusPass is already with User or Not
    // Validate Seat Availability for the route and timing
    // Create a bussPass Object
    // send object to busPassManager.create()
  }

  private void provideFeedback() {
     // Gets Comment from User
    // Create Feedback Object with UserId, feedbackId, and userId
    // Send feedback object to feedbackManager.create()
  }
}
