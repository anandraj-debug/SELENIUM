package com.amazon;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.base.pack.Base;
import com.base.pack.DriverBase;
import com.web.amazon.AmazonHomeFactory;
import com.web.amazon.ConfigurationReaderHelp;
import com.web.amazon.FailureManager;
import com.web.amazon.ResultPageFactory;
import com.web.amazon.SingetonDesignPattern;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNgTest extends DriverBase {

	WebDriver driver;
	SingetonDesignPattern sdp;
	Base bs;

	@Test(priority=0)
	public void browserLaunh() throws IOException {
		String browser = ConfigurationReaderHelp.getInstance().getBrowser();
		driver=browserLaunch(browser);
		bs=new Base(driver);
		sdp=new SingetonDesignPattern(driver);
		String url = ConfigurationReaderHelp.getInstance().getUrl();
		bs.getUrl(url);
	}
	@Test(priority=1)
	public void dropDown() {
		sdp.homePage().selectFromDropDown("Baby");
	}
	@Test(priority=2)
	public void search() throws InterruptedException {
		sdp.homePage().searchBox("toys");
	}
	@Test(priority=3)
	public void minimumPrice() {
		sdp.homePage().minimumPrice();
	}
	@Test(dependsOnMethods ="minimumPrice" )
	public void addCart() {
		sdp.resultPage().addToCart();
	}
	@Test(priority=5)
	public void goCart() {
		sdp.resultPage().goToCart();
	}
	@Test(priority=6)
	public void quantityIncreas() throws InterruptedException {
		sdp.resultPage().increaseQuantity("2");
	}
	@Test(priority=7)
	public void delete() {
		sdp.resultPage().delete();
	}@Test(dependsOnMethods ="delete" )
	public void browserclose() {
		bs.closeOrQuit("close");
	}
	@AfterMethod
	public void screenShot(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			FailureManager manager= new FailureManager(driver);
			manager.takeScreen(result.getName());
		}
	}

}
