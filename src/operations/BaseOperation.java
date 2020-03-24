package operations;

import customExceptions.ApplicationException;
import customExceptions.UserException;
import dbTools.Validator;
import java.util.InputMismatchException;
import java.util.Scanner;
import managers.UserManager;


public class BaseOperation {

  private final static int MAX_PASSWORD_CONFIRM_TRIES = 3;
  private final static int MAX_PASSWORD_TRIES = 3;
  private final static int MAX_EXISTING_PASSWORD_TRIES = 0;

  protected String getFirstName() throws UserException {
    Scanner sc = OperationFactory.getScannerInstance();

    String firstName;

    try {
      firstName = sc.next();
    } catch (InputMismatchException e) {
      throw new UserException("Entered Name is Invalid");
    }

    if (!Validator.isValidNameLength(firstName)) {
      throw new UserException("First Name should be below 50 characters ");
    }

    if (!Validator.isValidName(firstName)) {
      throw new UserException("First Name should not contain digits");
    }
    return firstName;
  }

  protected String getLastName() throws UserException {
    Scanner sc = OperationFactory.getScannerInstance();

    String lastName;

    try {
      lastName = sc.next();
    } catch (InputMismatchException e) {
      throw new UserException("Entered Name is Invalid");
    }

    if (!Validator.isValidNameLength(lastName)) {
      throw new UserException("Last Name not Valid. Should be below - 50 characters ");
    }

    if (!Validator.isValidName(lastName)) {
      throw new UserException("Last Name should not contain digits");
    }

    return lastName;
  }


  protected String getEmail() throws UserException {
    Scanner sc = OperationFactory.getScannerInstance();

    String emailAddress;

    try {
      emailAddress = sc.next();
    } catch (InputMismatchException e) {
      throw new UserException("Entered e-mail address is Invalid ");
    }

    if (!Validator.isValidEmail(emailAddress)) {
      throw new UserException("Email Address not Valid.\n Format should be : abcd@amazon.com," +
              "xyz@gmail.com,etc. ");
    }

    return emailAddress;
  }

  protected String getContactNo() throws UserException {
    Scanner sc = OperationFactory.getScannerInstance();

    String contactNo;

    try {
      contactNo = sc.next();
    } catch (InputMismatchException e) {
      throw new UserException("Entered Phone Number is Invalid");
    }

    if (!Validator.isValidPhoneNoLength(contactNo)) {
      throw new UserException("Entered Phone Number is not 10 or 12 digits long\n" +
              "Phone Number can be of 10 digits without country code or, 12 digits with country code\n");
    }

    if (!Validator.isValidPhoneNo(contactNo)) {
      throw new UserException("Entered Phone number should only contain digits");
    }

    return contactNo;
  }

  protected String getFullName() throws UserException {
    Scanner sc = OperationFactory.getScannerInstance();

    String fullName;

    try {
      fullName = sc.next();
    } catch (InputMismatchException e) {
      throw new UserException("Entered Name is Invalid ");
    }

    if (!Validator.isValidFullNameLength(fullName)) {
      throw new UserException("Full Name should be under 50 Characters.\n" +
              "Please shorten the name by abbreviating it. E.g.- G.V.Sindhu, MD.Zeeshan, B.Bhagat ");
    }

    if (!Validator.isValidName(fullName)) {
      throw new UserException("Name should not contain Numbers.");
    }

    return fullName;
  }

  protected String getBloodGroup() throws UserException {
    Scanner sc = OperationFactory.getScannerInstance();

    String bloodGroup;

    try {
      bloodGroup = sc.next();
    } catch (InputMismatchException e) {
      throw new UserException("Entered BloodGroup is Invalid ");
    }

    if (!Validator.isValidBloodGroup(bloodGroup)) {
      throw new UserException("BloodGroups Can only be of length 10 as per below format :" +
              "APOSITIVE, ABNEGATIVE, etc.");
    }

    return bloodGroup.toUpperCase();
  }

  protected String getPassword() throws UserException {
    Scanner sc = OperationFactory.getScannerInstance();

    String password;
    int passwordTries = 1;

    try {
      password = sc.next();
    } catch (InputMismatchException e) {
      throw new UserException("Entered Password is Invalid ");
    }

    while (passwordTries < MAX_PASSWORD_TRIES) {
      if (!Validator.isValidPassword(password)) {
        ++passwordTries;
        System.out.println("Please Enter a Valid password [Only 3 tries Allowed]: \n" +
                " 1. A password must have at least eight characters.\n" +
                " 2. A password consists of only letters and digits.\n" +
                " 3. A password must contain at least two digits \n");
        try {
          password = sc.next();
        } catch (InputMismatchException e) {
          throw new UserException("Entered Password is Invalid ");
        }
      } else {
        return password;
      }
    }
    if (!Validator.isValidPassword(password)) {
      throw new UserException("Password tries Exhausted");
    }
    return password;
  }

  // PARAMs
  protected String getConfirmedPassword(String password) throws UserException {
    Scanner sc = OperationFactory.getScannerInstance();

    String confirmedPassword;
    int passwordConfirmTries = 1;

    try {
      confirmedPassword = sc.next();
    } catch (InputMismatchException e) {
      throw new UserException("Entered Password is Invalid ");
    }

    while (passwordConfirmTries <= MAX_PASSWORD_CONFIRM_TRIES) {
      if (!Validator.arePasswordsMatching(password,confirmedPassword)) {
        ++passwordConfirmTries;
        System.out.println("Please Enter a password which matches previous password value " +
                "[Only 3 tries Allowed]: \n");
        try {
          confirmedPassword = sc.next();
        } catch (InputMismatchException e) {
          throw new UserException("Entered Password is Invalid ");
        }
      } else {
        return confirmedPassword;
      }
    }
    if (!Validator.arePasswordsMatching(password, confirmedPassword)) {
      throw new UserException("Both Passwords do not match. Password tries Exhausted");
    }
    return confirmedPassword;
  }

  protected String getExistingPassword(int userId) throws UserException, ApplicationException {
    Scanner sc = OperationFactory.getScannerInstance();

    String existingPassword;
    int passwordEntryCount = 1;

    try {
      existingPassword = sc.next();
    } catch (InputMismatchException e) {
      throw new UserException("Entered Password is Invalid ");
    }

    while (passwordEntryCount <= MAX_EXISTING_PASSWORD_TRIES) {
      if (!UserManager.getInstance().isValidUserPassword(userId, existingPassword)) {
        ++passwordEntryCount;
        System.out.println("Please enter value which matches current password " +
                "[Only 3 tries Allowed]: \n");
        try {
          existingPassword = sc.next();
        } catch (InputMismatchException e) {
          throw new UserException("Entered Password is invalid.");
        }
      } else {
        return existingPassword;
      }
    }
    if (!UserManager.getInstance().isValidUserPassword(userId, existingPassword)) {
      throw new UserException("Entered Password does not match existing value." +
              "Password tries Exhausted");
    }
    return existingPassword;
  }

  protected int getUserId() throws UserException {
    Scanner sc = OperationFactory.getScannerInstance();

    int userId;

    try {
      userId = sc.nextInt();
    } catch (InputMismatchException e) {
      throw new UserException("\n Please enter correct Employee ID. " +
              "\n It is a 9-10 digit number" +
              "\n You check your Phonetool Or, contact your manager to find further " +
              "information\n");
    }

    return userId;
  }

  protected String getAdminId() throws UserException {
    Scanner sc = OperationFactory.getScannerInstance();

    String adminId;

    try {
      adminId = sc.next();
    } catch (InputMismatchException e) {
      throw new UserException(" Invalid Value Entered.");
    }
    return adminId;
  }

  protected String getAdminPassword() throws UserException {
    Scanner sc = OperationFactory.getScannerInstance();

    String adminPassword;

    try {
      adminPassword = sc.next();
    } catch (InputMismatchException e) {
      throw new UserException(" Invalid Password Entered.");
    }
    return adminPassword;
  }

  protected String getVehicleNo() throws UserException {
    Scanner sc = OperationFactory.getScannerInstance();

    String vehicleNo;

    try {
      vehicleNo = sc.next();
    } catch (InputMismatchException e) {
      throw new UserException(" Invalid Vehicle Number Entered.");
    }

    if(!Validator.isValidVehicleNo(vehicleNo)) {
      throw new UserException(" Vehicle Number is only accepted in the below format : \n" +
              "AB-11X-9999\n");
    }
    return vehicleNo;
  }

  protected int getRouteId() throws UserException {
    Scanner sc = OperationFactory.getScannerInstance();

    int routeId;

    try {
      routeId = sc.nextInt();
    } catch (InputMismatchException e) {
      throw new UserException("\n Please enter valid Route ID. Route ID is an integer number.");
    }
    return routeId;
  }

  protected int getBusId() throws UserException {
    Scanner sc = OperationFactory.getScannerInstance();

    int busId;

    try {
      busId = sc.nextInt();
    } catch (InputMismatchException e) {
      throw new UserException("\n Please enter valid Bus ID. Bus ID is an integer number.");
    }
    return busId;
  }

  protected int getBusType() throws UserException {
    Scanner sc = OperationFactory.getScannerInstance();

    int busType;

    try {
      busType = sc.nextInt();
    } catch (InputMismatchException e) {
      throw new UserException("\n Please enter valid Bus Type/ Seat Capacity. " +
              "Bus Type is an integer number.");
    }

    if(!Validator.isValidBusType(busType)) {
      throw new UserException("Bus Type/Capacity value exceeds maximum limit of 50");
    }
    return busType;
  }

  protected int getStopCount() throws UserException {
    Scanner sc = OperationFactory.getScannerInstance();

    int stopCount;

    try {
      stopCount = sc.nextInt();
    } catch (InputMismatchException e) {
      throw new UserException("\n Please enter valid Stop Count as an integer value");
    }

    if(!Validator.isValidStopCount(stopCount)) {
      throw new UserException("Stop Count value exceeds maximum limit of 10");
    }
    return stopCount;
  }

  protected String getStopName() throws UserException {
    Scanner sc = OperationFactory.getScannerInstance();

    String stopName;

    try {
      stopName = sc.next();
    } catch (InputMismatchException e) {
      throw new UserException("\n Please enter valid Stop Name ");
    }

    if(!Validator.isValidStopName(stopName)) {
      throw new UserException("Stop Name value exceeds maximum size of 100 characters");
    }
    return stopName;
  }

  protected String getTimeString() throws UserException {
    Scanner sc = OperationFactory.getScannerInstance();

    String timeString;

    try {
      timeString = sc.next();
    } catch (InputMismatchException e) {
      throw new UserException("\n Please enter valid Timing info in 24 hour format ");
    }

    if(!Validator.isValidTimeString(timeString)) {
      throw new UserException("Please enter valid Timing value as per the below format :" +
              "1. Should start with two digits from 00 to 23 for Hours.\n" +
              "2. Must be followed by either of the following separators - ':' or '-' or '/' .\n" +
              "3. Should be followed by two digits from 00 to 59 for Minutes.\n");
    }
    return timeString;
  }

  protected String getComment() throws UserException {
    Scanner sc = OperationFactory.getScannerInstance();

    String comment;

    try {
      comment = sc.next();
    } catch (InputMismatchException e) {
      throw new UserException("Entered Comment is Invalid");
    }

    if (!Validator.isCommentBlank(comment)) {
      throw new UserException("Blank Comment Detected");
    }

    if (!Validator.isValidComment(comment)) {
      throw new UserException("Please enter comments within a character limit of 100 ");
    }
    return comment;
  }
}