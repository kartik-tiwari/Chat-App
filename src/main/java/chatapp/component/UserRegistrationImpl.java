package chatapp.component;

import chatapp.exceptions.*;
import chatapp.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chatapp.dao.DBAccessor;
import chatapp.enumerations.UserType;
import chatapp.model.User;
import chatapp.utils.UserValidation;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Objects;

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


	public void delete(String userName)  {
		User user = null;
		try {
			user = dbAccessor.load(User.class, userName);
			if (Objects.isNull(user)) {
				throw new InvalidInputException(Constants.ErrorsMessage.USERNAME_NOT_FOUND);
			}
			dbAccessor.delete(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
