package Execution;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.auth.InvalidCredentialsException;
import org.testng.Assert;
import org.testng.annotations.Test;

import EndPoints.End_Points;
import Pojo_Post.RootPut;
import RequestMethods.HttpMethods;
import Utilities.TestUtilities;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;


public class TestExecution extends TestUtilities {
	
	int id;
	int janetId;
	String replacedToJanani;
	
	@Test(enabled=false) 
	public void postRequest() throws IOException {  // making new request with same again and again is called IDEM potent. Only Id will be unique and name will be same.
		
		//Object dataFromProperties = getDataFromProperties("OAuth2Token");
		
//		RestAssured.given()
//		.auth()
//		.preemptive()
//		//.oauth2(dataFromProperties.toString());
//		.oauth2(getDataFromProperties("OAuth2Token").toString());
		
		
		
		
	}
	
	@Test(enabled=false)  
	public void getRequest() throws IOException { // Non-IDEM Potent method - all other request
		
		RestAssured.baseURI = End_Points.GET_COFFEE;
		
//		Response response = RestAssured.given()	
//			
//		.header(getDataFromProperties("HeaderKey").toString().trim(), getDataFromProperties("HeaderValue").toString().trim()) //trim() is used to remove the space in front and at the end of the string
//		.get();
		
		Response response = getRequestForSingleHeader(getDataFromProperties("HeaderKey").toString().trim(), getDataFromProperties("HeaderValue").toString().trim());
		
		int statusCode = response.statusCode();
		System.out.println("statusCode--> "+statusCode);
		Assert.assertEquals(statusCode, 200);
		
		String statusLine = response.statusLine();
		System.out.println("statusLine--> "+statusLine);
		
		String asPrettyStringResponse = response.getBody().asPrettyString();
		//System.out.println("asPrettyStringResponse--> "+asPrettyStringResponse);	
		
		JsonPath jsonPathResponse = response.jsonPath();
		int arraySize = jsonPathResponse.getInt("size()");
		System.out.println("arraySize--> "+arraySize);
		
		for(int i=0; i<arraySize; i++) {
			
			System.out.println("Entered into if condition");
			
			String name = jsonPathResponse.getString("["+i+"].name");
			
			System.out.println("name--> "+name);
			
			
			if (name.equalsIgnoreCase("Americano")) {
				
				System.out.println("Entered into if condition");
				
				 id = jsonPathResponse.getInt("["+i+"].id");
				
				System.out.println("Id of Americano is--> "+id);
				
			}
			
			
		}
		
		
		
	}
	
	@Test(enabled=false)  
	public void putRequest() throws IOException {
		
		RestAssured.baseURI = End_Points.PUT_PROFILE;
		
		System.out.println("Id--> "+id);
		
		RootPut rootput = new RootPut("APITesting", id, "RestAssured");
	
		System.out.println("Id--> "+id);
		
		Response put = RestAssured.given()
		.header(getDataFromProperties("HeaderKeyPut").toString().trim(), getDataFromProperties("HeaderValuePut").toString().trim())
		.header(getDataFromProperties("HeaderKey").toString().trim(), getDataFromProperties("HeaderValue").toString().trim())
		.body(rootput)
		.put();
		
		int statusCode = put.statusCode();
		System.out.println("statusCode--> "+statusCode);
		Assert.assertEquals(statusCode, 204);
		
		String statusLine = put.statusLine();
		System.out.println("statusLine--> "+statusLine);
		
		String putBody = put.getBody().asPrettyString();
		System.out.println("putBody--> "+putBody);
		

		  
		
	}
	
	
	@Test(enabled=false) 
	public void deleteRequest() throws IOException {

		
	RestAssured.baseURI = End_Points.DELETE_PROFILE;
	
	Response response = RestAssured.given()
	.header(getDataFromProperties("HeaderKeyDelete").toString().trim(), getDataFromProperties("HeaderValueDelete").toString().trim())
	.delete();
		
	int statusCode = response.getStatusCode();
	System.out.println("statusCode--> "+statusCode);
	Assert.assertEquals(statusCode, 200);
	
	String statusLine = response.getStatusLine();
	System.out.println("statusLine--> "+statusLine);
	
	ResponseBody body = response.getBody();
	System.out.println("body--> "+body);
	
	Headers headers = response.getHeaders();
	System.out.println("headers--> "+headers);
	
	for (Header header : headers) {
		
		String headerKey = header.getName();
		//System.out.println("headerKey--> "+headerKey);
		
		if (headerKey.equalsIgnoreCase("server")) {
			
			String headerValue = header.getValue();
			System.out.println("headerValue--> "+headerValue);
			Assert.assertEquals(headerValue, "Microsoft-IIS/10.0", "Header Verified Successfully"); 
			
		
			
		}
		
	}
	
	
	
		
	}
    
	@Test (priority = 1)
	public void patchGetRequest() {
		
		RestAssured.baseURI = End_Points.PATCHGET_USER;
		
		Response response = RestAssured.given()
		.header("accept", "application/json")
		.get();
		
		String reqBody = response.getBody().asPrettyString();
		
		if(reqBody.contains("Janet"))
		{
			replacedToJanani = reqBody.replace("Janet", "Janani");
			System.out.println("replacedToJanani--> "+replacedToJanani);
		}
		
		
//		System.out.println("reqBody--> "+reqBody);
//		
//		JsonPath jsonPathResponse = response.jsonPath();
//		int dataSize = jsonPathResponse.getInt("data.size()");
//		System.out.println("dataSize--> "+dataSize);
//		
//		for(int i=0; i<dataSize; i++)
//		{
//			String allFirstName = jsonPathResponse.getString("data["+i+"].first_name");
//			
//			if (allFirstName.equalsIgnoreCase("Janet"))
//			{
//				janetId = jsonPathResponse.getInt("data["+i+"].id");
//				System.out.println("janetId--> "+janetId);
//			}
//		}
//		
		
		
		
		
	}

	@Test (priority = 2)
public void patchRequest2() throws IOException {
	
	RestAssured.baseURI = End_Points.PATCH_USER;
	    
	    Response patch = RestAssured.given()
	    .header(getDataFromProperties("HeaderKeyPatch").toString().trim() , getDataFromProperties("HeaderValuePatch").toString().trim())
	    //.body(ReqBody)
	  //  .body(file)
	    .body(replacedToJanani)
	    .patch();
	    
	    int statusCode = patch.getStatusCode();
	    System.out.println("statusCode--> "+statusCode);
		Assert.assertEquals(statusCode, 200);
		
		String statusLine = patch.getStatusLine();
		System.out.println("statusLine--> "+statusLine);
		
		String patchBody = patch.getBody().asPrettyString();
		System.out.println("patchBody--> "+patchBody);
		
	
}
	
	
	@Test(enabled=false)  
	public void patchRequest() throws IOException {
		
		 String ReqBody = "{\n"
		 		+ "  \"data\": {\n"
		 		+ "    \"id\": 2,\n"
		 		+ "    \"email\": \"janet.weaver@reqres.in\",\n"
		 		+ "    \"first_name\": \"Janani\",\n"
		 		+ "    \"last_name\": \"Weaver\",\n"
		 		+ "    \"avatar\": \"https://reqres.in/img/faces/2-image.jpg\"\n"
		 		+ "  },\n"
		 		+ "  \"support\": {\n"
		 		+ "    \"url\": \"https://reqres.in/#support-heading\",\n"
		 		+ "    \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n"
		 		+ "  }\n"
		 		+ "}";
	// 1. Direct String
		 //2. json file
		 //3. pojo class
		 
		
    RestAssured.baseURI = End_Points.PATCH_USER;
    
  File file = new File(System.getProperty("user.dir")+"/src/test/resources/Resources_Post_Profile/PatchFile.json");
    
    Response patch = RestAssured.given()
    .header(getDataFromProperties("HeaderKeyPatch").toString().trim() , getDataFromProperties("HeaderValuePatch").toString().trim())
    //.body(ReqBody)
    .body(file)
    .patch();
    
    int statusCode = patch.getStatusCode();
    System.out.println("statusCode--> "+statusCode);
	Assert.assertEquals(statusCode, 200);
	
	String statusLine = patch.getStatusLine();
	System.out.println("statusLine--> "+statusLine);
	
	String patchBody = patch.getBody().asPrettyString();
	System.out.println("patchBody--> "+patchBody);
	
	
	
	
	
    
		
	}

	@Test
public void postCoffeeRequest() throws IOException {

		
//		curl -X 'POST' \
//		  'https://webservice.toscacloud.com/api/v1/Coffees' \
//		  -H 'accept: text/plain' \
//		  -H 'Content-Type: application/json' \
		// -H 'Authorization: Bearer <token>'
//		  -d '{ 
//		  "description": "string",
//		  "id": 0,
//		  "name": "string"
//		}'
	
		RestAssured.baseURI = "https://webservice.toscacloud.com";
		RestAssured.basePath = "/api/v1/Coffees"; // It includes Path & Query Param
		
	Response postRequestForTwoHeader = postRequestForTwoHeader(getDataFromProperties("HeaderKey").toString().trim(),getDataFromProperties("HeaderValue").toString().trim(), 
			getDataFromProperties("HeaderKeyPut").toString().trim(), getDataFromProperties("HeaderValuePut").toString().trim(), 
	"/src/test/resources/Resources_Post_Profile/PostFile.json"); //
	
	
	int statusCode = postRequestForTwoHeader.getStatusCode();
	System.out.println("statusCode--> "+statusCode);
	
	String resBody = postRequestForTwoHeader.getBody().asPrettyString();
	System.out.println("resBody--> "+resBody);
		
		
	}
	public void method() {
		
	}

public void newmethod() {
		
	}

}


