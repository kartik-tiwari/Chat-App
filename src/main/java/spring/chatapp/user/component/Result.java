package spring.chatapp.user.component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import spring.chatapp.user.data.User;

@Getter @Setter
@AllArgsConstructor
public class Result {
	private boolean isSuccess;
	private String message;
	User user;
	
}
