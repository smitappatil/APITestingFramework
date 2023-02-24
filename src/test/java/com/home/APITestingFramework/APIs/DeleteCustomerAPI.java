package com.home.APITestingFramework.APIs;

import java.util.Hashtable;

import com.home.APITestingFramework.basetest.Basetest;
import com.home.APITestingFramwwork.extentListener.ExtentListeners;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class DeleteCustomerAPI extends Basetest{
	
	public static Response sendDeleteRequestToDeleteCustomerAPIWithValidId(Hashtable<String, String> data) {
	
		Response response = given().auth().basic(config.getProperty("validSecretKey"),"")
				.delete(config.getProperty("customerAPIEndPoint")+"/"+data.get("id"));
			
		ExtentListeners.testReport.get().info("Deteting the customer with id : " +data.get("id"));
				
//				.delete(config.getProperty("baseURI")+config.getProperty("bastPath")+
//				config.getProperty("customerAPIEndPoint")+"/"+data.get("id"));
		
		return response;
	}

}
