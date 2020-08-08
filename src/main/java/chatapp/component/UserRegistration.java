package chatapp.component;

import chatapp.enumerations.UserType;
import chatapp.model.User;

public interface UserRegistration {

	/*
	 * This method adds a validated user to database
	 * 
	 * @param User user
	 * 
	 * @param UserType userType
	 * 
	 * @throws InavalidInputException, NonRetryableException, RetryableException
	 */
	public void register(User user, UserType userType) throws Exception;
}
