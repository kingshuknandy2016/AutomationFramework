package com.repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import jxl.Sheet;
import jxl.Workbook;
import org.openqa.selenium.By;

public class OR {

	private static String XpathExpression;

	public static By GetLocator(String ModuleName, String ObjectLogicalName) throws IOException {
		By obj = null;
		XpathExpression = OR.GetXpath(ModuleName, ObjectLogicalName);
		System.out.println("xpath from excel sheet:  " + XpathExpression);
		obj = By.xpath(XpathExpression);
		return obj;
	}

	/*************************Method to Read Excel*******************************************************/

	static String GetXpath(String ModuleName, String ObjectLogicalName) throws FileNotFoundException {

		//FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "\\ObjectRepository\\ValloeTestData.xls");
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"\\Utility\\TestData.xls");
		try {
			Workbook w = Workbook.getWorkbook(fi);
			Sheet s = w.getSheet("xpath");
			for (int row = 1; row < s.getRows(); row++) {

				/***************Read The Module Name ********************************/
				String ModuleName1 = s.getCell(0, row).getContents();
				

				/***************Read The Object Logical Name ********************************/
				String ObjectLogicalName1 = s.getCell(1, row).getContents();
				

				if (ModuleName1.equalsIgnoreCase(ModuleName) && ObjectLogicalName1.equalsIgnoreCase(ObjectLogicalName)) {

					/***************Read The Xpath Expression Name ********************************/
					XpathExpression = s.getCell(2, row).getContents();
					//System.out.println(XpathExpression);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return XpathExpression;
	}
}