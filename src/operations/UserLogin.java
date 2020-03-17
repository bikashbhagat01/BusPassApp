package operations;

import assets.AssetFactory;
import assets.User;
import java.sql.SQLException;
import java.util.Scanner;
import managers.UserManager;

public class UserLogin {
  Scanner in = new Scanner(System.in).useDelimiter("\n");

  public void showMenu(){
    // Switch Menu --> Existing, New or Exit
    boolean exCode = false;
    String choice = "";
    while(!exCode) {
      System.out.println( "\n1. Existing User " +
                          "\n2. New User" +
                          "\n3. Exit\n");
      choice = in.nextLine();
      switch (choice) {
        case("1"):
          setLoginDetails();
          break;

        case("2"):
          createAccount();
          break;


      }
    }
  }
  private void login(int userId, String password) throws SQLException, ClassNotFoundException {
    // If the user and passoword exist in user table
    if(Validate.validateUserPassword(userId, password)) {
      UserOperation.showMenu(userId);
    }
  }

  private void createAccount() throws SQLException, ClassNotFoundException {
    // use the validate class to validate if user exists or not
    System.out.println("Enter Employee ID : \n");
    int employeeId = in.nextInt();
    if(Validate.validateUsername(employeeId)){
      System.out.println("User Id for " + employeeId + " already exists\n");
      showMenu();
    } else {
      /*Get USer details*/
      User user = AssetFactory.getUserInstance(113322, "Cynitha",
                                    "PV", "cyniuthd@amazon.com"
                            , "9876578888", "gadagd",
              "adadad", "B+", "xffagdajh");
      System.out.println(user.getEmployeeId() + "has been created !");
      UserManager.create(user); // Create a Record in the DB
    }
    // Create a new user object if no user of same id exists
    // Pass the user object to UserManager.create()
  }

  public void setLoginDetails() throws SQLException, ClassNotFoundException {
    System.out.println("Enter Employee ID : \n");
    int userId=in.nextInt();
    System.out.println("Enter Password : \n");
    String password=in.next();
    login(userId, password);
  }
}
