package com.home.APITestingFramework.APIs;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import com.home.APITestingFramework.basetest.Basetest;
import com.home.APITestingFramwwork.extentListener.ExtentListeners;

import io.restassured.response.Response;

public class CreateCustomerAPI extends Basetest{

	public static Response sendPostRequestToCreateCustomerAPIWithValidAuthKey(Hashtable<String, String> data) {
	
		Response response = given().auth().basic(config.getProperty("validSecretKey"),"")
				.formParam("name", data.get("name"))
				.formParam("email", data.get("email"))
				.formParam("description", data.get("description"))
				.post(config.getProperty("customerAPIEndPoint"));
		ExtentListeners.testReport.get().info("Creating the customer with valid Auth Key");
		return response;
		
	}
	
	public static Response sendPostRequestToCreateCustomerAPIWithInValidAuthKey(Hashtable<String,String> data) {
		
		Response response = given().auth().basic(config.getProperty("invalidSecretKey"),"")
		.formParam("name", data.get("name"))
		.formParam("email", data.get("email"))
		.formParam("description", data.get("description"))
		.post(config.getProperty("customerAPIEndPoint"));
		ExtentListeners.testReport.get().info("Trying to Create customer with InValid Auth Key");
		return response;
	}	
	
}
