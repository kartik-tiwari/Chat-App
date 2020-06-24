package chatapp.configuration;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration

public class DynamoDBConfig {

	private String awsAccessKey = "AKIAR2AOKZ3ORJI7KA53";
	private String awsSecretKey = "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";
	private String awsRegion = "us-east-2";
	private String awsDynamoDBEndPoint = "dynamodb.us-east-2.amazonaws.com";

	@Bean
	public AmazonDynamoDB dynamoDBClient() {
		return AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsDynamoDBEndPoint, awsRegion))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
				.build();
	}

	@Bean
	public DynamoDBMapper mapper(AmazonDynamoDB dynamoDBClient) {
		return new DynamoDBMapper(dynamoDBClient);
	}

}
