package chatapp.user.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chatapp.user.data.DynamoDbDAO;
import chatapp.user.data.User;
import chatapp.utils.Result;
@Component
public class Registration {
	@Autowired
	Validation validation;
	@Autowired
	DynamoDbDAO dynamoDbDAO;
	
	public Result register(User user) throws Exception {
		
		//Validate User
		Result validationResult=validation.validate(user);
		
		if(validationResult.isSuccess()) {
			
			try{
				dynamoDbDAO.writeRecord(user);
			}
			catch (Exception exception) {
				return new Result(false, "Connection Error!!", null);
			}
			
			return new Result(true, "User Succesfully Registered",null);
		}	
		return new Result(false,validationResult.getMessage(),null);		
	}
}
