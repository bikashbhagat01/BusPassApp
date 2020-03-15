package assets;

public class Admin {
  private String adminId;
  private String password;
  private static Admin admin;

  private Admin() {}

  // Singleton
  public static Admin getAdminInstance(){
    if(admin == null) {
      admin = new Admin();
      admin.adminId = "ADMIN";
      admin.password = "ADMIN";
      return admin;
    }
    return admin;
  }

  public String getAdminId() {
    return adminId;
  }

  public String getPassword() {
    return password;
  }
}



//  private String fName;
//  private String lName;
//  private String email;
//  private String contactNo;
//  private String emergencyContactNumber;
//  private String emergencyContactName;
//  private String bloodGroup;