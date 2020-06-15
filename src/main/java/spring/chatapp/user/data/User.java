package spring.chatapp.user.data;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

import lombok.Getter;
import lombok.Setter;

@DynamoDBTable(tableName = "user")
@Getter @Setter
public class User {
	private String userName;
	
	private String firstName;
	
	private String lastName;
	
	private Date dateOfBirth;
	
	private Gender gender;
	
	private String password;
	
	@DynamoDBHashKey(attributeName = "userName")
	public String getUserName() {
		return this.userName;
	}
	@DynamoDBTypeConvertedEnum
	public Gender getGender() {
		return this.gender;
	}
}
