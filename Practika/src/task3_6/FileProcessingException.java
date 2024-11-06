package task3_6;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class FileProcessingException extends Exception {
  public FileProcessingException(String message) {
    super(message);
  }

  public FileProcessingException(String message, Throwable cause) {
    super(message, cause);
  }
}

