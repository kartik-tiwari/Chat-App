package chatapp.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import chatapp.exceptions.DependencyFailureException;
import chatapp.exceptions.InternalException;
import chatapp.exceptions.InvalidInputException;
import chatapp.exceptions.NonRetryableException;
import chatapp.exceptions.RetryableException;
import chatapp.model.User;
import chatapp.utils.Constants;

@Component
public class UserAuthenticationImpl implements UserAuthentication {

	@Autowired
	private UserMangement userManagement;

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
	public User authenticate(String userName, String password) throws Exception {
		try {
			User user = userManagement.getUserByUserName(userName);
			if (password.equals(user.getPassword())) {
				return user;
			} else {
				throw new InvalidInputException(Constants.ErrorsMessage.INVALID_USERNAME_PASSWORD);
			}
		} catch (InternalException exception) {
			throw new NonRetryableException(exception.getMessage());
		} catch (DependencyFailureException exception) {
			throw new RetryableException(exception.getMessage());
		} catch (InvalidInputException exception) {
			throw new InvalidInputException(Constants.ErrorsMessage.INVALID_USERNAME_PASSWORD);
		}

	}
}
