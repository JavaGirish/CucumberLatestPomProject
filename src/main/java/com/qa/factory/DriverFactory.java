package com.qa.factory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public WebDriver driver;
	
	// create thread local driver
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<> ();
	
	
	// Initialize browser
	public WebDriver init(String browser) {
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver());
			
		}
		
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
			
		}
		
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
		
		return getDriver();
		
		
	}
	
	
	public static synchronized  WebDriver getDriver() {
		
		return tlDriver.get();
		
	}
	

}
