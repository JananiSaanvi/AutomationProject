package Utilities;

import org.testng.annotations.Test;

import RequestMethods.DummyEnum;
import io.restassured.specification.RequestSpecification;

public class Practise {

	RequestSpecification reqSpec;

	@Test
	public void switchCase() {

		// alternate of "if" condition is "switch case"

		String name = "Janani";

		// For one or two condition - we use if condition
		if (name.equalsIgnoreCase("Janani")) {
			System.out.println("True");
		} else {
			System.out.println("False");
		}

		// Multiple conditions - we use switch case
		System.out.println("*****************");
		switch (name) {
		case "Janani":
			System.out.println("True");
			break;

		default:
			System.out.println("False");
			break;
		}
	}

	public void httpMethods(DummyEnum Methods) throws NoSuchMethodException {

		switch (Methods) {
		case POST:
			reqSpec.log().all().post();
			break;
			
		case PUT:
			reqSpec.log().all().put();
			break;

		case GET:
			reqSpec.log().all().get();
			break;
			
		case PATCH:
			reqSpec.log().all().patch();
			break;
			
		case DELETE:
			reqSpec.log().all().delete();
			break;

		default:
			throw new NoSuchMethodException();
			//break;
		}
		
		if (Methods.POST != null) {
			reqSpec.log().all().post();
			
		}
		
		else if (Methods.PUT != null) {
			reqSpec.log().all().put();
		}
		
		else if (Methods.GET != null) {
			reqSpec.log().all().get();
		}
		
		
		

	}
}
