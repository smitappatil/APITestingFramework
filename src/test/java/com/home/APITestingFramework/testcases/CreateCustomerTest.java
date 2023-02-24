package com.home.APITestingFramework.testcases;

import static io.restassured.RestAssured.*;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.home.APITestingFramework.APIs.CreateCustomerAPI;
import com.home.APITestingFramework.basetest.Basetest;
import com.home.APITestingFramework.utilities.DataUtil;
import com.home.APITestingFramwwork.extentListener.ExtentListeners;
import com.home.APITestingFramwwork.extentListener.ExtentManager;

import io.restassured.response.Response;

public class CreateCustomerTest extends Basetest {

	
	// Create customer with valid auth key
	@Test (dataProviderClass = DataUtil.class, dataProvider = "data")
	public void validateCreateCustomerAPIWithValidSecretkey(Hashtable<String, String> data) {

		
		Response response = CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithValidAuthKey(data);

		ExtentListeners.testReport.get().info(data.toString());
		response.prettyPrint();
		
		System.out.println(response.statusCode());
		Assert.assertEquals(response.statusCode(), 200);
		
	}
	
	// Create customer with invalid auth key
	@Test (dataProviderClass = DataUtil.class , dataProvider = "data")
	public void validateCreateCustomerAPIWithInValidSecretkey(Hashtable<String, String> data) {
		
		Response response = CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithInValidAuthKey(data);
		ExtentListeners.testReport.get().info(data.toString());
		response.prettyPrint();
		
		System.out.println(response.statusCode());
		Assert.assertEquals(response.statusCode(), 401);
	}

}
