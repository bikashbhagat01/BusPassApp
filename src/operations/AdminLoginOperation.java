package operations;

import assets.Admin;
import customExceptions.ApplicationException;
import customExceptions.UserException;

// AdminLogin , Singleton as Only 1 ADMIN is Hard-Coded
public class AdminLoginOperation extends BaseOperation {

  // Set Hard-Coded values of ADMIN object to variables
  private String adminID = Admin.getAdminInstance().getAdminId();
  private String password = Admin.getAdminInstance().getPassword();

  private static int loginTries = 1;
  private final static int maxLoginTries = 3;

  private static AdminLoginOperation adminLogin;

  private AdminLoginOperation() { }

  public static AdminLoginOperation getInstance() {
    if(adminLogin == null) {
      adminLogin = new AdminLoginOperation();
    }
    return adminLogin;
  }

  public void showMenu() throws ApplicationException {
    try {
      setLoginDetails();
    } catch (UserException e) {
      System.out.println("An issue has occurred. Please retry logging in.");
    }
  }

  private void login(String adminId, String password) throws ApplicationException, UserException {
    if(adminId.trim().equals(this.adminID) && password.equals(this.password)) {
      OperationFactory.getAdminOperationInstance().showMenu();
    } else {
      System.out.println("Please enter correct ID and Password.[Only 3 tries are allowed] \n");
      setLoginDetails();
    }
  }

  private boolean setLoginDetails() throws UserException, ApplicationException {
    // Get Admin credentials
    loginTries += 1;

    System.out.println("Enter Login ID : \n");
    String userId = this.getAdminId();

    System.out.println("Enter Password : \n");
    String password = this.getAdminPassword();

    if (loginTries > maxLoginTries) {
      System.out.println("Maximum Login Tries Exceeded! \n Returning to Home.");

      loginTries = 0;

      return false;
    }

    login(userId, password);

    return true;
  }
}