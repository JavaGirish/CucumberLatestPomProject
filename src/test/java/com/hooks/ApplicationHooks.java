package com.hooks;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.factory.DriverFactory;
import com.qa.utils.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {
	
	 private WebDriver driver;
	 private DriverFactory driverfactory;
	 private ConfigReader reader;
	 Properties prop;
	 
	 @Before(order = 0) 
	 public void getProperty() {
		 reader = new ConfigReader();
		 prop = reader.init_prop();
		 
	 }
	 
	 @Before(order =1)
	 public void launchBrowser() {
		 String browserName = prop.getProperty("browser");
		 driverfactory = new DriverFactory();
		 driver = driverfactory.init(browserName);
		  
	 }
	 
	 
	 @After(order =0)
	 public void quitBrowser() {
		 driver.quit();
		 
		 
	 }
	 
	 @After(order =1)
	 public void tearDown(Scenario scenario) {
		 if(scenario.isFailed()) {
			 
			// take screenshot:
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);
			 
			 
		 }
		 
	 }
	 
	 
	 
	 
}
