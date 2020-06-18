package chatapp.dao;

import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
@Component
public class DbAccessor<T> {

	public void save(T t) throws Exception{
		mapper.save(t);
	}

	public T load(Class<T> classType, String hashKey) throws Exception {
		return mapper.load(classType, hashKey);
	}

	
	
	
	
	
	private String awsAccessKey = "";
	private String awsSecretKey = "";
	private String awsRegion = "";
	private String awsDynamoDBEndPoint = "";

	public AmazonDynamoDB amazonDynamoDBConfig() {
		return AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsDynamoDBEndPoint, awsRegion))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
				.build();
	}

	DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDBConfig());
}