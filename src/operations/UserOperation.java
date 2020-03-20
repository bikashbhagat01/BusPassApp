package operations;

import dbTools.Validate;
import java.sql.SQLException;
import java.util.Scanner;
import managers.BusPassManager;
import managers.UserManager;

public class UserOperation {
  Scanner sc = OperationFactory.getScannerInstance();
  public  void showMenu(int userId) throws SQLException, ClassNotFoundException {
    boolean exCode = false;
    String choice = "";
    while(!exCode){
      System.out.println( "\n1. View Available Routes\n" +
              "2. Update Profile\n" +
              "3. Raise Request For a New Route" +
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
          requestNewRoute();
        case "4":
          requestForBusPass();
          break;
        case "5":
          provideFeedback();
          break;
        case "6":
          exCode = true;
          break;
        default:
          System.out.println("Please Enter Valid Option\n");
      }
    }
    System.out.println("Returning to Main Menu");
    OperationFactory.getAppDriverInstance().initiate();
  }


  private void viewRoute() {
    // Create Query to read the same, Maybe delete stop object and just keep
    // routeid with stopname
  }

  private boolean updateProfile(int userId) throws SQLException, ClassNotFoundException {
    /*
    * Get the Fields from console to be updated and create an array
    * Get the Values from Console to be updated and create an array
    * send the values to UserManager().update(userId, fileds[],newValues[])
    * */
    boolean exCode = false;
    String choice = "";
    while(!exCode){
      System.out.println("Select Field to Update");
      System.out.println( "\n1. Name \n" +
              "2. E-mail address\n" +
              "3. Contact Number\n" +
              "4. Emergency Contact Details\n" +
              "5. Blood Group\n" +
              "6. Password\n" +
              "7. Return to User Menu");
      choice = sc.nextLine();
      if(!choice.equalsIgnoreCase("7")) {
        System.out.println("Please enter values below : ");
      }
      switch (choice) {
        case "1":
          System.out.println("First Name :\n");
          String firstName = sc.next();
          System.out.println("Last Name :\n");
          String lastName = sc.next();
          UserManager.update(userId,"fname", firstName);
          UserManager.update(userId,"lname", lastName);
          System.out.println("Name Updated to : ");
          break;
        case "2":
          System.out.println("E-mail Address :\n");
          String email = sc.next();
          UserManager.update(userId,"email",email);
          System.out.println("E-mail address Updated");
          break;
        case "3":
          System.out.println("Contact Number :");
          String contactNumber = sc.next();
          UserManager.update(userId, "contactno",contactNumber);
          System.out.println("Contact Number Updated");
          break;
        case "4":
          System.out.println("Emergency Contact Name : \n");
          String emegergencyContactName = sc.next();
          System.out.println("Emergency Contact Number : \n");
          String emergencyContactNumber = sc.next();
          UserManager.update(userId,"emergencycontactname", emegergencyContactName);
          UserManager.update(userId, "emergencycontactno", emergencyContactNumber);
          System.out.println("Emergency Contact Details Updated");
          break;
        case "5":
          System.out.println("Blood Group :\n");
          String bloodGroup = sc.next();
          UserManager.update(userId,"bloodgroup", bloodGroup);
          System.out.println("Blood Group Updated");
          break;
        case "6":
          System.out.println("Existing Password :\n");
          String oldPassword = sc.next();
          if(!Validate.isValidUserPassword(userId, oldPassword)) {
            System.out.println("Incorrect Password Entered. Returning to Update Menu");
            break;
          }
          System.out.println("Enter New Password :\n");
          String newPasswordOnce = sc.next();
          System.out.println("Enter New Password Again :\n");
          String newPasswordTwice = sc.next();
          if(!newPasswordOnce.equals(newPasswordTwice)) {
            System.out.println("Both Passwords do not match");
            break;
          }
          UserManager.update(userId,"password", newPasswordTwice);
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

  private void requestForBusPass() throws SQLException, ClassNotFoundException {
    // --- > Writes to Assets.BusPass Manager
    // Take user details for buspass - Route ID, Timing
    // Validate BusPass is already with User or Not
    // Validate Seat Availability for the route and timing
    // Create a bussPass Object
    // send object to busPassManager.create()
    BusPassManager.create();
  }

  private void provideFeedback() {
     // Gets Comment from User
    // Create Feedback Object with UserId, feedbackId, and userId
    // Send feedback object to feedbackManager.create()
  }
}
