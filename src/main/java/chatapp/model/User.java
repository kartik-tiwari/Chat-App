package chatapp.model;

import java.util.Date;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

import chatapp.enumerations.Gender;
import lombok.Getter;
import lombok.Setter;
@DynamoDBTable(tableName = "user")
@Getter @Setter
public class User {
	@DynamoDBHashKey(attributeName = "userName")
	private String userName;
	
	private String firstName;
	
	private String lastName;
	
	private Date dateOfBirth;
	
	@DynamoDBTypeConvertedEnum
	private Gender gender;
	
	private String password;
	
}
