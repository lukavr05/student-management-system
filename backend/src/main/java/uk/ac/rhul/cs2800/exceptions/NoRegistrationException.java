package uk.ac.rhul.cs2800.exceptions;

/**
 * An exception thrown when students are not registered for a module.
 */
public class NoRegistrationException extends Exception {
  private static final long serialVersionUID = 1L;
  private String errorMessage;

  /**
   * The constructor for the NoRegistrationException class.
   *
   * @param errorMessage the message to be passed in.
   */
  public NoRegistrationException(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  @Override
  public String getMessage() {
    return errorMessage;
  }
}
