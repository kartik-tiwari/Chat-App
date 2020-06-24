package chatapp.utils;

import java.util.Date;

import chatapp.enumerations.UserType;
import chatapp.model.User;

public interface UserValidation {
	
	public void validate(User user, UserType userType) throws Exception;

	public void validateUserName(String userName) throws Exception;

	public void checkAvailableUserName(String userName) throws Exception;
	
	public void validateFirstName(String firstName) throws Exception;
	
	public void validateLastName(String lastName) throws Exception;
	
	public void validateDateOfBirth(Date dateOfBirth) throws Exception;
	
	public void validatePassword(String password) throws Exception;
}
