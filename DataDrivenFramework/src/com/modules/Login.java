package com.modules;

import com.repository.OR;

import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.repository.Parameter;
import com.repository.config;

public class Login {
	static String TestId, Actual, Status, ModuleName, Emailid,ExpectedResult,ErrorMsg;
	static String baseWindowHdl;	
	//static ExtentReports extent;

	public void LoginFeature() throws  InterruptedException, FileNotFoundException {
		
	/*********************************TC001_EmailId_Empty********************************************/
				
		baseWindowHdl = config.driver.getWindowHandle();
		ModuleName = "Login";
		TestId = "TC001_EmailId_Empty";
		Emailid = Parameter.GetInput(ModuleName, TestId, 5);
		ExpectedResult = Parameter.GetInput(ModuleName, TestId, 2);
		WebElement ErrorMsgField = null;
		try {	
			config.driver.findElement(OR.GetLocator(ModuleName, "Next")).click();;
			ErrorMsgField=config.driver.findElement(OR.GetLocator(ModuleName, "EmailErrorFeild"));
			Actual = ErrorMsgField.getText();
			if (Actual.contains(ExpectedResult)) {
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
			} else {
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			}

		} catch ( Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
		
		
		/*********************************TC001_EmailId_Empty********************************************/
		
		
		TestId = "TC002_Invalid_EmailId_Empty";
		Emailid = Parameter.GetInput(ModuleName, TestId, 5);
		ExpectedResult = Parameter.GetInput(ModuleName, TestId, 2);
		//WebElement ErrorMsgField = null;
		try {
			
			config.driver.findElement(OR.GetLocator(ModuleName, "EmailFeild")).sendKeys(Emailid);;
			config.driver.findElement(OR.GetLocator(ModuleName, "Next")).click();
			ErrorMsgField=config.driver.findElement(OR.GetLocator(ModuleName, "EmailErrorFeild"));
			Actual = ErrorMsgField.getText();
			if (Actual.contains(ExpectedResult)) {
				Parameter.GetOutput(ModuleName, TestId, Actual, "Pass");
			} else {
				Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
			}

		} catch (Exception e) {
			Actual = "Exception Occured" + "Error" + e.getLocalizedMessage();
			Parameter.GetOutput(ModuleName, TestId, Actual, "Fail");
		}
	}
}
