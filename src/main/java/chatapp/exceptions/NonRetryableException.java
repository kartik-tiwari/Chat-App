package chatapp.exceptions;

/**
 * This exception is thrown when any of the dependencies fails with a non-retryable
 * exception where retrying will not work.
 */
public class NonRetryableException extends Exception {
	
	public NonRetryableException(String message) {
		
		super(message);
	
	}
}
