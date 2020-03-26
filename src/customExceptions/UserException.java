package customExceptions;

// User Exceptions - String tyoe input --> varchar - Limit cross : Sql Exception
// EmployeeID - Integer --> abc - Exception,

public class UserException extends Exception {

  public UserException(String message) {
    super(message);
  }
}