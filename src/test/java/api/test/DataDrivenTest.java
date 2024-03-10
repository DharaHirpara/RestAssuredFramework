package api.test;



import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.UserPOJO;

import io.restassured.response.Response;

public class DataDrivenTest {

	

	@Test(priority = 1, dataProvider = "Data",dataProviderClass=DataProvider.class)
	public void testPostUser(String userID, String userName, String fName, String lName, String useremail, String pswd,
			String ph) {// create all users

		UserPOJO userPayload = new UserPOJO();

		userPayload.setId(Integer.parseInt(userID));// create fake id and pass this all data to test method _test method
													// will pass data to userendpoint class method
		userPayload.setUsername(userName);
		userPayload.setFirstName(fName);
		userPayload.setLastName(lName);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pswd);
		userPayload.setPhone(ph);

		Response response = UserEndPoints.createUser(userPayload);// call createUser method of UserEndPoints class
		// whatever payload generate above pass it here

		Assert.assertEquals(response.getStatusCode(), 200);// validate status code
	}

	

	@Test(priority = 2, dataProvider = "UserNames",dataProviderClass=DataProvider.class)
	public void testDeleteUserByName(String userName) {// delete all users by userName

		Response response = UserEndPoints.deleteUser(userName);
		// whatever payload generate above pass it here

		Assert.assertEquals(response.getStatusCode(), 200);// validate status code
	}

}
