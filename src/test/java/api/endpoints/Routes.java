package api.endpoints;

//this is main URL
//swagger URI-->https://petstore.swagger.io

//this is base url till io and endpoints
//create user(post)--->https://petstore.swagger.io/v2/user
//get user(get)--->https://petstore.swagger.io/v2/user/{username}
//update user(put)--->https://petstore.swagger.io/user/{username}
//Delete user(Delete)--->https://petstore.swagger.io/user/{username}
				
public class Routes {
//maintain only Url from swagger or i can create separate properties file in test resource and there store all url
	
	//create var to store base url which is common for all method
	public static String base_url="https://petstore.swagger.io";
	
	//Url for user model--->base url+endpoint ,for send this all req we need end points method so create separate class userEndPoints-->contain CRUD ,methods
	public static String post_url=base_url+"/v2/user";//concetanation
	public static String get_url=base_url+"/v2/user/{username}";//get_url is pathparameter
	public static String update_url=base_url+"/v2/user/{username}";
	public static String delete_url=base_url+"/v2/user/{username}";
	
	
	//Url for Pet model--->base url+endpoint 
	
	
	//Url for store model--->base url+endpoint 
}
