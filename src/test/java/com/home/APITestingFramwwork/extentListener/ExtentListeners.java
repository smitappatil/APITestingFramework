package com.home.APITestingFramwwork.extentListener;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentListeners implements ITestListener, ISuiteListener {

	static Date d = new Date();

	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

	private static ExtentReports extent = ExtentManager.createInstance(".//reports//" + fileName);

	//public static ExtentTest test;
	
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {

		ExtentTest test = extent.createTest(" @ClassName : " + result.getTestClass().getName() + "  @TestCase : "
				+ result.getMethod().getMethodName());
		testReport.set(test);
	}

	public void onTestSuccess(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);
		//test.pass(m);

	}

	public void onTestFailure(ITestResult result) {

		// test.fail(result.getThrowable().getMessage());
//		try {
//			ExtentManager.captureScreenshot();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		/*
		 * to add a link for exception occurred
		 */
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		testReport.get().fail(result.getThrowable().getMessage().toString());
		testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=red>" + "Exception Occured : Click to see" + "</font>"
		+"</b>"+"</summary>" + exceptionMessage.replaceAll(",","<br>")+"</details>" +"\n");
		
		
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " FAILED" + "</b>";

//		test.fail("<b><font color=red>" + "Screenshot of failure" + "</font></b><br>",
//				MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.fileName).build());

		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		//test.log(Status.FAIL, m);
		testReport.get().log(Status.FAIL, m);
	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName.toUpperCase() + " SKIPPED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
		//test.skip(m);
		testReport.get().skip(m);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {

		if (extent != null) {

			extent.flush();
		}

	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub

	}

}
