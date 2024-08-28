package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.http.auth.InvalidCredentialsException;

import RequestMethods.HttpMethods;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestUtilities {

	// RequestSpecification given;

	public RequestSpecification reqSpec;
	public Response response;

	// To get the data from properties file

	public Object getDataFromProperties(String key) throws IOException {
		Properties prop = new Properties();
		File file = new File(System.getProperty("user.dir") + "/Properties_Files/Credentials.properties");
		FileInputStream stream = new FileInputStream(file);
		prop.load(stream);
		Object value = prop.get(key);
		return value;

	}

	public Response getRequestForSingleHeader(String Key, String Value) {

		Response response = RestAssured.given().header(Key, Value).get();

		return response;

	}

	public void addTwoHeaders(Map<String, Object> map ) {

		 map = new LinkedHashMap<>();
		
		// given = RestAssured.given();
		reqSpec.headers(map);

	}

	public Response postRequestForTwoHeader(String Key1, String Value1, String Key2, String Value2, String FilePath) {

		Map<String, String> map = new LinkedHashMap<>();
		map.put(Key1, Value1);
		map.put(Key2, Value2);

		File file = new File(System.getProperty("user.dir") + FilePath);

		Response post = RestAssured.given().headers(map).body(file).post();

		return post;

	}

	// Example - Main method to use utilities

//	public static void main(String[] args) throws IOException {
//		
//		TestUtilities test = new TestUtilities();
//		Object object = test.getDataFromProperties("UserName");
//		System.out.println("object--> "+object);
//		
//	}

	public void initResAssured() {
		reqSpec = RestAssured.given();

	}

	public void addHeaders(Map<String, String> map) {
		// type1
		/*
		 * RestAssured.given() .header("","") .header("","");
		 */

		// type2
		/*
		 * Header header1 = new Header("", ""); Header header2 = new Header("", "");
		 * 
		 * List<Header> list = new LinkedList<>(); list.add(header1); list.add(header2);
		 * 
		 * Headers headers = new Headers(list);
		 * 
		 * RestAssured.given() .headers(headers);
		 */

		// Type 3

		/*
		 * Map<String,String> map = new LinkedHashMap<>(); map.put("", "");
		 * map.put("","");
		 */
		// RestAssured.given().headers(map);

		reqSpec.headers(map);
	}

	public void addCookies(Map<String, String> map) {
//		reqSpec.cookie("","");
		reqSpec.cookies(map);
	}

	public void addQueryParam(Map<String, String> map) {
		reqSpec.queryParams(map);
	}

	public void addBasicAuth(String userName, String password) {
		reqSpec.auth().preemptive().basic(userName, password);
	}

	public void addOAuth2(String token) {
		reqSpec.auth().preemptive().oauth2(token);
	}

	public void addFileUpload(String fileDesc, String path) {
		File file = new File(path);
		reqSpec.multiPart(fileDesc, file);
	}
//TestUtilities test = new TestUtilities();

	public void addReqBody(Object body) { // using pojo class - object type
		reqSpec.body(body);
	}
	

		
	
	

	public void addReqBodyFile(String filePath) { // to call from file
		File file = new File(filePath);
		reqSpec.body(file);
	}

	public void addReqBodyAsString(String body) { // to call directly in the class

		reqSpec.body(body);
	}

	public Response reqType(HttpMethods name) throws InvalidCredentialsException {

		switch (name) {
		case POST:
			response = reqSpec.log().all().post();
			break;
		case PUT:
			response = reqSpec.log().all().put();
			break;
		case PATCH:
			response = reqSpec.log().all().patch();
			break;
		case DELETE:
			response = reqSpec.log().all().delete();
			break;
		case GET:
			response = reqSpec.log().all().get();
			break;

		default:
			throw new InvalidCredentialsException("Invalid Method");
		}
		return response;
	}

	public int statusCode(Response response) {
		int statusCode = response.getStatusCode();

		return statusCode;

	}

	public String statusLine(Response response) {
		String statusLine = response.getStatusLine();

		return statusLine;

	}

	public long responseTime(Response response) {
		long resTime = response.getTime();

		return resTime;

	}

	public List<String> responseHeadersKeys(Response response) {
		Headers headers = response.getHeaders();
		List<String> list = new ArrayList<>();
		for (Header header : headers) {
			String headerKey = header.getName();
			list.add(headerKey);
		}
		return list;
	}

	public List<String> responseHeadersValues(Response response) {
		Headers headers = response.getHeaders();
		List<String> list = new ArrayList<>();
		for (Header header : headers) {
			String headerValue = header.getValue();
			list.add(headerValue);
		}
		return list;
	}

	public String responseHeaderValue(String headerKey) {
		String header = response.getHeader(headerKey);
		return header;

	}

	public List<String> responseCookiesKeys() {
		Map<String, String> cookies = response.getCookies();
		Set<Entry<String, String>> entrySet = cookies.entrySet();
		List<String> list = new ArrayList<>();
		for (Entry<String, String> entry : entrySet) {
			String cookieskeys = entry.getKey();
			list.add(cookieskeys);

		}
		return list;
	}

	public List<String> responseCookiesValues() {
		Map<String, String> cookies = response.getCookies();
		Set<Entry<String, String>> entrySet = cookies.entrySet();
		List<String> list = new ArrayList<>();
		for (Entry<String, String> entry : entrySet) {
			String cookiesValues = entry.getValue();
			list.add(cookiesValues);

		}
		return list;
	}

	public String getCookie(String key) {
		String cookie = response.getCookie(key);
		return cookie;
	}

	public int getInt(Response response, String xpath) {
		JsonPath jsonPath = response.jsonPath();
		int num = jsonPath.getInt(xpath);
		return num;
	}

	public String getString(String xpath) {
		JsonPath jsonPath = response.jsonPath();
		String value = jsonPath.getString(xpath);
		return value;
	}

	public int getSize(String xpath) {
		JsonPath jsonPath = response.jsonPath();
		int size = jsonPath.getInt(xpath);
		return size;
	}

	public String getStringBody(Response response) {
		String resBody = response.getBody().asPrettyString();
		return resBody;

	}

	public Object getBody(Response response, Class<Object> name) {
		Object resBody = response.as(name);

		return resBody;
	}

	public boolean validateJsonSchema(String schemaPath, Response response) {

		File file = new File(schemaPath);

		response.then().body(JsonSchemaValidator.matchesJsonSchema(file));
		return true;

	}

// Revision
	public void dummyInitRestAssured() { // no return type, to initialize, just call this method
		RestAssured.given();
	}

	public void dummyAddHeaders(Map<String, String> map) {
		reqSpec.headers(map);
	}

	// 23 July 2024
	public Object getBodyAs(Class<Object> cls) {
		Object object = response.as(cls);
		return object;
	}

	// 29th July 2024
	// BaseClass Method for adding class:

	public <T> T addResBodyPojo(Response response, Class<T> classObject) {
		
		return response.as(classObject);

	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}