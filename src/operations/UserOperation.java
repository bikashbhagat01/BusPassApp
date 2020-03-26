package operations;

import assets.AssetFactory;
import assets.Feedback;
import assets.RouteRequest;
import customExceptions.ApplicationException;
import customExceptions.UserException;
import dbTools.TimeConverter;
import managers.BusManager;
import managers.BusPassManager;
import managers.FeedbackManager;
import managers.RouteManager;
import managers.RouteRequestManager;
import managers.StopManager;
import managers.UserManager;

public class UserOperation extends BaseOperation{

  public boolean showMenu(int userId) throws ApplicationException {

    boolean exitCode = false;
    String choice = "";

    while (!exitCode) {

        System.out.println("\n1. View Available Routes\n" +
                "2. Update Profile\n" +
                "3. Raise Request For a New Route \n" +
                "4. Raise Request for a Bus Pass\n" +
                "5. Provide Feedback\n" +
                "0. Exit to Main Menu\n");
        choice = OperationFactory.getScannerInstance().next();

      switch (choice) {
        case "1":
          displayBusTimingsAndRoutes();
          break;
        case "2":
          updateProfile(userId);
          break;
        case "3":
          try {
            requestNewRoute(userId);
          } catch (UserException e) {
            System.out.println("Returning to previous menu as the below exception has occurred.");
            System.out.println(e.getMessage());
          }
          break;
        case "4":
          requestForBusPass(userId);
          break;
        case "5":
          try {
            provideFeedback(userId);
          } catch (UserException e) {
            System.out.println("Returning to previous menu as the below exception has occurred.");
            System.out.println(e.getMessage());
          }
          break;
        case "0":
          exitCode = true;
          break;

        default:
          System.out.println("Please Enter Valid Option\n");
      }
    }

    System.out.println("Returning to Previous Menu");

    return true;
  }


  private void displayBusTimingsAndRoutes() throws ApplicationException {
    System.out.println("Below is information on Start Timings and Routes which have Active " +
            "and Available Buses");

    BusManager.getInstance().displayAvailableBusTimingsAndRoutes();
  }

  private boolean updateProfile(int userId) throws ApplicationException {
    boolean exCode = false;
    String choice = "";

    while (!exCode) {
      System.out.println("\nSelect Field to Update\n");
      System.out.println("1. Name \n" +
              "2. E-mail address\n" +
              "3. Contact Number\n" +
              "4. Emergency Contact Details\n" +
              "5. Blood Group\n" +
              "6. Password\n" +
              "0. Return to User Menu");

      choice = OperationFactory.getScannerInstance().next();

      if (!choice.equalsIgnoreCase("0")) {
        System.out.println("Please enter values below : ");
      }

      switch (choice) {
        case "1":
          try {
          updateName(userId);
          } catch (UserException e) {
            System.out.println("Returning to previous menu as the below exception has occurred.");
            System.out.println(e.getMessage());
          }
          break;

        case "2":
          try {
            updateEmail(userId);
          } catch (UserException e) {
            System.out.println("Returning to previous menu as the below exception has occurred.");
            System.out.println(e.getMessage());
          }
          break;

        case "3":
          try {
            updateContactNo(userId);
          } catch (UserException e) {
            System.out.println("Returning to previous menu as the below exception has occurred.");
            System.out.println(e.getMessage());
          }
          break;

        case "4":
          try {
            updateEmergencyContact(userId);
          } catch (UserException e) {
            System.out.println("Returning to previous menu as the below exception has occurred.");
            System.out.println(e.getMessage());
          }

          break;

        case "5":
          try {
            updateBloodGroup(userId);
          } catch (UserException e) {
            System.out.println("Returning to previous menu as the below exception has occurred.");
            System.out.println(e.getMessage());
          }
          break;

        case "6":
          try {
            updatePassword(userId);
          } catch (UserException e) {
            System.out.println("Returning to previous menu as the below exception has occurred.");
            System.out.println(e.getMessage());
          }
          break;

        case "0":
          exCode = true;
          break;

        default:
          System.out.println("Please Enter Valid Option\n");
      }

    }
    System.out.println("Returning to User Menu");

    return true;
  }

  private void updatePassword(int userId) throws UserException, ApplicationException {
    System.out.println("Existing Password :\n");
    String oldPassword = this.getExistingPassword(userId);

    System.out.println("Enter New Password :\n");
    String newPassword = this.getPassword();

    System.out.println("Enter New Password Again :\n");
    String newConfirmedPassword = this.getConfirmedPassword(newPassword);

    UserManager.getInstance().update(userId, "password", newConfirmedPassword);
  }

  private void updateBloodGroup(int userId) throws UserException, ApplicationException {
    System.out.println("Blood Group :\n");
    String bloodGroup = this.getBloodGroup();

    UserManager.getInstance().update(userId, "bloodgroup", bloodGroup);

    System.out.println("Blood Group Updated");

  }

  private void updateEmergencyContact(int userId) throws UserException, ApplicationException {
    System.out.println("Emergency Contact Name : \n");
    String emergencyContactName = this.getFullName();

    System.out.println("Emergency Contact Number : \n");
    String emergencyContactNumber = this.getContactNo();

    UserManager.getInstance().update(userId, "emergencycontactname", emergencyContactName);
    UserManager.getInstance().update(userId, "emergencycontactno", emergencyContactNumber);

    System.out.println("Emergency Contact Details Updated");
  }

  private void updateContactNo(int userId) throws UserException, ApplicationException {
    System.out.println("Contact Number :");
    String contactNumber = this.getContactNo();

    UserManager.getInstance().update(userId, "contactno", contactNumber);

    System.out.println("Contact Number Updated");
  }

  private void updateEmail(int userId) throws UserException, ApplicationException {
    System.out.println("E-mail Address :\n");
    String email = this.getEmail();

    UserManager.getInstance().update(userId, "email", email);

    System.out.println("E-mail address Updated");
  }

  private void updateName(int userId) throws UserException, ApplicationException {
    System.out.println("First Name :\n");
    String firstName = this.getFirstName();

    System.out.println("Last Name :\n");
    String lastName = this.getLastName();

    UserManager.getInstance().update(userId, "fname", firstName);
    UserManager.getInstance().update(userId, "lname", lastName);

    System.out.println("Name Updated");
  }

  private boolean requestNewRoute(int userId) throws ApplicationException, UserException {
    /**Validates if requested stops are under a route with selected timing.
     * if route(s) with selected timing and stops exist(s), then displays the information
     * if such a route does not exist, creates a route request record in table.
     */

    System.out.println("Enter Details Below: \n");

    System.out.println("Start Stop Name :\n");
    String startStop = this.getStopName();

    System.out.println("End Stop Name :\n");
    String endStop = this.getStopName();

    System.out.println("Start Timing [in 24 hour format separated by : Or / or -]:\n");
    String timingString = this.getTimeString();

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

    RouteRequest routeRequest;

    if (stopsExist == true) {
      routeRequest = AssetFactory.getInstance().getRouteRequestInstance(startStopId, endStopId, userId,
              routeExists, timeInMinutes);
    } else {
      routeRequest = AssetFactory.getInstance().getRouteRequestInstance(startStop, endStop, userId,
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
  private boolean provideFeedback(int userId) throws ApplicationException, UserException {
    System.out.println( "Enter Your Valuable Comment below [Word Limit 100]\n " +
                        "To return to previous menu, press 'Enter' twice\n");

    String comment = this.getComment();

    Feedback feedback = AssetFactory.getInstance().getFeedbackInstance(userId, comment);

    FeedbackManager.getInstance().create(feedback);

    System.out.println("Thank You for Your Valuable Comment");

    return true;
  }
}
