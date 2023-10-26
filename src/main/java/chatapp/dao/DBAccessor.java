package chatapp.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;



@Component
public class DBAccessor<T> {
	
	@Autowired
	private DynamoDBMapper mapper;
	
	/*
	 * This method saves a record DynamoDb table
	 * @param T t
	 * @throws DynamoDb Exceptions
	 */
	public void save(T t) throws Exception{
		mapper.save(t);
	}
	
	/*
	 * This method load a record with provided hashKey in class T from DynamoDb table
	 * @param String hashKey
	 * @return Object of T or null if no record is found
	 * @throws DynamoDbException
	 */
	public T load(Class<T> classType, String hashKey) throws Exception{
		return mapper.load(classType, hashKey);
	}


    public void delete(T user) {
		mapper.delete(user);
    }
}
