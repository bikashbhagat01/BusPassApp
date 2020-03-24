package dbTools;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Validator {

  private static String sqlQuery;

  private static int PASSWORD_LENGTH = 8;

  public static boolean isValidPassword(String password) {

    if (password.length() < PASSWORD_LENGTH) {
      return false;
    }

    int charCount = 0;
    int numCount = 0;

    for (int i = 0; i < password.length(); i++) {
      char ch = password.charAt(i);

      if (isNumeric(ch)) {
        numCount++;
      } else if (is_Letter(ch)) {
        charCount++;
      } else {
        return false;
      }
    }
    return (charCount >= 2 && numCount >= 2);
  }

  public static boolean is_Letter(char ch) {
    ch = Character.toUpperCase(ch);
    return (ch >= 'A' && ch <= 'Z');
  }


  public static boolean isNumeric(char ch) {
    return (ch >= '0' && ch <= '9');
  }

  public static boolean isValidComment(String comment) {
    if (comment.length() <= 100 && comment.length() > 1) {
      return true;
    }
    return false;
  }

  public static boolean isCommentBlank(String comment) {
    if (comment.trim().equals("")) {
      return true;
    }
    return false;
  }

  public static boolean isValidEmail(String emailAddress) {
    if (emailAddress == null) {
      return false;
    }

    String emailRegex = "^[a-zA-Z0-9]+" +
                        "([a-zA-Z0-9_+&-]+)@" +
                        "([a-zA-Z0-9-]+\\.)+[a-z" +
                        "A-Z]{2,7}$";

    Pattern pat = Pattern.compile(emailRegex);

    return pat.matcher(emailAddress).matches();
  }

  public static boolean isValidNameLength(String firstName) {
    if(firstName.length() < 50){
      return true;
    }
    return false;
  }


  public static boolean isValidPhoneNo(String contactNo) {
    for(int i = 0; i < contactNo.length(); i++) {
      if(!isNumeric(contactNo.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  public static boolean isValidPhoneNoLength(String contactNo) {
    if(contactNo.length() > 12) {
      return false;
    }

    if(contactNo.length() < 10) {
      return false;
    }

    return true;
  }

  public static boolean isValidFullNameLength(String fullName) {
    if(fullName.length() < 50) {
      return true;
    }
    return false;
  }


  public static boolean isValidName(String name) {
    for(int i = 0; i < name.length(); i++) {
      if(isNumeric(name.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  public static boolean isValidBloodGroup(String bloodGroup) {

    List<String> bloodGroups = new ArrayList<String>();
    bloodGroups.add("APOSITIVE");
    bloodGroups.add("BPOSITIVE");
    bloodGroups.add("OPOSITIVE");
    bloodGroups.add("ABPOSITIVE");
    bloodGroups.add("ANEGATIVE");
    bloodGroups.add("BNEGATIVE");
    bloodGroups.add("ONEGATIVE");
    bloodGroups.add("ABNEGATIVE");

    if( bloodGroups.contains(bloodGroup.trim().toUpperCase())) {
      return true;
    }
    return false;
  }


  public static boolean arePasswordsMatching(String password, String confirmedPassword) {
    return password.equals(confirmedPassword);
  }
}

/**  "1. A password must have at least eight characters.\n" +
     "2. A password consists of only letters and digits.\n" +
     "3. A password must contain at least two digits \n" +
     "Input a password (You are agreeing to the above Terms and Conditions.):
 */