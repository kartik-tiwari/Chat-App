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
import org.apache.commons.codec.digest.DigestUtils;

@Component
public class UserRegistrationImpl implements UserRegistration {
	@Autowired
	private UserValidation validation;
	@Autowired
	private DBAccessor<User> dbAccessor;

	/*
	 * This method adds a validated user to database
	 * 
	 * @param User user [not final : password is hashed ]
	 * 
	 * @param UserType userType
	 * 
	 * @throws InavalidInputException, NonRetryableException, RetryableException
	 */
	public void register(User user, final UserType userType) throws Exception {

		try {
			// validate before registering
			validation.validate(user, userType);

			// Hash password before storing in DB
			String plainTextPassword = user.getPassword();
			String hashedPassword = DigestUtils.md5Hex(plainTextPassword);
			user.setPassword(hashedPassword);

			// Save user if validation passes
			dbAccessor.save(user);
		} catch (InternalException exception) {
			throw new NonRetryableException(exception.getMessage());
		} catch (DependencyFailureException exception) {
			throw new RetryableException(exception.getMessage());
		}

	}
}
