package operations;

import assets.AssetFactory;
import assets.User;
import customExceptions.ApplicationException;
import customExceptions.UserException;
import java.util.Scanner;
import managers.UserManager;

public class UserLogin extends BaseOperation {

  private final static int maxLoginTries = 5;
  private static int loginTries = 0;

  // try combining userlogin and userOperation using states. Remove bottom to top layer routing via
  // returns and state alterations

  public boolean showMenu() throws ApplicationException {
    boolean exitCode = false;
    String choice = "";
    Scanner sc = OperationFactory.getScannerInstance();

    while (!exitCode) {

        System.out.println("\n1. Existing User " +
                "\n2. New User" +
                "\n0. Exit\n");

      choice = sc.next();

      switch (choice) {
        case "1":
          try {
            // setLoginDetails ka definition me ja
            setLoginDetails();
          } catch (UserException e) {
            System.out.println("Returning to previous menu as the below exception has occurred.");
            System.out.println(e.getMessage());
          }
          break;
        case "2":
          try {
            createAccount();
          } catch (UserException e) {
            System.out.println("Returning to previous menu as the below exception has occurred.");
            System.out.println(e.getMessage());
          }
          break;
        case "0":
          exitCode = true;
          break;

        default:
          System.out.println("Please Enter Valid Option");
      }
    }

    System.out.println("Returning to Main Menu");

    return true;
  }

  private void createAccount() throws ApplicationException, UserException {
    // Creates a User Account and sends to login page
    System.out.println("\nPlease Enter the below details as prompted." +
            "\nPress Enter to confirm entry\n ");
    System.out.println("\n Employee ID : \n");
    Scanner sc = OperationFactory.getScannerInstance();

    int employeeId = sc.nextInt();
    boolean userAlreadyExists = UserManager.getInstance().isValidUser(employeeId);
    if (userAlreadyExists) {
      System.out.println("User Id for " + employeeId + " already exists\n");
    }
    if (!userAlreadyExists) {

      System.out.println("\n First Name : \n");
      String firstName = this.getFirstName();

      System.out.println("\n Last Name : \n");
      String lastName = this.getLastName();

      System.out.println("\n Email Address : \n");
      String email = this.getEmail();

      System.out.println("\n Contact No. : \n");
      String contactNo = this.getContactNo();

      System.out.println("\n Emergency Contact No. : \n");
      String emergencyContactNo = this.getContactNo();

      System.out.println("\n Emergency Contact Name : \n");
      String emergencyContactName = this.getFullName();

      System.out.println("\n BloodGroup [Format : APOSITIVE, ABNEGATIVE,etc.] : \n");
      String bloodGroup = this.getBloodGroup();

      System.out.println("\n Password : \n" +
              "[Should be of at least 8 characters, contain only letters and digits and " +
              "must contain at least 2 digits]");
      String password = this.getPassword();

      System.out.println("\n Confirm Password : \n" +
              "[Should be the same value as entered before]");
      String confirmedPassword = this.getConfirmedPassword(password);

      User user = AssetFactory.getInstance().getUserInstance(employeeId, firstName, lastName,
              email, contactNo, emergencyContactNo,
              emergencyContactName, bloodGroup,
              confirmedPassword);

      UserManager.getInstance().create(user);

      System.out.println("Your Account with User ID : " + user.getEmployeeId() +
              " has been created ! \n");
      System.out.println("Please Login with your User ID and Password below : \n");

        setLoginDetails();
    }
  }

  // If the user and password combination exist, redirect to UserOperations
  private void login(int userId, String password) throws ApplicationException, UserException {
    if (UserManager.getInstance().isValidUserPassword(userId, password)) {
      OperationFactory.getUserOperationInstance().showMenu(userId); // So, yeha se return kar k kidhar setlogin pe?
    } else {
      System.out.println("\nIncorrect Credentials Entered \nPlease enter correct credentials : \n");
      setLoginDetails();
    }
  }

  private boolean setLoginDetails() throws ApplicationException, UserException {

    loginTries += 1;

    System.out.println("Enter Employee ID : \n");
    int userId = this.getUserId();

    System.out.println("Enter Password : \n");
    String password = this.getPassword();

    if (loginTries > maxLoginTries) {
      System.out.println("Maximum Login Tries Exceeded! \n Returning to Home.");

      loginTries = 0;
      return false;
    }
    // iska
    login(userId, password);

    return true;
    }
  }

