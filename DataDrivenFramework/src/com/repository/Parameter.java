package com.repository;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import jxl.Sheet;
import jxl.Workbook;

public class Parameter {
	
	private static String InputValue;
	

	/*************************Method to Read Excel*******************************************************/

public static String GetInput(String ModuleName, String TestId,int ColumnName ) throws FileNotFoundException {

		//FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "\\ObjectRepository\\ValloeTestData.xls");
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"\\Utility\\TestData.xls");
		try {
			Workbook w = Workbook.getWorkbook(fi);
			Sheet s = w.getSheet(ModuleName);
			for (int row = 1; row < s.getRows(); row++) {

				/***************Read The Module Name ********************************/
				String ModuleName1 = s.getCell(0, row).getContents();
				System.out.println("Module name:"+ModuleName1);

				/***************Read The test Name ********************************/
				String TestName1 = s.getCell(1, row).getContents();
				System.out.println("Test name:"+TestName1);

				if (ModuleName1.equalsIgnoreCase(ModuleName) && TestName1.equalsIgnoreCase(TestId)) {

					/***************Read The Xpath Expression Name ********************************/
					InputValue = s.getCell(ColumnName, row).getContents();
					System.out.println("Feild value:"+InputValue);
					break;
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return InputValue;
		
	}

public static void GetOutput(String ModuleName, String TestId, String Actual,String Status) throws FileNotFoundException {
	try {
	FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\Utility\\TestData.xls");
	HSSFWorkbook workbook = new HSSFWorkbook(file);
	HSSFSheet sheet = workbook.getSheet(ModuleName);
	
		for (int row = 1; row < sheet.getPhysicalNumberOfRows(); row++) {

			/***************Read The Module Name ********************************/
			String ModuleName1 = sheet.getRow(row).getCell((short) 0).getStringCellValue();
			System.out.println(ModuleName1);

			/***************Read The test Name ********************************/
			String TestName1 = sheet.getRow(row).getCell((short) 1).getStringCellValue();
			System.out.println(TestName1);

			if (ModuleName1.equalsIgnoreCase(ModuleName) && TestName1.equalsIgnoreCase(TestId)) {

				/***************Read The Xpath Expression Name ********************************/
				sheet.getRow(row).createCell((short) 3).setCellValue(Actual);
				sheet.getRow(row).createCell((short) 4).setCellValue(Status);
				/*FileOutputStream out = new FileOutputStream(new File("E:\\Selenium_Practise\\Aurora_Automation\\Result\\VelloeExecutionLog.xls"));
				      workbook.write(out);*/
				break;
			}
		}
		FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.dir")+"\\Utility\\TestData.xls"));
	      workbook.write(out);		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
 }

public static String GetModuleExecutionStatus(String ModuleName) throws FileNotFoundException {	
	FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"\\Utility\\TestData.xls");
	String moduleExecutionStatus = null;
	try {
		Workbook w = Workbook.getWorkbook(fi);
		Sheet s = w.getSheet("Modules");
		for (int row = 1; row < s.getRows(); row++) {
			/***************Read The Module Name ********************************/
			String ModuleName1 = s.getCell(1, row).getContents();
			moduleExecutionStatus = s.getCell(2, row).getContents();
			if (ModuleName1.equalsIgnoreCase(ModuleName)) {
			break;				
			}
		}
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	return moduleExecutionStatus;
	
}



}