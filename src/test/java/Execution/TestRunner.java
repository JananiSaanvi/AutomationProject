package Execution;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.auth.InvalidCredentialsException;
import org.testng.Assert;
import org.testng.annotations.Test;

import EndPoints.End_Points;
import Pojo_Post.RootPut;
import RequestMethods.HttpMethods;
import Utilities.TestUtilities;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestRunner extends TestUtilities {
	@Test
	public void postCoffeeRequestFromUtitilies() throws IOException, InvalidCredentialsException {
//		curl -X 'POST' \
//		  'https://webservice.toscacloud.com/api/v1/Coffees' \
//		  -H 'accept: text/plain' \
//		  -H 'Content-Type: application/json' \
//		  -d '{
//		  "description": "string",
//		  "id": 0,
//		  "name": "string"
//		}'
		RestAssured.baseURI = End_Points.POST_COFFEE;
//		String body = "{\n"
//				+ "//		  \"description\": \"string\",\n"
//				+ "//		  \"id\": 0,\n"
//				+ "//		  \"name\": \"string\"\n"
//				+ "//		}";
		initResAssured();

		Map<String, String> map = new LinkedHashMap<>();
		map.put(getDataFromProperties("HeaderKey").toString().trim(),
				getDataFromProperties("HeaderValue").toString().trim());
		map.put(getDataFromProperties("HeaderKeyPut").toString().trim(),
				getDataFromProperties("HeaderValuePut").toString().trim());

		addHeaders(map);

		// addReqBodyAsString(body);

		// addReqBodyFile(System.getProperty("user.dir")+"/src/test/resources/Resources_Post_Profile/PostCoffeeReqBody");

		RootPut postBody = new RootPut("Janani", 10, "Sampath");
		addReqBody(postBody);

		reqType(HttpMethods.POST);

		//Assert.assertEquals(statusCode(), 200);
		//System.out.println(statusCode());
		
		//statusCode();
		
		
		System.out.println(responseHeadersKeys(response));
		

	}

	public void dummyMethod() {

		RequestSpecification given = RestAssured.given();
		RequestSpecification headers = given.headers("key", "value");
		RequestSpecification queryParam = given.queryParam("", "");

		RequestSpecification basic = given.auth().preemptive().basic("", "");

	}

}
