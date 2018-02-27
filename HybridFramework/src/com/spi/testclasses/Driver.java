package com.spi.testclasses;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.spi.dataprovider.ExcelLibrary;
import com.spi.testrunner.TestExecutor;

public class Driver {
	public static WebDriver driver;
	public static String filePath=System.getProperty("user.dir")+"\\ControlSheets\\KSRTC TestCase.xls";
	
	@BeforeTest
	public void openApplication(){
		
		if(filePath != null){
			ExcelLibrary el = new ExcelLibrary(filePath);
			String browserType = (String) el.getCellData("Driver Details", 0, 1);
			if(browserType.equalsIgnoreCase("firefox"))
				driver = new FirefoxDriver();
			else if(browserType.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Driver\\chromedriver.exe");
				driver = new ChromeDriver();
			}else if(browserType.equalsIgnoreCase("ie")){
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\Driver\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String url = (String) el.getCellData("Driver Details", 1, 1);
			driver.get(url);
		}else{
			System.out.println("File Path is invalid");
		}
	}
	
	
	@DataProvider
	public Object[][] getSheetName(){
		List  <String> sheetNameList=getSheetNameList();
		Object[][] data = new Object[sheetNameList.size()][1];
		for(int i = 0;i<sheetNameList.size();i++){
			data[i][0] = sheetNameList.get(i);
		}
		return data;		
	}
	
	public List<String> getSheetNameList(){
		List<String> sheetNameList = new ArrayList<String>();
		ExcelLibrary lib=new ExcelLibrary(filePath);
		int rowCount=lib.getRowCount("ScenarioList");
		for (int i = 1; i <= rowCount; i++) {
			String exeStatus=(String) lib.getCellData("ScenarioList", i, 1);
			if(exeStatus.equalsIgnoreCase("yes")){
				String sheetName=(String) lib.getCellData("ScenarioList", i, 0);
				sheetNameList.add(sheetName);
			}else{
				lib.writeToCell("ScenarioList", i, 2, "Skipped");
			}
		}
		return sheetNameList;	
	}
	
	@Test(dataProvider="getSheetName")
	public void testMethod(String sheetName){
        TestExecutor te=new TestExecutor();
        te.run(sheetName);
	}
	
		@AfterClass
		public void closeBrowser(){
			if(filePath != null){
				driver.close();
				driver.quit();
		    }	
		}
}
