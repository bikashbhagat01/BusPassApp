package operations;

import java.sql.SQLException;
import java.util.Scanner;

public class OperationFactory {

  public static AdminLogin getAdminLoginInstance()
          throws SQLException, ClassNotFoundException {
    return AdminLogin.getInstance();
  }

  public static AdminOperation getAdminOperationInstance()
          throws SQLException, ClassNotFoundException {
    return new AdminOperation();
  }

  public static UserLogin getUserLoginInstance()
          throws SQLException, ClassNotFoundException {
    return new UserLogin();
  }

  public static UserOperation getUserOperationInstance()
          throws SQLException, ClassNotFoundException {
    return new UserOperation();
  }

  public static VisitorOperation getVisitorOperationInstance()
          throws SQLException, ClassNotFoundException {
    return new VisitorOperation();
  }

  public static Scanner getScannerInstance() {
    return new Scanner(System.in).useDelimiter("\n");
  }

  public static AppDriver getAppDriverInstance() {
    return new AppDriver();
  }
}
