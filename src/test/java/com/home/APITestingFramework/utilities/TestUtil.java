package com.home.APITestingFramework.utilities;

import org.json.JSONObject;

import com.home.APITestingFramwwork.extentListener.ExtentListeners;

public class TestUtil {

	public static boolean jsonHasKey(String json, String key) {

		JSONObject jsonObject = new JSONObject(json);
		ExtentListeners.testReport.get().info("Validating presence of Key :" +key);
		return jsonObject.has(key);
	}
	
	public static String getJsonKeyValue(String json, String key) {
		JSONObject jsonObject = new JSONObject(json);
		ExtentListeners.testReport.get().info("Validating value of Key :" +key);
		return jsonObject.get(key).toString();
		
	}


}
