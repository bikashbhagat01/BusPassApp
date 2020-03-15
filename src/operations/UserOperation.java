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

  private void requestNewRoute() {
    // Get Stops, time
    // Search and save array of stopIds from StopManager.search()
    //  if stopIds do not exist create a routeRequest to Admin by calling RouteRequest Manager
    //  with routeExists flag as False
    //
    // Validate if route(s) exist(s) for stopIds or not, by searching in Route-Stops look-up table
    // via RouteManager.search(start, end)
    // if route Doesn't exist, proceed to create a routeRequest to Admin by calling RouteRequest Manager
    // with routeExists flag as False
    // if route exists and a bus exists time, inform route exists
    // else, proceed to create a routeRequest to Admin with routeExists flag as True
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
