package com.Cartesian.exception;


/**
* Custom exception for invalid set operations
* Thrown when set validation fails or invalid operations are attempted
*/
public class InvalidSetException extends Exception {
  
  /**
   * Constructs a new InvalidSetException with the specified detail message
   * 
   * @param message The detail message explaining the exception
   */
  public InvalidSetException(String message) {
      super(message);
  }
  
  /**
   * Constructs a new InvalidSetException with the specified detail message and cause
   * 
   * @param message The detail message
   * @param cause The cause of this exception
   */
  public InvalidSetException(String message, Throwable cause) {
      super(message, cause);
  }
  
  /**
   * Constructs a new InvalidSetException with a cause
   * 
   * @param cause The cause of this exception
   */
  public InvalidSetException(Throwable cause) {
      super(cause);
  }
}
