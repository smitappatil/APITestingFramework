package com.home.APITestingFramework.testcases;

import java.util.Hashtable;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.home.APITestingFramework.APIs.DeleteCustomerAPI;
import com.home.APITestingFramework.basetest.Basetest;
import com.home.APITestingFramework.utilities.DataUtil;
import com.home.APITestingFramework.utilities.TestUtil;
import com.home.APITestingFramwwork.extentListener.ExtentListeners;

import io.restassured.response.Response;

public class DeleteCustomerTest extends Basetest  {

	@Test(dataProviderClass = DataUtil.class, dataProvider = "data")
	public void deleteCustomerWithValidCustomerId(Hashtable<String,String> data) {
		
		Response response = DeleteCustomerAPI.sendDeleteRequestToDeleteCustomerAPIWithValidId(data);
		ExtentListeners.testReport.get().info("Response Json : "+response.toString());
		response.prettyPrint();
		String expectedId = data.get("id"); // from excel sheet
//		//assert id
//		String actualId= response.jsonPath().getString("id").toString();
//		
//		System.out.println("Actual id : " +actualId);
//		Assert.assertEquals(actualId, expectedId, "Id not matched");
//		//Check status code
//		System.out.println("Status code : "+response.getStatusCode());
//		Assert.assertEquals(response.getStatusCode(), 200);
		
		
//		JSONObject jsonObject = new JSONObject(response.asString());
//		System.out.println(jsonObject.has("id"));
//		System.out.println(jsonObject.has("object"));
//		System.out.println(jsonObject.has("deleted"));
//		String actualId = jsonObject.get("id").toString();
//		
//		Assert.assertEquals(actualId, expectedId, "Id not matched");
		
		Assert.assertTrue(TestUtil.jsonHasKey(response.asString(), "id"),"id key is not present in response");
		String actualId =TestUtil.getJsonKeyValue(response.asString(), "id");
		Assert.assertEquals(actualId, expectedId, "Id is not matching");
		
	//	System.out.println("Presence check of Object key :" +TestUtil.jsonHasKey(response.asString(), "object"));
	//	System.out.println("Presence check of deleted key :" +TestUtil.jsonHasKey(response.asString(), "deleted"));
		Assert.assertTrue(TestUtil.jsonHasKey(response.asString(), "object"), "object key is not present in response");
		System.out.println("Object key value = "+ TestUtil.getJsonKeyValue(response.asString(), "object"));
		Assert.assertTrue(TestUtil.jsonHasKey(response.asString(), "deleted"), "deleted key is not present in response");
		System.out.println("deleted key value = "+TestUtil.getJsonKeyValue(response.asString(), "deleted"));
	}
}

