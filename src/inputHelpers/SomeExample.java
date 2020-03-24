package inputHelpers;

/** Package inputHelpers is Not Used
 * Proposed implementation of Interface for FieldValidaor and input helper in action
 * */
class UserIdValidator implements FieldValidator {

  @Override
  public boolean validate(String s) {
    if (s.contains("78")) {
      return false;
    }

    return true;
  }

  @Override
  public void warn() {
    System.out.println("User id cannot contain 78");
  }
}


// An Operation Like class. Acts as client for inputHelper
public class SomeExample {
  InputHelper inputHelper;

  SomeExample() {
    this.inputHelper  = new InputHelper();
  }

  public void tryLogin() {
    // Lets Validate
    String userid = this.inputHelper.takeStringWithValidator("Please enter user id", new UserIdValidator());
    System.out.println(userid);
  }

  public static void main(String[] args) {
    SomeExample s = new SomeExample();
    s.tryLogin();
  }
}
