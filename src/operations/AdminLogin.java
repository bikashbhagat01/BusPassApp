package operations;
import assets.Admin;
import java.sql.SQLException;
import java.util.Scanner;

// AdminLogin , Singleton as Only 1 ADMIN is Hard-Coded
public class AdminLogin {
  // Set Hard-Coded values of ADMIN object to variables
  private String adminID = Admin.getAdminInstance().getAdminId();
  private String password = Admin.getAdminInstance().getPassword();

  private static int loginTries = 0;
  private final static int maxLoginTries = 5;

  private Scanner sc = OperationFactory.getScannerInstance();

  private static AdminLogin adminLogin;

  private AdminLogin(){}

  public static AdminLogin getInstance() {
    if(adminLogin == null) {
      adminLogin = new AdminLogin();
    }
    return adminLogin;
  }

  public void showMenu() throws Exception {
    setLoginDetails();
  }

  private void login(String adminId, String password) throws Exception {
    if(adminId.trim().equals(this.adminID) && password.equals(this.password)) {
      OperationFactory.getAdminOperationInstance().showMenu();
    } else {
      System.out.println("Incorrect Credentials Entered \n Please enter correct credentials : \n");
      setLoginDetails();
    }
  }

  private void setLoginDetails() throws Exception {
    // Get Admin credentials
    loginTries += 1;
    System.out.println("Enter Login ID : \n");
    String userId=sc.next();
    System.out.println("Enter Password : \n");
    String password=sc.next();
    if(loginTries < maxLoginTries) {
      login(userId, password);
    } else {
      System.out.println("Maximum Login Tries Exceeded! \n Returning to Home.");
      loginTries = 0;
      OperationFactory.getAppDriverInstance().initiate();
    }
  }
}

/**
 * operations/AppDriver.initiate() is used to be exit to Main menu
 * Alternative - use try-catch to reflect to operations/AppDriver.initiate()
 **/