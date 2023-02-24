package com.home.APITestingFramwwork.extentListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;
	public static String fileName;

	public static ExtentReports createInstance(String fileName) {

		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);

		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setReportName("Automation Report");
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Tester", "Smita");
		extent.setSystemInfo("Organization", "Way2Automation");
		extent.setSystemInfo("Build no", "W2A-1234");

		return extent;
	}

}
