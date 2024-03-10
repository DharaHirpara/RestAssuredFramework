package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

//import static io.restassured.RestAssured.when;
import api.payload.UserPOJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {
	// this class created for perform Create,Read,Update,Delete 
	// userEndPoints-->contain CRUD ,methods
	// work as a page class in selenium
	
	//call this method in createUser,readUser,updateUser,deleteUser
	public static ResourceBundle getURL(){//get url from  properties file and return ResourceBundle of obj
		ResourceBundle routes=ResourceBundle.getBundle("routes");//load properties file,routes is properties file name,no need to supply path of properties file
		return routes;
	}
	

	// method for create user
	public static Response createUser(UserPOJO payload) // not void but will return Response
	
	{
		String post_url = getURL().getString("post_url");//from this getURL() we are getting url in form of string so getString
		
		Response response = given()// pre-requisite
				                .contentType(ContentType.JSON)
				                .accept(ContentType.JSON)
				                .body(payload)
			               .when()// req
				                .post(post_url);// send request and will return reposnse
		                   return response;
		// post_url var from routes class and its static so use other class var by using
		// class name
		// this method will return response but i will not validate it here but will do
		// it in test class
	}

	public static Response readUser(String userName) {// not void but will return Response

		String get_url = getURL().getString("get_url");//from this getURL() we are getting url in form of string so getString
		
		Response response = 
			given()// pre-requisite
				.pathParam("username", userName).when()// req
				.get(get_url);// send request and will return reposnse
		return response;
		// post_url var from routes class and its static so use other class var by using
		// class name
		// this method will return response but i will not validate it here but will do
		// it in test class
	}

	

	public static Response updateUser(String userName, UserPOJO payload) {// not void but will return Response

		String update_url = getURL().getString("update_url");//from this getURL() we are getting url in form of string so getString

		Response response = 
			given()// pre-requisite
				.contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("username", userName)
				.body(payload).when()// req
				.put(update_url);// send request and will return reposnse
		return response;
		// post_url var from routes class and its static so use other class var by using
		// class name
		// this method will return response but i will not validate it here but will do
		// it in test class
	}

	public static Response deleteUser(String userName) {// not void but will return Response

		String delete_url = getURL().getString("delete_url");//from this getURL() we are getting url in form of string so getString

		Response response =
			given()// pre-requisite
				.pathParam("username", userName).when()// req
				.delete(delete_url);// send request and will return reposnse
		return response;
		// post_url var from routes class and its static so use other class var by using
		// class name
		// this method will return response but i will not validate it here but will do
		// it in test class
	}

}
