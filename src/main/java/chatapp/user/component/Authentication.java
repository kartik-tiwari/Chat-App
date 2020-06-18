package chatapp.user.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chatapp.dao.DbAccessor;

import chatapp.user.model.User;
import chatapp.utils.Result;

@Component
public class Authentication {
	
	@Autowired
	DbAccessor<User> dbAccesor;
	
	public User getUserByUserName(String userName) throws Exception {
		
			return dbAccesor.load(User.class, userName);
	}
	
	public Result authenticate(String userName, String password) throws Exception {
		
		User user=getUserByUserName(userName);
		if(user==null) {
			
			return new Result(false, "No User Found",null);
		}
		
		if(password.equals(user.getPassword())) {
			
			return new Result(true, "User Succesfully logged in", user);
			
		}
		else {
			
			return new Result(false, "Wrong Username or password", null);
			
		}
		
	}
}
