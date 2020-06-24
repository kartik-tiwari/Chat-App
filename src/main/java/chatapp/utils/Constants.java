package chatapp.utils;

public class Constants {

	public class Attribute {
		public static final String MESSAGE = "message";
		public static final String GENDERS = "genders";
		public static final String USER = "user";
		public static final String CURRENT_USER="currentUser";
		public static final String TARGET_USER="targetUser";
		
	}
	public class Directory{
		public static final String CONVERSATION="conversation/";
		public static final String LANDING="landing/";
		
		
	}
	public class VIEWS{
		public static final String USER_EDIT="editView";
		public static final String USER_HOME="homeView";
		public static final String USER_LOGIN="loginView";
		public static final String USER_WELCOME="welcomeView";
		public static final String USER_PROFILE="profileView";
		public static final String USER_REGISTER="registerView";
		
		
		

		
	}

	public class SuccessMessage {

		public static final String SUCCESFUL_REGISTRATION = "User registered succesfully";
		public static final String SUCCESFUL_UPDATION = "Profile edited succesfully";

	}

	public class ErrorsMessage {

		// Invalid Input Exception
		public static final String INVALID_USERNAME_LENGTH = "User name length invalid";
		public static final String INVALID_USERNAME_CHARACTERS = "User name characters invalid";
		public static final String INVALID_FIRSTNAME_LENGTH = "First name length invalid";
		public static final String INVALID_FIRSTNAME_CHARACTERS = "First name characters invalid";
		public static final String INVALID_LASTNAME_LENGTH = "Last name length invalid";
		public static final String INVALID_LASTNAME_CHARACTERS = "Last name characters invalid";
		public static final String INVALID_USER_AGE = "User age invalid";
		public static final String INVALID_PASSWORD_LENGTH = "Password invalid";

		// Invalid login credentials
		public static final String INVALID_USERNAME_PASSWORD = "Wrong username or password";

		// Username not found
		public static final String USERNAME_NOT_FOUND = "User not found";

		// Username not avaliable

		public static final String USERNAME_NOT_AVAILABLE = "Username not available";

		// Retryable Exception message
		public static final String RETRYABLE_EXCEPTION_MESSAGE = "Something went wrong retry";

		// Retryable Exception message
		public static final String NON_RETRYABLE_EXCEPTION_MESSAGE = "Internal Error can't continue";

	}

	public class UserValidationParameters {
		public static final int MIN_USERNAME_LENGTH = 5;
		public static final int MAX_USERNAME_LENGTH = 15;
		public static final int MIN_FIRSTNAME_LENGTH = 1;
		public static final int MAX_FIRSTNAME_LENGTH = 20;
		public static final int MIN_LASTNAME_LENGTH = 1;
		public static final int MAX_LASTNAME_LENGTH = 20;
		public static final int MIN_USER_AGE = 16;
		public static final int MAX_USER_AGE = 99;
		public static final int MIN_PASSWORD_LENGTH = 5;
		public static final int MAX_PASSWORD_LENGTH = 20;
	}

}
