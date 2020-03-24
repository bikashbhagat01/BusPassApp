package inputHelpers;

import java.util.Scanner;
/** Package inputHelpers is Not Used
 *  Proposed interface and classes for Validating console inputs. To improve re-usability and utilize strategy
 *  **/

interface FieldValidator {
  boolean validate(String s);
  void warn();
}

// Refactoring to take inputs from console
public class InputHelper {
  Scanner sc;

  InputHelper() {
    this.sc = new Scanner(System.in);
  }

  protected void print(String message) {
    System.out.println(message);
  }

  // Takes a heading string. Shows the user message before taking the string
  public String takeString(String message) {
    this.print(message);
    String val = this.sc.nextLine();
    return val;
  }

  // Takes string and keeps taking until validation is passed
  public String takeStringWithValidator(String message, FieldValidator v) {
    this.print(message);
    boolean exit = false;
    String val = "";
    // Keeps on taking value and run validate. If validated --> exits, else takes a value again
    while(!exit) {
      val = this.sc.nextLine();
      if (v.validate(val)) {
        exit = true;
      } else {
        // User is frustrated with wrong email, he can type exit to quit
        if (val.equalsIgnoreCase("exit")) {
          exit = true;
        }
        v.warn();
      }
    }

    return val;
  }

}
