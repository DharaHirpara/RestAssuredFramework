package api.endpoints;

import static io.restassured.RestAssured.given;
import api.payload.UserPOJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.when;

public class UserEndPoints {
	// for send this all req we need end points method so create separate class
	// userEndPoints-->contain CRUD ,methods
	// work as a page class in selenium

	// method for create user
	public static Response createUser(UserPOJO payload) {// not void but will return Response

		Response response = given()// pre-requisite
				.contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()// req
				.post(Routes.post_url);// send request and will return reposnse
		return response;
		// post_url var from routes class and its static so use other class var by using
		// class name
		// this method will return response but i will not validate it here but will do
		// it in test class
	}

	public static Response readUser(String userName) {// not void but will return Response

		Response response = 
			given()// pre-requisite
				.pathParam("username", userName).when()// req
				.get(Routes.get_url);// send request and will return reposnse
		return response;
		// post_url var from routes class and its static so use other class var by using
		// class name
		// this method will return response but i will not validate it here but will do
		// it in test class
	}

	

	public static Response updateUser(String userName, UserPOJO payload) {// not void but will return Response

		Response response = 
			given()// pre-requisite
				.contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("username", userName)
				.body(payload).when()// req
				.put(Routes.update_url);// send request and will return reposnse
		return response;
		// post_url var from routes class and its static so use other class var by using
		// class name
		// this method will return response but i will not validate it here but will do
		// it in test class
	}

	public static Response deleteUser(String userName) {// not void but will return Response

		Response response =
			given()// pre-requisite
				.pathParam("username", userName).when()// req
				.delete(Routes.delete_url);// send request and will return reposnse
		return response;
		// post_url var from routes class and its static so use other class var by using
		// class name
		// this method will return response but i will not validate it here but will do
		// it in test class
	}

}
