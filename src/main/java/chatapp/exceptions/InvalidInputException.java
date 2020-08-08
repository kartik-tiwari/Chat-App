package chatapp.exceptions;

/**
 *  This is a base exception type for all exceptions thrown when the
 *  input arguments are invalid.
 *  The request should not be retried by the clients with the same inputs.
 */
public class InvalidInputException extends Exception {
	
	public InvalidInputException(String message) {
		super(message);
	}
}
