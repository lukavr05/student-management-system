package uk.ac.rhul.cs2800.exceptions;

/**
 * An exception thrown when no grade can be found.
 */
public class NoGradeAvailableException extends Exception {
  private String errorMessage;
  private static final long serialVersionUID = 1L;

  /**
   * Constructor for the exception.
   *
   * @param errorMessage the specific message to be returned.
   */
  public NoGradeAvailableException(String errorMessage) {
    super();
    this.errorMessage = errorMessage;
  }

  @Override
  public String getMessage() {
    return errorMessage;
  }

}
