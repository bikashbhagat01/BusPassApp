package customExceptions;

public class UserException extends Exception {
  public UserException(String message, String code) {
    super(message);
  }
}