package task3_6;

public class DataProcessingException extends Exception {
  public DataProcessingException(String message) {
    super(message);
  }

  public DataProcessingException(String message, Throwable cause) {
    super(message, cause);
  }
}
