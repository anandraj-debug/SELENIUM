package com.base.pack;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.ScreenshotException;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public  WebDriver driver;

	public Base(WebDriver driver) {
		this.driver=driver;
		
	}
	
	public void getUrl(String url) throws MalformedURLException {
		try {
			driver.get(url);
		} catch (Exception e) {
			throw new MalformedURLException("Entered Invalid URL");
		}
	}

	public void elementSendKeys(WebElement element, String values) {
		try {
			element.sendKeys(values);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void elementClick(WebElement element) {
		try {
			element.click();
		} catch (ElementClickInterceptedException e) {
			e.printStackTrace();
		}
	}

	public void elementIsDisplayed(WebElement element) {
		boolean isDisplayed = element.isDisplayed();
		try {
			if (isDisplayed == true) {
				System.out.println("WebElement is Displayed : true ");
			} else {
				System.out.println("WebElement is Displayed : no ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void elementIsenabled(WebElement element) {
		try {
			boolean isnabled = element.isEnabled();
			if (isnabled == true) {
				System.out.println("WebElement is isnabled : true ");
			} else {
				System.out.println("WebElement is isnabled : no ");
			}
		} catch (ElementNotInteractableException e) {
			e.printStackTrace();
		}
	}

	public void elementIsSelected(WebElement element) {
		try {
			boolean isSelected = element.isEnabled();
			if (isSelected == true) {
				System.out.println("WebElement is isSelected : true ");
			} else {
				System.out.println("WebElement is isSelected : no ");
			}
		} catch (ElementNotInteractableException e) {
			e.printStackTrace();
		}
	}

	public void threadSleep(int sleepValue) {
		try {
			Thread.sleep(sleepValue);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void elementClear(WebElement element) {
		try {
			element.clear();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (ElementNotInteractableException e) {
			e.printStackTrace();
		}
	}

	public void takesScreenShot(String imageName) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(".\\Selenium_projects\\screenshot" + imageName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ScreenshotException e) {
			e.printStackTrace();
		}
	}

	public void screenShot(String name) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File screenshot = ts.getScreenshotAs(OutputType.FILE);
		Path destination = Paths.get(".\\screenshot\\" + name + ".png");
		try {
			Files.move(screenshot.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ScreenshotException e) {
			e.printStackTrace();
		}
	}

	public void closeOrQuit(String action) {
		try {
			if (action.equalsIgnoreCase("close")) {
				driver.close();
			} else if (action.equalsIgnoreCase("quite")) {
				driver.quit();
			} else if (action.equalsIgnoreCase("quiteandclose")) {
				driver.close();
				driver.quit();
			}
		} catch (UnreachableBrowserException e) {
			e.printStackTrace();
		}
	}

	public String driverMethods(String action) {
		try {
			if (action.equalsIgnoreCase("title")) {
				String title=driver.getTitle();
				return title;
			} else if (action.equalsIgnoreCase("currentUrl")) {
				String url=driver.getCurrentUrl();
				return url;
			}
		} catch (UnreachableBrowserException e) {
			e.printStackTrace();
		}
		 return null;
	}

	public Alert alertMethod() {
		Alert alert = null;
		try {
			alert = driver.switchTo().alert();
		} catch (NoAlertPresentException e) {
			e.printStackTrace();
		}
		return alert;
	}

	public  void frameOptins(WebElement element, String option, String value) {
		try {
			if (option.equalsIgnoreCase("index")) {
				int index = Integer.parseInt(value);
				driver.switchTo().frame(index);
			} else if (option.equalsIgnoreCase("name")) {
				driver.switchTo().frame(value);
			} else if (option.equalsIgnoreCase("element")) {
				driver.switchTo().frame(element);
			} else if (option.equalsIgnoreCase("parentframe")) {
				driver.switchTo().parentFrame();
			} else if (option.equalsIgnoreCase("default")) {
				driver.switchTo().defaultContent();
			}
		} catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
	}

	public void navigate(String action, String url) {
		try {
			if (action.equalsIgnoreCase("back")) {
				driver.navigate().back();
			} else if (action.equalsIgnoreCase("forward")) {
				driver.navigate().forward();
			} else if (action.equalsIgnoreCase("refresh")) {
				driver.navigate().refresh();
			} else if (action.equalsIgnoreCase("url")) {
				driver.navigate().to(url);
			}
		} catch (NoSuchWindowException e) {
			e.printStackTrace();
		}
	}

	public static void selecMethod(WebElement element, String type, String values) {
		try {
			Select elementSelect = new Select(element);
			if (type.equalsIgnoreCase("index")) {
				int index = Integer.parseInt(values);
				elementSelect.selectByIndex(index);
			} else if (type.equalsIgnoreCase("value")) {
				elementSelect.selectByValue(values);
			} else if (type.equalsIgnoreCase("text")) {
				elementSelect.selectByVisibleText(values);
			}

			else if (type.equalsIgnoreCase("deselectAll")) {
				elementSelect.deselectAll();
			} else if (type.equalsIgnoreCase("deselectByIndex")) {
				int index = Integer.parseInt(values);
				elementSelect.deselectByIndex(index);
			} else if (type.equalsIgnoreCase("deselectByValue")) {
				elementSelect.deselectByValue(values);
			} else if (type.equalsIgnoreCase("deselectByVisibleText")) {
				elementSelect.deselectByVisibleText(values);
			} else if (type.equalsIgnoreCase("getAllSelectedOptions")) {
				elementSelect.getAllSelectedOptions();
			} else if (type.equalsIgnoreCase("getFirstSelectedOption")) {
				elementSelect.getFirstSelectedOption();
			} else if (type.equalsIgnoreCase("getOptions")) {
				elementSelect.getOptions();
			} else if (type.equalsIgnoreCase("ismultipule")) {
				elementSelect.isMultiple();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (ElementNotInteractableException e) {
			e.printStackTrace();
		}
	}

	public void javaScriptExecutor(WebElement element, int range, String action) {

		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			if (action.equalsIgnoreCase("scrollintoview")) {
				js.executeScript("arguments[0].scrollIntoView();", element);
			} else if (action.equalsIgnoreCase("scrollby")) {
				js.executeAsyncScript("window.ScrollBy(0," + range + ");");
			} else if (action.equalsIgnoreCase("click")) {
				js.executeScript("arguments[0].click();", element);
			}
		} catch (JavascriptException e) {
			e.printStackTrace();
		}
	}
	public  Actions actions(WebElement element, String type) {
		Actions action = new Actions(driver);
		if (type.equalsIgnoreCase("click")) {
			action.click(element).perform();
		} else if (type.equalsIgnoreCase("context")) {
			action.contextClick(element);
		} else if (type.equalsIgnoreCase("double")) {
			action.doubleClick(element);
		}
		return action;
	}
}
