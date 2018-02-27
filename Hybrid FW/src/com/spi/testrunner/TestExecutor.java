package com.spi.testrunner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.spi.data.ExcelLibrary;
import com.spi.test.Driver;

public class TestExecutor {

	String scenarioStatus = "PASS";
	public void run(String sheetName) {
		
		
		
		ExcelLibrary el = new ExcelLibrary(Driver.filePath);
		int rowCount = el.getRowCount(sheetName);
		for(int i = 1;i<=rowCount;i++){
			String action = (String) el.getCellData(sheetName, i, 0);
			String idMethod = null,locator = null,data = null,status = null;
			if(!action.equalsIgnoreCase("verifytitle")){
				//read IDMethod and locator from excel only if action is not a verifyTitle
				idMethod = (String) el.getCellData(sheetName, i, 1);
				locator = (String) el.getCellData(sheetName, i, 2);
			}
			
			if(!action.equalsIgnoreCase("click")){
				data = (String) el.getCellData(sheetName, i, 3);
			}
			
			
			if(action.toLowerCase().equalsIgnoreCase("verifytitle")) 
						status = verifyTitle(data);
			else if(action.toLowerCase().equalsIgnoreCase("type"))
					status = type(idMethod,locator,data);
			else if(action.toLowerCase().equalsIgnoreCase("click"))
				status = click(idMethod,locator);
			else if(action.toLowerCase().equalsIgnoreCase("verifytext"))
				status = verifyText(idMethod,locator,data);
							
			el.writeToCell(sheetName, i, 4, status);
		}
		
		rowCount = el.getRowCount("ScenarioList");
		for(int i = 1;i<=rowCount;i++){
			String scenarioName = (String) el.getCellData("ScenarioList", i, 0);
			if(scenarioName.equalsIgnoreCase(sheetName)){
				el.writeToCell("ScenarioList", i, 2, scenarioStatus);
				break;
			}
		}
		
		
		
		

	}
	private String verifyText(String idMethod, String locator,
			String data) {
		String status =null;
		try{
			WebElement element = getElement(idMethod, locator);
			String actualText = element.getText();
			if(actualText.contains(data))
				status = "PASS";
			else {
				status = "FAIL";
				scenarioStatus = "FAIL";
			}
			
		}catch (Exception e) {
			status = "FAIL: "+e.getMessage();
			scenarioStatus = "FAIL";
		}
		return status;
	}
	private String click(String idMethod, String locator) {
		String status = "PASS";
		try{
			WebElement element = getElement(idMethod, locator);
			
			element.click();
			
		}catch (Exception e) {
			status = "FAIL: "+e.getMessage();
			scenarioStatus = "FAIL";
		}
		return status;
	}
	private String type(String idMethod, String locator, String data) {
		String status = "PASS";
		try{
			
			
			WebElement element = getElement(idMethod, locator);
			element.sendKeys(data);
			
		}catch (Exception e) {
			status = "FAIL: "+e.getMessage();
			scenarioStatus = "FAIL";
		}
		return status;
	}
	private String verifyTitle(String expectedTitle) {
		String status = null;
		try{
			String actualTitle = Driver.driver.getTitle();
			if(actualTitle.contains(expectedTitle)){
				status = "PASS";
			}else {
				status = "FAIL";
				scenarioStatus = "FAIL";
			}
			
		}catch (Exception e) {
			status = "FAIL: "+e.getMessage();
			scenarioStatus = "FAIL";
		}
		return status;
	}
	public WebElement getElement(String idMethod,String locator) throws Exception{
		WebElement element = null;
		if(idMethod.equalsIgnoreCase("id"))
			element = Driver.driver.findElement(By.id(locator));
		else if(idMethod.equalsIgnoreCase("name"))
			element = Driver.driver.findElement(By.name(locator));
		else if(idMethod.equalsIgnoreCase("classname"))
			element = Driver.driver.findElement(By.className(locator));
		else if(idMethod.equalsIgnoreCase("xpath"))
			element = Driver.driver.findElement(By.xpath(locator));
		else if(idMethod.equalsIgnoreCase("linktext"))
			element = Driver.driver.findElement(By.linkText(locator));
		else if(idMethod.equalsIgnoreCase("partiallinktext"))
			element = Driver.driver.findElement(By.partialLinkText(locator));
		else if(idMethod.equalsIgnoreCase("cssselector"))
			element = Driver.driver.findElement(By.cssSelector(locator));
		else if(idMethod.equalsIgnoreCase("tagname"))
			element = Driver.driver.findElement(By.tagName(locator));
		else 
			throw new Exception("Invalid ID Method");
		return element;
	}

}
