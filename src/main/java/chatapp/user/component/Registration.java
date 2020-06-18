package chatapp.user.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chatapp.dao.DbAccessor;
import chatapp.user.model.User;
import chatapp.utils.Result;
@Component
public class Registration {
	@Autowired
	Validation validation;
	@Autowired
	DbAccessor<User> dbAccessor;
	
	public Result register(User user) throws Exception {
		
		//Validate User
		Result validationResult=validation.validate(user);
		if(validationResult.isSuccess()) {
			dbAccessor.save(user);			
			return new Result(true, "User Succesfully Registered",null);
		}	
		return new Result(false,validationResult.getMessage(),null);		
	}
}
