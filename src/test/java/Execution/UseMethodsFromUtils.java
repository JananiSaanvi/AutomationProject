package Execution;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.auth.InvalidCredentialsException;
import org.testng.annotations.Test;

import EndPoints.End_Points;
import Pojo_Post.RootPost;
import RequestMethods.HttpMethods;
import Utilities.TestUtilities;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UseMethodsFromUtils extends TestUtilities {
	
	@Test
	public void postRequest() throws InvalidCredentialsException  {
		
//		curl -X 'POST' \
//		  'https://reqres.in/api/register' \
//		  -H 'accept: application/json' \
//		  -H 'Content-Type: application/json' \
//		  -d '{
//		  "username": "string",
//		  "email": "string",
//		  "password": "string"
//		}'
		
		
		//RestAssured.baseURI = "https://reqres.in/api/register";
		
		//End_Points.POST_REGISTER;
		
		RestAssured.baseURI = End_Points.POST_REGISTER;
		initResAssured();
		
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("accept", "application/json");
		map.put("Content-Type", "application/json");	
	
		addHeaders(map);
		
		RootPost rootPost = new RootPost("Janani", "janani@gmail.com", "password");
		
		addReqBody(rootPost);
		
		Response response = reqType(HttpMethods.POST);
		
		int statusCode = statusCode(response);
		System.out.println("statusCode "+statusCode);
		
		String statusLine = statusLine(response);
		System.out.println("statusLine "+statusLine);
		
	}

}
