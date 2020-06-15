package spring.chatapp.user.component;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;

import lombok.Setter;
import spring.chatapp.user.data.DynamoDbDAO;
import spring.chatapp.user.data.User;


@Getter
@Setter

@Component
public class Validation {
	
	@Autowired
	DynamoDbDAO dynamoDbDAO;
	public Result validate(User user) {
		// Validate User Name

		// Length
		String userName = user.getUserName();
		if (userName.length() > 20 || userName.length() < 3) {
			return new Result(false, "Invalid User Name length",null);
		}

		// Alphanumeric and Underscore
		for (int i = 0; i < userName.length(); i++) {
			if (!Character.isLetterOrDigit(userName.charAt(i)) && userName.charAt(i) != '_') {
				return new Result(false, "Invalid User Name Characters",null);
			}
		}
		// Available
		if(dynamoDbDAO.loadRecord(user.getUserName())!=null) {
			return new Result(false, "User Name Taken",null);
		}

		// Validate First Name

		// Length
		String name = user.getFirstName();

		if (name.length() < 1 || name.length() > 20) {
			return new Result(false, "Invalid First Name length",null);
		}

		// Alphabets Only

		for (int i = 0; i < name.length(); i++) {
			if (!Character.isAlphabetic(name.charAt(i))) {
				return new Result(false, "Invalid First Name Charactres",null);
			}
		}

		// Validate Last Name
		name = user.getLastName();

		if (name.length() < 1 || name.length() > 20) {
			return new Result(false, "Invalid Last Name length",null);
		}

		// Alphabets Only

		for (int i = 0; i < name.length(); i++) {
			if (!Character.isAlphabetic(name.charAt(i))) {
				return new Result(false, "Invalid Last Name Charactres",null);
			}
		}
		
		//Validate age
		//Not past
		Date today= new Date();
		if(today.before(user.getDateOfBirth())) {
			return new Result(false, "Birth day should be in past",null);
		}
		// Above 16
		if(today.getYear()-user.getDateOfBirth().getYear()<16) {
			return new Result(false, "Age should be atleast 16",null);
		}
		
		// Validate password
		String password = user.getPassword();
		// Length
		if(password.length()<5 || password.length()>20) {
			return new Result(false, "Invalid Password",null);
		}
		
		
		// Passed All validation test
		return new Result(true, "User Valid",null);
	}
}
