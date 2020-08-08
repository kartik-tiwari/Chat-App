package chatapp.exceptions;

/**
 * This exception is thrown when any of the dependencies (datastore/dependent
 * services) errors out in spite of getting correct validated input and it can
 * be retried again.
 */
public class DependencyFailureException extends Exception {
	
	public DependencyFailureException(String message) {
		
		super(message);
	
	}
}
