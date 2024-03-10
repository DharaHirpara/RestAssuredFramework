package api.test;


import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;



import api.endpoints.UserEndPoints;
import api.payload.UserPOJO;
import groovyjarjarantlr4.v4.runtime.misc.LogManager;
import io.restassured.response.Response;

public class UserTests {
//call 4 method of UserEndPoint class
	
	//create global var
	Faker faker;
	UserPOJO userPayload;
	public Logger logger;
	//create fake data
//	int id;
//	String username;
//	String firstName;
//	String lastName;
//	String email;
//	String password;
//	String phone;
//     int userStatus;
	
	@BeforeClass
	public void setUp() {//this method will set/generate data by using Faker class
	//we can prepare this data for for multiple user in excel sheet its called data driven test	and need to use Apache POI can read excel file,excel utility
		//this excel sheet attached in this project by creating folder:testDataDriven, DataProvider in TestNg will take data from excel sheet
		//then this data provider will provide data to test case/test method
		
		faker=new Faker();
		userPayload=new UserPOJO();
		
		userPayload.setId(faker.idNumber().hashCode());//create fake id and pass this all data to test method _test method will pass data to userendpoint class method
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger=org.apache.logging.log4j.LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
public void testPostUser(){//test method
		
		logger.info( "**************Creating user **********************");
		Response response = UserEndPoints.createUser(userPayload);//call createUser method of UserEndPoints class 
		//whatever payload generate above pass it here
		
		response.then()//then means after send req,validate res
		.log()
		.all();
		
		Assert.assertEquals(response.getStatusCode(),200);//validate status code
		logger.info( "************** user is created **********************");
		
		}
	@Test(priority=2)
	public void testGetUserByName(){//test method
		
		logger.info( "**************Reading user Info **********************");
			Response response = UserEndPoints.readUser(this.userPayload.getUsername());//here we will not pass payload but extract username from payload
			//whatever payload generate above pass usename from it here
			
			response.then()//then means after send req,validate res
			.log()
			.all();
			
			Assert.assertEquals(response.getStatusCode(),200);//validate status code
			logger.info( "**************User info is displayed **********************");
			
			}
	@Test(priority=3)
	public void testupdateUserByName(){//update  data of this userName by using paylaod
		
		logger.info( "**************Updating user **********************");
		//update data by using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
			Response response = UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);//here we will not pass payload but extract username from payload
			//whatever payload generate above pass usename from it here
			
			response.then()//then means after send req,validate res
			.log()
			.body()
			.statusCode(200);//this all chai assertion which is comes under rest assured
		//or	
			Assert.assertEquals(response.getStatusCode(),200);//validate status code
			
			logger.info( "************** User is updated **********************");
			
			//check that data update or not by GET
			Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());//here we will not pass payload but extract username from payload
			//whatever payload generate above pass usename from it here
			
			response.then()//then means after send req,validate res
			.log()
			.all();
			
			Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);//validate status code
			
			}
	@Test(priority=4)
	public void testdeleteUserByName(){//update  data of this userName by using paylaod,this is testng assertion
		
		logger.info( "**************Deleting User **********************");
		
			Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());//here we will not pass payload but extract username from payload
			//whatever payload generate above pass usename from it here
			
			response.then()//then means after send req,validate res
			.log()
			.all();
			
			Assert.assertEquals(response.getStatusCode(),200);//validate status code
			
			logger.info( "************** User Deleted **********************");
			}
	
	
	
}
