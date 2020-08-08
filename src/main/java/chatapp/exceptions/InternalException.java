package chatapp.exceptions;

/**
 * This is a base exception type for all exceptions thrown by DARE Service due to
 * internal errors.
 */
public class InternalException extends Exception {
	
	public InternalException(String message) {
		
		super(message);
		
	}
}
