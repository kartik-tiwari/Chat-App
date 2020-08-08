package chatapp.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chatapp.dao.DBAccessor;
import chatapp.enumerations.UserType;
import chatapp.exceptions.InvalidInputException;
import chatapp.model.User;

@Component
public class UserValidationImpl implements UserValidation {

	@Autowired
	private DBAccessor<User> dbAccessor;

	/*
	 * Validates user based on usertype
	 * 
	 * @param User user
	 * 
	 * @param usertType UserType
	 * 
	 * @throws DynamoDb exception
	 * 
	 * @throws InvalidInputException
	 */
	public void validate(User user, UserType userType) throws Exception {

		if (userType == UserType.UNREGISTERED) {
			checkAvailableUserName(user.getUserName());
		}

		validateUserName(user.getUserName());

		validateFirstName(user.getFirstName());

		validateLastName(user.getLastName());

		validateDateOfBirth(user.getDateOfBirth());

		validatePassword(user.getPassword());

	}

	public void validateUserName(String userName) throws Exception {
		// Length
		if (userName.length() > Constants.UserValidationParameters.MAX_USERNAME_LENGTH
				|| userName.length() < Constants.UserValidationParameters.MIN_USERNAME_LENGTH) {
			throw new InvalidInputException(Constants.ErrorsMessage.INVALID_USERNAME_LENGTH);
		}

		// Alphanumeric and Underscore
		for (int i = 0; i < userName.length(); i++) {
			if (!Character.isLetterOrDigit(userName.charAt(i)) && userName.charAt(i) != '_') {
				throw new InvalidInputException(Constants.ErrorsMessage.INVALID_USERNAME_CHARACTERS);
			}
		}

	}

	public void checkAvailableUserName(String userName) throws Exception {

		User existingUser = dbAccessor.load(User.class, userName);
		if (existingUser != null) {
			throw new InvalidInputException(Constants.ErrorsMessage.USERNAME_NOT_AVAILABLE);
		}

	}

	public void validateFirstName(String firstName) throws Exception {

		if (firstName.length() < Constants.UserValidationParameters.MIN_FIRSTNAME_LENGTH
				|| firstName.length() > Constants.UserValidationParameters.MAX_FIRSTNAME_LENGTH) {
			throw new InvalidInputException(Constants.ErrorsMessage.INVALID_FIRSTNAME_LENGTH);
		}

		for (int i = 0; i < firstName.length(); i++) {
			if (!Character.isAlphabetic(firstName.charAt(i))) {
				throw new InvalidInputException(Constants.ErrorsMessage.INVALID_FIRSTNAME_CHARACTERS);
			}
		}

	}

	public void validateLastName(String lastName) throws Exception {
		if (lastName.length() < Constants.UserValidationParameters.MIN_LASTNAME_LENGTH
				|| lastName.length() > Constants.UserValidationParameters.MAX_LASTNAME_LENGTH) {
			throw new InvalidInputException(Constants.ErrorsMessage.INVALID_LASTNAME_LENGTH);
		}

		// Alphabets Only

		for (int i = 0; i < lastName.length(); i++) {
			if (!Character.isAlphabetic(lastName.charAt(i))) {
				throw new InvalidInputException(Constants.ErrorsMessage.INVALID_LASTNAME_CHARACTERS);
			}
		}

	}

	public void validateDateOfBirth(Date dateOfBirth) throws Exception {
		Date today = new Date();
		if (today.before(dateOfBirth)) {
			throw new InvalidInputException(Constants.ErrorsMessage.INVALID_USER_AGE);
		}
		// Above 16
		if (today.getYear() - dateOfBirth.getYear() < 16) {
			throw new InvalidInputException(Constants.ErrorsMessage.INVALID_USER_AGE);
		}
	}

	public void validatePassword(String password) throws Exception {
		if (password.length() < 5 || password.length() > 20) {
			throw new InvalidInputException(Constants.ErrorsMessage.INVALID_PASSWORD_LENGTH);
		}

	}
}
