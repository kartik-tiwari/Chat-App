package chatapp.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;

import chatapp.dao.DBAccessor;
import chatapp.exceptions.DependencyFailureException;
import chatapp.exceptions.InternalException;
import chatapp.exceptions.InvalidInputException;
import chatapp.model.User;
import chatapp.utils.Constants;

@Component
public class UserMangement {

	@Autowired
	private DBAccessor<User> dbAccessor;

	/*
	 * This method loads user of userName
	 * 
	 * @param String userName
	 * 
	 * @return User user
	 * 
	 * @throws InvalidInputException, InternalException, DependencyFalureException
	 */
	public User getUserByUserName(final String userName) throws Exception {
		try {
			User user = dbAccessor.load(User.class, userName);
			if (user != null) {
				return user;
			} else {
				throw new InvalidInputException(Constants.ErrorsMessage.USERNAME_NOT_FOUND);
			}
		} catch (ResourceNotFoundException exception) {
			throw new InternalException(exception.getMessage());
		} catch (SdkClientException exception) {
			throw new DependencyFailureException(exception.getMessage());
		}
	}
}
