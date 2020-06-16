package chatapp.user.data;
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
	
	
	
	
	
	
	
	
	private String awsAccessKey="AKIAR2AOKZ3ORJI7KA53";
	private String awsSecretKey="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";
	private String awsRegion="us-east-2";
	private String awsDynamoDBEndPoint="dynamodb.us-east-2.amazonaws.com";

	public AmazonDynamoDB amazonDynamoDBConfig() {
		return AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsDynamoDBEndPoint, awsRegion))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
				.build();
	}
	DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDBConfig());



}
