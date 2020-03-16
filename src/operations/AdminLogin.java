package operations;
import assets.Admin;
import java.util.Scanner;

// AdminLogin , Singleton as Only 1 ADMIN is Hard-Coded

public class AdminLogin {
  private String adminID = Admin.getAdminInstance().getAdminId();
  private String password = Admin.getAdminInstance().getPassword();
  private static AdminLogin adminLogin;
  private AdminLogin(){}

  public static AdminLogin getInstance() {
    if(adminLogin == null) {
      adminLogin = new AdminLogin();
    }
    return adminLogin;
  }

  private void login(String adminId, String password) {
      //  true --> calls Assets.Admin Operations Menu
    if(adminId.trim().equalsIgnoreCase(this.adminID) && password.equals(this.password)) {
      AdminOperation adminOperation = new AdminOperation();
      adminOperation.showMenu();
    } else {
      System.out.println("Incorrect Credentials Entered \n Please enter correct credentials : \n");
      setLoginDetails();
      /* Redirect to Driver Menu - Feature to be implemented
         Potential Solution - Moving main menu functionality to current package
         Throw an Exception and call Driver in Catch
        */
    }
  }

  public void setLoginDetails() {
    Scanner in = new Scanner(System.in);
    System.out.println("Enter Login ID : \n");
    String userId=in.next();
    System.out.println("Enter Password : \n");
    String password=in.next();
    login(userId, password);
  }
}
