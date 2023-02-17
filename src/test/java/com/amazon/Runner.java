package com.web.amazon;

import java.io.IOException;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.base.pack.Base;
import com.base.pack.DriverBase;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features=".\\java\\com\\web\\amazon\\AmazonApp.feature",
glue=".\\com\\web\\amazon\\StepDefenition.java",monochrome=true,
dryRun=false,plugin={"pretty",
"html:Report/htmlReport.html",

"json:Report/jsonreport.json",
"junit:Report/junitReport.xml"})
public class Runner extends DriverBase  {
	public static WebDriver driver = null;
	Base bs;
	StepDefenition sd;
	@BeforeAll
	public void browserLaunch() throws IOException {
		String browser = ConfigurationReaderHelp.getInstance().getBrowser();
		driver=browserLaunch(browser);
		bs=new Base(driver);
		sd=new StepDefenition(driver);
		String url = ConfigurationReaderHelp.getInstance().getUrl();
		bs.getUrl(url);
	}
	
	@AfterAll
	public void browserClose() {
		bs.closeOrQuit("close");
	}
}
