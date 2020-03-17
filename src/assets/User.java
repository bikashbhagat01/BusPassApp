package assets;

// POJO, Only holds data

public class User {
  private int employeeId;
  private String fName;
  private String lName;
  private String email;
  private String contactNo;
  private String emergencyContactNumber;
  private String emergencyContactName;
  private String bloodGroup;
  private String password;

  public User(int employeeId, String fName, String lName, String email,
              String contactNo, String emergencyContactNumber,
              String emergencyContactName, String bloodGroup,
              String password) {
    this.employeeId = employeeId;
    this.fName = fName;
    this.lName = lName;
    this.email = email;
    this.contactNo = contactNo;
    this.emergencyContactNumber = emergencyContactNumber;
    this.emergencyContactName = emergencyContactName;
    this.bloodGroup = bloodGroup;
    this.password = password;
  }

  public int getEmployeeId() {
    return employeeId;
  }
}