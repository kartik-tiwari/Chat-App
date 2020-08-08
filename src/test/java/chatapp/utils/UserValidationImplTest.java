package chatapp.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import chatapp.dao.DBAccessor;
import chatapp.enumerations.Gender;
import chatapp.enumerations.UserType;
import chatapp.exceptions.InvalidInputException;
import chatapp.model.User;

@ExtendWith(MockitoExtension.class)
class UserValidationImplTest {
	
	@Mock
	private DBAccessor<User> dbAccessor;
	
	@InjectMocks
	private UserValidationImpl userValidation = new UserValidationImpl();
	
	@Test
	void testUserValidation() throws Exception{

		String userName = "testUserName";
		String firstName = "firstName1";
		String lastName = "lastName";
		Date dateOfBirth = new Date(1999, 8, 21);
		Gender gender = Gender.Male;
		String password = "password";

		User user = new User();

		user.setUserName(userName);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setGender(gender);
		user.setDateOfBirth(dateOfBirth);
		user.setPassword(password);
		
		//Mocking dependency
		Mockito.when(dbAccessor.load(User.class, userName)).thenReturn(null);


		assertThrows(InvalidInputException.class, ()->userValidation.validate(user, UserType.UNREGISTERED),"First Name Contains a numeric");

	}

}
