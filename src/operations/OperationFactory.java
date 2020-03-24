package operations;

import java.util.Scanner;

public class OperationFactory {

  public static AdminLogin getAdminLoginInstance() {
    return AdminLogin.getInstance();
  }

  public static AdminOperation getAdminOperationInstance() {
    return new AdminOperation();
  }

  public static UserLogin getUserLoginInstance() {
    return new UserLogin();
  }

  public static UserOperation getUserOperationInstance() {
    return new UserOperation();
  }

  public static VisitorOperation getVisitorOperationInstance() {
    return new VisitorOperation();
  }

  public static Scanner getScannerInstance() {
    return new Scanner(System.in).useDelimiter("\n");
  }

  public static AppDriver getAppDriverInstance() {
    return new AppDriver();
  }
}
