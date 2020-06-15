package spring.chatapp.user.data;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
@Component
public class DynamoDbDAO implements UserDAO  {
	
	
	public void writeRecord(User user) {
		mapper.save(user);
	}
	
	public User loadRecord(String userName) {
		return mapper.load(User.class,userName);
	}
	
	
	
	
	
	
	
	
	private String awsAccessKey="hidden";
	private String awsSecretKey="hidden";
	private String awsRegion="hidden";
	private String awsDynamoDBEndPoint="hidden";

	public AmazonDynamoDB amazonDynamoDBConfig() {
		return AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsDynamoDBEndPoint, awsRegion))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
				.build();
	}
	DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDBConfig());



}
