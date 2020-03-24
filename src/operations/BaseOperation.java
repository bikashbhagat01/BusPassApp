package operations;

import customExceptions.UserException;
import dbTools.Validator;
import java.util.InputMismatchException;
import java.util.Scanner;




public class BaseOperation {

  private static int passwordTries = 1;
  private final static int MAX_PASSWORD_TRIES = 3;

  private static int passwordConfirmTries = 1;
  private final static int MAX_PASSWORD_CONFIRM_TRIES = 3;

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

    return bloodGroup;
  }

  protected String getPassword() throws UserException {

    Scanner sc = OperationFactory.getScannerInstance();

    String password;

    try {
      password = sc.next();
    } catch (InputMismatchException e) {
      throw new UserException("Invalid Password Entered");
    }

    if (!Validator.isValidPassword(password) && passwordTries < MAX_PASSWORD_TRIES) {
      System.out.println("Please Enter a Valid password [Only 3 tries Allowed]: \n" +
              " 1. A password must have at least eight characters.\n" +
              " 2. A password consists of only letters and digits.\n" +
              " 3. A password must contain at least two digits \n");
      ++passwordTries;
      getPassword();
    } else if((!Validator.isValidPassword(password) && passwordTries >= MAX_PASSWORD_TRIES)){
      passwordTries  = 1;
      throw new UserException("Password tries exhausted.");
    }
    passwordTries = 1;
    return password;
  }

  protected String getConfirmedPassword(String password) throws UserException {
    Scanner sc = OperationFactory.getScannerInstance();

    String confirmedPassword;

    try {
      confirmedPassword = sc.next();
    } catch(InputMismatchException e) {
      throw new UserException("Incorrect Value Entered");
    }

    if(!Validator.arePasswordsMatching(password, confirmedPassword)
            && passwordConfirmTries < MAX_PASSWORD_CONFIRM_TRIES) {
      System.out.println("Please enter the same password again to confirm [Only 3 tries Allowed]");
      ++passwordConfirmTries;
      getConfirmedPassword(password);
    } else {
      throw new UserException("Confirm Password tries exhausted.");
    }
    passwordConfirmTries = 1;
    return confirmedPassword;
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
}