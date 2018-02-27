package com.testexecutor;

import org.testng.annotations.Test;
import com.modules.Login;
import com.repository.LaunchApplication;
import com.repository.Parameter;

public class TestExecutor {
	
	private static String moduleExecutionStatus;
	
	

	public static void main(String[] args) throws Exception {
		System.out.println("Executing");
		String ModuleNameArray[] = new String[5];		
		ModuleNameArray[0] = "Login";
		ModuleNameArray[1] = "Registration";
		ModuleNameArray[2] = "BusinessHours";
		ModuleNameArray[3] = "ContactInformation";
		ModuleNameArray[4] = "GeneralInformation";
		
		
			for(int i=0;i<ModuleNameArray.length;i++){
			
				moduleExecutionStatus = Parameter.GetModuleExecutionStatus(ModuleNameArray[i]);		
				
			    if(ModuleNameArray[i].equalsIgnoreCase("Login")&& moduleExecutionStatus.equalsIgnoreCase("Yes"))
			    {
				LaunchApplication.applicationLaunch();		
				Login login=new Login();
				login.LoginFeature();
			    }
			    else if(ModuleNameArray[i].equalsIgnoreCase("Registration")&& moduleExecutionStatus.equalsIgnoreCase("Yes"))
			    {
			    LaunchApplication.applicationLaunch();	
				/*Registration re=new Registration();
				re.RegistrationValidity();*/
				}
			    else if(ModuleNameArray[i].equalsIgnoreCase("BusinessHours")&& moduleExecutionStatus.equalsIgnoreCase("Yes"))
			    {
			    LaunchApplication.applicationLaunch();	
				/*BusinessHours bsh = new BusinessHours();
				bsh.BusinessHoursExecution();*/
			    }
			    else if(ModuleNameArray[i].equalsIgnoreCase("BusinessHours")&& moduleExecutionStatus.equalsIgnoreCase("Yes"))
			    {
			    LaunchApplication.applicationLaunch();
				/*ContactInformation cnf = new ContactInformation();
				cnf.ContactInformationCheck();*/
			    }
			    else if(ModuleNameArray[i].equalsIgnoreCase("BusinessHours")&& moduleExecutionStatus.equalsIgnoreCase("Yes"))
			    {
			    LaunchApplication.applicationLaunch();
			    /*GeneralInformation cnf = new GeneralInformation();
				cnf.checkGeneralInformation();*/
			    }
			}

		}

	}			
			
	

