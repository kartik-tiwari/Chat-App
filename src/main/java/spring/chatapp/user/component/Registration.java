package spring.chatapp.user.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring.chatapp.user.data.DynamoDbDAO;
import spring.chatapp.user.data.User;
@Component
public class Registration {
	@Autowired
	Validation validation;
	@Autowired
	DynamoDbDAO dynamoDbDAO;
	
	public Result register(User user) {
		
		//Validate User
		Result validationResult=validation.validate(user);
		
		if(validationResult.isSuccess()) {
			dynamoDbDAO.writeRecord(user);
			return new Result(true, "User Succesfully Registered",null);
		}	
		return new Result(false,validationResult.getMessage(),null);		
	}
}
