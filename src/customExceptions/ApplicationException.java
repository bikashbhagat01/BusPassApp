package customExceptions;

// Catch from where calling
// Application Exceptions - SQL Query Incorrect, sent to Query Executor or Validate
// Catch from AppDriver

public class ApplicationException extends Exception {

  public ApplicationException(String message) {
    super(message);
  }
}
