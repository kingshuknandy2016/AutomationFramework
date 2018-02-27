package com.spi.testrunner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.spi.dataprovider.ExcelLibrary;
import com.spi.testclasses.Driver;


public class TestExecutor {

	public static String sheetName="Scenario1"; 
	public static String action=null;
	public static String idMethod=null;
	public static String locator=null;
	public static String data=null;
	public static String status=null;
	public static String scenarioStatus="PASS";

	
	public static void main(String[] args) {


	}
	
	public void run(String sheetName) {
		
		ExcelLibrary lib=new ExcelLibrary(Driver.filePath);
		int rowCount=lib.getRowCount(sheetName);
		//For a particular Scnerio
		for (int i = 1; i <= rowCount; i++) {
			
			//Getting type of action--calling corrosponding method
			action=(String) lib.getCellData(sheetName, i, 0);
			switch (action.toLowerCase()) {
			case "verifytitle": data=(String) lib.getCellData(sheetName, i, 3);
				                status = verifyTitle(data);
								break;
			case "type" :idMethod=(String) lib.getCellData(sheetName, i, 1);
                         locator=(String) lib.getCellData(sheetName, i, 2);
                         data=(String) lib.getCellData(sheetName, i, 3);
				         status = type(idMethod,locator,data) ; 
				     	 break ;
			case  "click" :idMethod=(String) lib.getCellData(sheetName, i, 1);
                           locator=(String) lib.getCellData(sheetName, i, 2);
				           status = click(idMethod,locator);
						   break;
			case "verifytext":idMethod=(String) lib.getCellData(sheetName, i, 1);
                              locator=(String) lib.getCellData(sheetName, i, 2);
                              data=(String) lib.getCellData(sheetName, i, 3);
				              status = verifyText(idMethod,locator,data);
			default:
				break;
			}
			//Writing the status of each action for one scenerio
			lib.writeToCell(sheetName, i, 4, status);
		}
		
		//Writing the execution-status of each scenario
		rowCount = lib.getRowCount("ScenarioList");
		for(int i = 1;i<=rowCount;i++){
			String scenarioName = (String) lib.getCellData("ScenarioList", i, 0);
			if(scenarioName.equalsIgnoreCase(sheetName)){
				lib.writeToCell("ScenarioList", i, 2, scenarioStatus);
				break;
			}
		}
	}

	private String verifyText(String idMethod, String locator, String data) {
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

	private WebElement getElement(String idMethod, String locator) throws Exception {
		WebElement element = null;
		
		switch (idMethod.toLowerCase()) {
		case "id":element = Driver.driver.findElement(By.id(locator));
				  break;
		case "name":element = Driver.driver.findElement(By.name(locator));
		          break;
		case "classname":element = Driver.driver.findElement(By.className(locator));
		           break;
        case "xpath":element = Driver.driver.findElement(By.xpath(locator));
                   break;		          
		case "linktext":element = Driver.driver.findElement(By.linkText(locator));
		           break;
        case "partiallinktext":element = Driver.driver.findElement(By.partialLinkText(locator));
                    break;
        case "cssselector":element = Driver.driver.findElement(By.cssSelector(locator));
                   break;
		case "tagname":element = Driver.driver.findElement(By.tagName(locator));
                   break;

		   default:throw new Exception("Invalid ID Method");
			      
		}
		return element;
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
			System.out.println("Page Title is: "+actualTitle);
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

}
