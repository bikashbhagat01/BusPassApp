package operations;

public class UserLogin {

  public void showMenu(){
    // Switch Menu --> Existing, New or Exit
  }
  private boolean login(String usedId, String password) {
    // for existing user, Calls User Operations.showMenu(userId) --> True
    // use the validate class to validate if user exists or not
    return false;
  }

  private void createAccount() {
    // use the validate class to validate if user exists or not
    // Create a new user object if no user of same id exists
    // Pass the user object to UserManager.create()
  }
}
