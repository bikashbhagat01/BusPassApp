package operations;

import assets.Admin;

public class AdminLogin {
  private String adminID = Admin.getAdminInstance().getAdminId();
  private String password = Admin.getAdminInstance().getPassword();

  // Singleton Implementation

  public boolean login(String adminId, String password) {

      //  true --> calls Assets.Admin Operations Menu

      return false;
  }
}
