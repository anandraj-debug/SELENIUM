package com.web.amazon;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
public class FailureManager {
public static WebDriver driver;
	static final Logger log=LogManager.getLogger(MethodHandles.lookup().lookupClass());
	public FailureManager(WebDriver driver) {
	this.driver=driver;
}
public void takeScreen(String name) {
	TakesScreenshot ts=(TakesScreenshot)driver;
	File screen= ts.getScreenshotAs(OutputType.FILE);
	Path destination=Paths.get("C:\\Users\\anand\\eclipse-workspace\\Selenium_projects\\screenshot"+name+".png");
	try {
		Files.move(screen.toPath(), destination,StandardCopyOption.REPLACE_EXISTING);
	}catch(IOException e) {
		log.error("Exception moving screenshot from{} to {}",screen,destination, e);
		
	}

}
}
