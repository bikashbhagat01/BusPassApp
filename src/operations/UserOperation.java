package operations;

import assets.AssetFactory;
import assets.Feedback;
import assets.RouteRequest;
import customExceptions.ApplicationException;
import dbTools.TimeConverter;
import dbTools.Validator;
import java.sql.SQLException;
import java.util.Scanner;
import managers.BusManager;
import managers.BusPassManager;
import managers.FeedbackManager;
import managers.RouteManager;
import managers.RouteRequestManager;
import managers.StopManager;
import managers.UserManager;

public class UserOperation {
  Scanner sc = OperationFactory.getScannerInstance();

  public boolean showMenu(int userId) throws ApplicationException {

    boolean exCode = false;
    String choice = "";

    while (!exCode) {
      System.out.println("\n1. View Available Routes\n" +
              "2. Update Profile\n" +
              "3. Raise Request For a New Route \n" +
              "4. Raise Request for a Bus Pass\n" +
              "5. Provide Feedback\n" +
              "6. Exit to Main Menu\n");

      choice = sc.nextLine();

      switch (choice) {
        case "1":
          viewRoute();
          break;
        case "2":
          updateProfile(userId);
          break;
        case "3":
          requestNewRoute(userId);
        case "4":
          requestForBusPass(userId);
          break;
        case "5":
          provideFeedback(userId);
          break;
        case "6":
          exCode = true;
          break;

        default:
          System.out.println("Please Enter Valid Option\n");
      }
    }

    System.out.println("Returning to Previous Menu");

    return true;
  }


  private void viewRoute() throws ApplicationException {
    BusManager.getInstance().displayAvailableBusTimingsAndRoutes();
  }

  private boolean updateProfile(int userId) throws ApplicationException {
    boolean exCode = false;
    String choice = "";

    while (!exCode) {
      System.out.println("Select Field to Update");
      System.out.println("\n1. Name \n" +
              "2. E-mail address\n" +
              "3. Contact Number\n" +
              "4. Emergency Contact Details\n" +
              "5. Blood Group\n" +
              "6. Password\n" +
              "7. Return to User Menu");

      choice = sc.nextLine();

      if (!choice.equalsIgnoreCase("7")) {
        System.out.println("Please enter values below : ");
      }

      switch (choice) {
        case "1":
          System.out.println("First Name :\n");
          String firstName = sc.next();
          System.out.println("Last Name :\n");
          String lastName = sc.next();
          UserManager.getInstance().update(userId, "fname", firstName);
          UserManager.getInstance().update(userId, "lname", lastName);
          System.out.println("Name Updated to : ");
          break;
        case "2":
          System.out.println("E-mail Address :\n");
          String email = sc.next();
          UserManager.getInstance().update(userId, "email", email);
          System.out.println("E-mail address Updated");
          break;
        case "3":
          System.out.println("Contact Number :");
          String contactNumber = sc.next();
          UserManager.getInstance().update(userId, "contactno", contactNumber);
          System.out.println("Contact Number Updated");
          break;
        case "4":
          System.out.println("Emergency Contact Name : \n");
          String emegergencyContactName = sc.next();
          System.out.println("Emergency Contact Number : \n");
          String emergencyContactNumber = sc.next();
          UserManager.getInstance().update(userId, "emergencycontactname", emegergencyContactName);
          UserManager.getInstance().update(userId, "emergencycontactno", emergencyContactNumber);
          System.out.println("Emergency Contact Details Updated");
          break;
        case "5":
          System.out.println("Blood Group :\n");
          String bloodGroup = sc.next();
          UserManager.getInstance().update(userId, "bloodgroup", bloodGroup);
          System.out.println("Blood Group Updated");
          break;
        case "6":
          System.out.println("Existing Password :\n");
          String oldPassword = sc.next();
          if (!UserManager.getInstance().isValidUserPassword(userId, oldPassword)) {
            System.out.println("Incorrect Password Entered. Returning to Update Menu");
            break;
          }
          System.out.println("Enter New Password :\n");
          String newPasswordOnce = sc.next();
          System.out.println("Enter New Password Again :\n");
          String newPasswordTwice = sc.next();
          if (!newPasswordOnce.equals(newPasswordTwice)) {
            System.out.println("Both Passwords do not match");
            break;
          }
          UserManager.getInstance().update(userId, "password", newPasswordTwice);
          break;
        case "7":
          exCode = true;
          break;

        default:
          System.out.println("Please Enter Valid Option\n");
      }
    }
    System.out.println("Returning to User Menu");

    return true;
  }

  private boolean requestNewRoute(int userId) throws ApplicationException {
    /**Validates if requested stops are under a route with selected timing.
     * if route(s) with selected timing and stops exist(s), then displays the information
     * if such a route does not exist, creates a route request record in table.
     */

    System.out.println("Enter Details Below: \n");

    System.out.println("Start Stop Name :\n");
    String startStop = sc.next();

    System.out.println("End Stop Name :\n");
    String endStop = sc.next();

    System.out.println("Start Timing [in 24 hour format separated by : Or / or -]:\n");
    String timingString = sc.next();

    int timeInMinutes = TimeConverter.getTimeAsMinutes(timingString);

    boolean routeExists = true;
    boolean stopsExist = true;

    int startStopId = 0;
    int endStopId = 0;

    if (!(StopManager.getInstance().isPresent("stop", "stopname", startStop) ||
            StopManager.getInstance().isPresent("stop", "stopname", endStop))) {
      stopsExist = false;
    }

    if (stopsExist == true) {
      startStopId = StopManager.getInstance().getStopIdForName(startStop);
      endStopId = StopManager.getInstance().getStopIdForName(endStop);

      int[] routeIds = RouteManager.getInstance().findRoutesForStops(startStopId, endStopId);

      if (BusManager.getInstance().isBusAvailableForRoutesAndTiming(routeIds, timeInMinutes)) {
        System.out.println("Available bus(es) for your request.");

        BusManager.getInstance().displayAvailableBusTimingsAndRoutes(routeIds, timeInMinutes);

        return false;
      } else {
        routeExists = false;
      }
    }

    RouteRequest routeRequest = null;

    if (stopsExist == true) {
      routeRequest = AssetFactory.getRouteRequestInstance(startStopId, endStopId, userId,
              routeExists, timeInMinutes);
    } else {
      routeRequest = AssetFactory.getRouteRequestInstance(startStop, endStop, userId,
              routeExists, timeInMinutes);
    }

    RouteRequestManager.getInstance().create(routeRequest);

    System.out.println("Your Route Request has been sent!");

    return true;
  }

  // Creates a BusPass for User
  private void requestForBusPass(int userId) throws ApplicationException {
    BusPassManager.getInstance().createBusPass(userId);
  }

  // Creates a Feedback record for the user comment
  private boolean provideFeedback(int userId) throws ApplicationException {
    System.out.println( "Enter Your Valuable Comment below [Word Limit 100]\n " +
                        "To return to previous menu, press 'Enter' twice\n");

    String comment = sc.next();

    if (Validator.isCommentBlank(comment)) {
      System.out.println("No Comment Found.\n Returning to User Menu");
      return false;
    }

    if (!Validator.isValidComment(comment)) {
      System.out.println("Comment exceeds word limit of 100 characters.\n Returning to User Menu");
      return false;
    }

    Feedback feedback = AssetFactory.getFeedbackInstance(userId, comment);

    FeedbackManager.getInstance().create(feedback);


    System.out.println("Thank You for Your Comment");

    return true;
  }
}
