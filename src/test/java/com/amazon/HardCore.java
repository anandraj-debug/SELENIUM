package com.amazon;

import java.time.Duration;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.mutable.MutableBoolean;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.runtime.directive.Foreach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HardCore {

	 WebDriver driver;
	 String Title;
	@BeforeClass
	public void BrowserLaunch() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
	}
	@Test (priority=0)
	public void selectBaby() {
		
		WebElement dropDown = driver.findElement(By.id("searchDropdownBox"));
		//dropDown.click();
		List<WebElement> baby = driver.findElements(By.xpath("//select[@id='searchDropdownBox']//child::option"));
		baby.stream().filter(c->c.getText().equalsIgnoreCase("Baby")).forEach(c->c.click());
	}
	@Test(priority=1)
	public void searchbox() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement box = driver.findElement(By.id("twotabsearchtextbox"));
		box.sendKeys("toys");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		Thread.sleep(3000);
		List<WebElement> list = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath
		("//div[@class='autocomplete-results-container']//*//div[@class='s-suggestion s-suggestion-ellipsis-direction']")));
		list.stream().filter(c->c.getText().equalsIgnoreCase("toys")).collect(Collectors.toList()).get(0).click();
	}	
	@Test(priority=2)
		public void minimumPrice() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Title=driver.getTitle();
		List<WebElement> products = driver.findElements(By.xpath("//div[@data-index]//*//span[@class='a-price-whole']"));
		WebElement webElement = products.stream()
				.filter(c -> !StringUtils.isEmpty(c.getText()))
				.min(Comparator.comparingInt(c->Integer.valueOf(c.getText().replaceAll(",", "")))).get();
		System.out.println("gettext "+webElement.getText());
		webElement.click();
			}
	@Test (priority=3)
	public void resultPage() {
		Set<String> tabs = driver.getWindowHandles();
		for(String tab:tabs) {
		if(!(Title==driver.getTitle())) {
			driver.switchTo().window(tab);
		}
		
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement addCart = driver.findElement(By.id("add-to-cart-button"));
		wait.until(ExpectedConditions.elementToBeClickable(addCart));
		addCart.click();
		WebElement goToCart = driver.findElement(By.xpath("//span[@id='sw-gtc']//child::span[@class='a-button-inner']"));
		goToCart.click();
		WebElement quantity = driver.findElement(By.xpath("//span[@class='a-button-text a-declarative']"));
		wait.until(ExpectedConditions.elementToBeClickable(quantity));
		quantity.click();
		WebElement increment = driver.findElement(By.xpath("//ul[@role='listbox']//child::li"));
		increment.sendKeys("2");
		WebElement delete = driver.findElement(By.xpath("//input[@value='Delete']"));
		wait.until(ExpectedConditions.elementToBeClickable(delete));
		delete.click();
	}

}
