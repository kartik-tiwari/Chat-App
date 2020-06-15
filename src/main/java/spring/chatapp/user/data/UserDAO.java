package spring.chatapp.user.data;

public interface UserDAO {
	
	void writeRecord(User user);
	
	User loadRecord(String userName);
}
