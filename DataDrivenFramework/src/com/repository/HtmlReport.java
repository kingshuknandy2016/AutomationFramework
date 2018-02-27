package com.repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


public class HtmlReport {

 public static void main(String[] args)
    {
	 HtmlReport htm= new HtmlReport();
	 htm.GenreateReport("Sidd1asdmasdas", "Sidd2asdasdas", "dd", "Pass", "Pass");
    }  
public void GenreateReport(String ModuleName,String TestId,String Actual,String Expected,String Status) {
	
	  String timestamp = 
		    new java.text.SimpleDateFormat("MM/dd/yyyy h:mm:ss a").format(new Date());
	   
        try {
            StringBuilder htmlStringBuilder=new StringBuilder();
            htmlStringBuilder.append("<html><head><title>Velloe Automation Result </title></head>");
            htmlStringBuilder.append("<body>");
            htmlStringBuilder.append("<table border=\"1\" bordercolor=\"000099\" width=\"100%\"height=\"5%\" overflow=\"hidden\" white-space=\"nowrap\" text-overflow=\"ellipsis\">");
            htmlStringBuilder.append("<col style=\"width:10%\" height=\"5%\" overflow=\"hidden\" white-space=\"nowrap\" text-overflow=\"ellipsis\">");
            htmlStringBuilder.append("<col style=\"width:25%\" height=\"5%\" overflow=\"hidden\" white-space=\"nowrap\" text-overflow=\"ellipsis\">");
            htmlStringBuilder.append("<col style=\"width:25%\" height=\"5%\" overflow=\"hidden\" white-space=\"nowrap\" text-overflow=\"ellipsis\">");
            htmlStringBuilder.append("<col style=\"width:25%\" height=\"5%\" overflow=\"hidden\" white-space=\"nowrap\" text-overflow=\"ellipsis\">");
            if(Status.equalsIgnoreCase("pass")){
            htmlStringBuilder.append("<col style=\"width:5%\" bgcolor=\"#00FF00\" height=\"5%\" overflow=\"hidden\" white-space=\"nowrap\" text-overflow=\"ellipsis\">");
            }
            else{
            htmlStringBuilder.append("<col style=\"width:5%\" bgcolor=\"#FF0000\" height=\"5%\" overflow=\"hidden\" white-space=\"nowrap\" text-overflow=\"ellipsis\">");	
            }
            htmlStringBuilder.append("<col style=\"width:10%\" height=\"5%\" overflow=\"hidden\" white-space=\"nowrap\" text-overflow=\"ellipsis\">");            
            String projectPath = System.getProperty("user.dir");
            String tempFile = projectPath + File.separator+"Execution_Summary.html";
            File file = new File(tempFile);
            if(!file.exists()){
            htmlStringBuilder.append("<thead> <tr><th>Module Name</th><th>TestId</th><th>Expected</th><th>Actual</th><th>Status</th><th>Run Time</th></tr></thead>");
           // htmlStringBuilder.append("<tr><td><b>ModuleName</b><td><b>TestId</b></td><td><b>Expected</b></td><td><b>Actual</b></td><td><b>TestResult</b></td><td><b>Time</b></td></tr>");
            }
            htmlStringBuilder.append("<tr><td>"+ModuleName+"</td><td>"+TestId+"</td><td>"+Expected+"</td><td>"+Actual+"</td><td>"+Status+"</td><td>"+timestamp+"</td></tr>");            
            htmlStringBuilder.append("</table></body></html>");
            WriteToFile(htmlStringBuilder.toString(),"Execution_Summary.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void WriteToFile(String fileContent, String fileName) throws IOException {
        String projectPath = System.getProperty("user.dir");
        String tempFile = projectPath + File.separator+fileName;
        File file = new File(tempFile);
        
        if(!file.exists()){
			file.createNewFile();
		}
        
        
        /*if (file.exists()) {
              File newFileName = new File(projectPath + File.separator+ "PreviousRun_"+fileName);
			  file.renameTo(newFileName);
			  file.createNewFile();
        }*/
        
        FileWriter fileWritter = new FileWriter(file.getAbsoluteFile(),true);
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        bufferWritter.write(fileContent);
        bufferWritter.close();        
        
/*        OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
        Writer writer=new OutputStreamWriter(outputStream);
        writer.write(fileContent);
        writer.close();*/
    }
}