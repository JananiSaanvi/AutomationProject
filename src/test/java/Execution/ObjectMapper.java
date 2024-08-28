package Execution;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.auth.InvalidCredentialsException;
import org.testng.Assert;
import org.testng.annotations.Test;

import EndPoints.End_Points;
import Pojo_Post.RootPostRegister;
import RequestMethods.HttpMethods;
import Utilities.TestUtilities;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

// To manipulate the response body , we use jsonpath and object mapper
// Code Date: 23 July 2024

public class ObjectMapper extends TestUtilities{
	
	public Response response;
	@Test
	public void ObjMap() {
		RestAssured.baseURI = "https://reqres.in/api/users";
		
		Response response = RestAssured.given().log().all().get();
		String resBody = response.getBody().asPrettyString();
		
		//System.out.println("ResponseBody "+resBody);
		
		Root root = response.as(Root.class);
		int page = root.getPage();
		
		//int per_page = root.getPer_page();
		
		System.out.println("Page "+page);
		//System.out.println("Page "+page);
		
		String first_name = root.getData().get(1).getFirst_name();
		System.out.println("First Name "+first_name);
		
		String url = root.getSupport().getUrl();
		
		System.out.println("Support URL "+url);
		
		
		
	}

	@Test
	public void ObjMapFromUtils() {
		RestAssured.baseURI = "https://reqres.in/api/users";
		
		 response = RestAssured.given().log().all().get();
		
		//getBody(response, Root.class);
		 
		 Root resBodyPojo = addResBodyPojo(response, Root.class);
		 int page = resBodyPojo.getPage();
		 System.out.println("page "+page);
	}
	@Test
	public void schemaValidator() {
		RestAssured.baseURI = "https://reqres.in/api/users";
		
		response = RestAssured.given().log().all().get();
		String resBody = response.getBody().asPrettyString();
		
		int int1 = getInt(response, "page");
		System.out.println("page "+int1);
		
		//File file = new File(System.getProperty("user.dir")+"//ResponseBodySchema.json");
		//ValidatableResponse body = response.then().body(JsonSchemaValidator.matchesJsonSchema(file));
		
		//System.out.println("resBody "+resBody);
		
		//boolean validateJsonSchema = validateJsonSchema(System.getProperty("user.dir")+"//ResponseBodySchema.json", response);
		
		//Assert.assertTrue(validateJsonSchema);
		
		
		
		
	}
	
	@Test
	public void postRequest() throws InvalidCredentialsException {
		
		RestAssured.baseURI = End_Points.POST_REGISTER;
		
//		curl -X 'POST' \
//		  'https://reqres.in/api/register' \
//		  -H 'accept: application/json' \
//		  -H 'Content-Type: application/json' \
//		  -d '{
//		  "username": "string",
//		  "email": "string",
//		  "password": "string"
//		}'
		
		initResAssured();
		
		Map<String, Object> map = new LinkedHashMap<>();
		
		map.put("accept", "application/json");
		map.put("Content-Type", "application/json");
		
	    addTwoHeaders(map);	
	    
	    RootPostRegister root = new RootPostRegister("Janani", "sjanani@gmail.com", "password");
	    
	    
        addReqBody(root);
        
        response = reqType(HttpMethods.POST);
        
        int statusCode = statusCode(response);
        System.out.println("statusCode "+statusCode);
        
        String statusLine = statusLine(response);
        System.out.println("statusLine "+statusLine);
	
        long responseTime = responseTime(response);
        System.out.println("responseTime "+responseTime);
        
        String stringBody = getStringBody(response);
        System.out.println("stringBody "+stringBody);
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

