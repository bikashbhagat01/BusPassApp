package operations;

import assets.AssetFactory;
import assets.User;
import dbTools.Validator;
import java.sql.SQLException;
import java.util.Scanner;
import managers.UserManager;

public class UserLogin {

  private final static int maxLoginTries = 5;
  private static int loginTries = 0;
  private Scanner sc = OperationFactory.getScannerInstance();

  public void showMenu() throws SQLException, ClassNotFoundException {
    boolean exCode = false;
    String choice = "";
    while (!exCode) {
      System.out.println("\n1. Existing User " +
              "\n2. New User" +
              "\n3. Exit\n");
      choice = sc.nextLine();
      switch (choice) {
        case "1":
          setLoginDetails();
          break;

        case "2":
          createAccount();
          break;

        case "3":
          exCode = true;
          break;

        default:
          System.out.println("Please Enter Valid Option");
      }
    }
    System.out.println("Returning to Main Menu");
    OperationFactory.getAppDriverInstance().initiate();
  }

  private void createAccount() throws SQLException, ClassNotFoundException {
    // Creates a User Account and sends to login page
    System.out.println("Please Enter the below details as prompted. " +
            "\n Press Enter to confirm entry\n ");
    System.out.println("Employee ID : \n");
    int employeeId = sc.nextInt();
    boolean userAlreadyExists = Validator.isValidUser(employeeId);
    if (userAlreadyExists) {
      System.out.println("User Id for " + employeeId + " already exists\n");
      showMenu();
    }
    if (!userAlreadyExists) {
      System.out.println("\n First Name : \n");
      String firstName = sc.next();
      System.out.println("\n Last Name : \n");
      String lastName = sc.next();
      System.out.println("\n Email-ID : \n");
      String email = sc.next();
      System.out.println("\n Contact No. : \n");
      String contactNo = sc.next();
      System.out.println("\n Emergency Contact No. : \n");
      String emergencyContactNo = sc.next();
      System.out.println("\n Emergency Contact Name : \n");
      String emergencyContactName = sc.next();
      System.out.println("\n BloodGroup : \n");
      String bloodGroup = sc.next();
      boolean exitCode = false;
      String password = "";
      while (!exitCode) {
        System.out.println("\n Password : \n");
        password = sc.next();
        if (!Validator.isValidPassword(password)) {
          System.out.println("Please Enter a Valid password :" +
                  "\n 1. A password must have at least eight characters.\n" +
                  " 2. A password consists of only letters and digits.\n" +
                  " 3. A password must contain at least two digits \n");
        } else {
          System.out.println("\n Password Again: \n");
          String passwordConfirm = sc.next();
          if (!password.equals(passwordConfirm)) {
            System.out.println("Both Passwords do not match");
          } else {
            exitCode = true;
          }
        }
      }
      User user = AssetFactory.getUserInstance(employeeId, firstName, lastName,
              email, contactNo, emergencyContactNo,
              emergencyContactName, bloodGroup,
              password);
      UserManager.create(user);
      System.out.println("Your Account with User ID : " + user.getEmployeeId() +
              " has been created ! \n");
      System.out.println("Please Login with your User ID and Password below : \n");
      setLoginDetails();
    }
  }

  private void login(int userId, String password) throws SQLException, ClassNotFoundException {
    // If the user and password exist, redirect to UserOperations
    if (Validator.isValidUserPassword(userId, password)) {
      OperationFactory.getUserOperationInstance().showMenu(userId);
    } else {
      System.out.println("\nIncorrect Credentials Entered \n Please enter correct credentials : \n");
      setLoginDetails();
    }
  }

  private void setLoginDetails() throws SQLException, ClassNotFoundException {
    loginTries += 1;
    System.out.println("Enter Employee ID : \n");
      int userId = sc.nextInt();
      System.out.println("Enter Password : \n");
      String password = sc.next();
      if (loginTries < maxLoginTries) {
        login(userId, password);
      } else {
        System.out.println("Maximum Login Tries Exceeded! \n Returning to Home.");
        loginTries = 0;
        OperationFactory.getAppDriverInstance().initiate();
      }
    }
  }

