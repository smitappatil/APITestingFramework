package com.home.APITestingFramework.utilities;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import com.home.APITestingFramework.basetest.Basetest;

public class DataUtil extends Basetest {

	@DataProvider(name = "data" , parallel = true)
	public Object[][] getData(Method result) {

		String sheetName = config.getProperty("testDataSheetName");
		System.out.println(config.getProperty("testDataSheetName"));
		System.out.println("sheetname= " + sheetName);

		// total rows in the excel sheet
		int rows = excel.getRowCount(sheetName);
		System.out.println("total rows : " + rows);

		String testName = result.getName();

		int testCaseRowNum = 1;
		// find the test case start row
		for (testCaseRowNum = 1; testCaseRowNum < rows; testCaseRowNum++) {

			String testCaseName = excel.getCellData(sheetName, 0, testCaseRowNum);

			if (testCaseName.equalsIgnoreCase(testName)) {
				break;
			}
		}

		System.out.println("Test case starts from row num testCaseRowNum:" + testCaseRowNum);

		// find number of rows of test data.. checking total rows in test case

		int testDataStartRowNum = testCaseRowNum + 2;
		int testRows = 0;

		while (!excel.getCellData(sheetName, 0, testDataStartRowNum + testRows).equals("")) {
			testRows++;
		}

		System.out.println("total rows of data are testRows :" + testRows);

		// find number of cols of test data.. checking total cols in test case
		int colStartColNum = testCaseRowNum + 1;
		int testCols = 0;
		while (!excel.getCellData(sheetName, testCols, colStartColNum).equals("")) {
			testCols++;
		}

		System.out.println("total cols of data are testCols:" + testCols);

		// get test data
		// Object[][] data = new Object[testRows][testCols];
		Object[][] data = new Object[testRows][1];

		int i = 0;
		for (int rowNum = testDataStartRowNum; rowNum < testDataStartRowNum + testRows; rowNum++) {
			Hashtable<String, String> table = new Hashtable<String, String>();
			for (int colNum = 0; colNum < testCols; colNum++) {

				System.out.println(excel.getCellData(sheetName, colNum, rowNum));
				// data[rowNum-testDataStartRowNum][colNum] = excel.getCellData(sheetName,
				// colNum, rowNum);
				String testData = excel.getCellData(sheetName, colNum, rowNum);
				String colName = excel.getCellData(sheetName, colNum, colStartColNum);
				table.put(colName, testData);

			}
			data[i][0] = table;
			i++;
		}

		return data;

	}

}
