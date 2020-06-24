package chatapp.component;

import chatapp.model.User;

public interface UserAuthentication {
	/*
	 * This method Authenticates user by matching a right combination of username
	 * and password
	 * 
	 * @param String username
	 * 
	 * @param String password
	 * 
	 * @return User user
	 * 
	 * @throws InvalidInputException NonRetryableException, Retryable Exception
	 */
	public User authenticate(String userName, String password) throws Exception;

}
