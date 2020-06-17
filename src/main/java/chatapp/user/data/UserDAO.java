package chatapp.user.data;

public interface UserDAO {
	
	public void writeRecord(User user) throws Exception;
	
	public User loadRecord(String userName) throws Exception;
}
