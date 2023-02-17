package com.base.pack;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverBase {

	public WebDriver driver;
	public  WebDriver browserLaunch(String name) {

		try {
			if (name.equalsIgnoreCase("chrome")) {
				ChromeOptions opt = new ChromeOptions();
				opt.addArguments("start-maximized");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(opt);
			} else if (name.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			} else if (name.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (name.equalsIgnoreCase("safari")) {
				WebDriverManager.safaridriver().setup();
				driver = new SafariDriver();
			}

		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}
		return driver;
	}

	public WebDriver browserLaunch(AbstractDriverOptions name1, AbstractDriverOptions name2) {

		try {
			driver = RemoteWebDriver.builder().oneOf(name1).addAlternative(name2).build();
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}
		return driver;

	}

	public WebDriver browserSetup(String browser) {

		try {
			if (browser.equalsIgnoreCase("Chrome")) {
				driver = RemoteWebDriver.builder().oneOf(new ChromeOptions()).build();
			} else if (browser.equalsIgnoreCase("edge")) {
				driver = RemoteWebDriver.builder().oneOf(new EdgeOptions()).build();
			} else if (browser.equalsIgnoreCase("firefox")) {
				driver = RemoteWebDriver.builder().oneOf(new FirefoxOptions()).build();
			} else if (browser.equalsIgnoreCase("safari")) {
				driver = RemoteWebDriver.builder().oneOf(new SafariOptions()).build();
			}
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}

		return driver;

	}

	public WebDriver browserInstance(String browser) {

		try {
			driver = WebDriverManager.getInstance(browser).create();
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}
		return driver;
	}

	public WebDriver browserCreate(String browser) {
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				driver = WebDriverManager.chromedriver().create();
			} else if (browser.equalsIgnoreCase("edge")) {
				driver = WebDriverManager.edgedriver().create();
			} else if (browser.equalsIgnoreCase("safari")) {
				driver = WebDriverManager.safaridriver().create();
			} else if (browser.equalsIgnoreCase("firefox")) {
				driver = WebDriverManager.firefoxdriver().create();
			}
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}
		return driver;
	}

}
