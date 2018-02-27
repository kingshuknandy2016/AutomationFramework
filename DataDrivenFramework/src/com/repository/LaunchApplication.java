package com.repository;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;




public class LaunchApplication
{
	static String baseUrl;
	
	public static void applicationLaunch() throws Exception {
		System.out.println("Inside Application Launch");
		String driverPath=System.getProperty("user.dir")+"\\Driver\\chromedriver.exe";
		//System.setProperty("webdriver.chrome.driver",driverPath);
		config.driver = new FirefoxDriver();
        baseUrl = "https://accounts.google.com/ServiceLogin?sacu=1&scc=1&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&osid=1&service=mail&ss=1&ltmpl=default&rm=false#identifier";
        config.driver.get(baseUrl);
        config.driver.manage().window().maximize();
        config.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
}	
}