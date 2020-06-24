package chatapp.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chatapp.dao.DBAccessor;
import chatapp.enumerations.UserType;
import chatapp.exceptions.DependencyFailureException;
import chatapp.exceptions.InternalException;
import chatapp.exceptions.NonRetryableException;
import chatapp.exceptions.RetryableException;
import chatapp.model.User;
import chatapp.utils.UserValidation;

@Component
public class UserRegistrationImpl implements UserRegistration {
	@Autowired
	private UserValidation validation;
	@Autowired
	private DBAccessor<User> dbAccessor;

	/*
	 * This method adds a validated user to database
	 * 
	 * @param User user
	 * 
	 * @throws InavalidInputException, NonRetryableException, RetryableException
	 */
	public void register(User user, UserType userType) throws Exception {

		try {
			// validate before registering
			validation.validate(user, userType);

			// Save user if validation passes
			dbAccessor.save(user);
		} catch (InternalException exception) {
			throw new NonRetryableException(exception.getMessage());
		} catch (DependencyFailureException exception) {
			throw new RetryableException(exception.getMessage());
		}

	}
}
