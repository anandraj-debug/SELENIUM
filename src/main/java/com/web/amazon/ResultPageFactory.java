package com.web.amazon;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.pack.Base;

public class ResultPageFactory extends Base {

	private static WebDriver driver;
	int second=5;
	public WebDriverWait wait;
	@FindBy(id="add-to-cart-button")
	private WebElement addToCart;
	
	@FindBy(xpath="//span[@id='sw-gtc']//child::span[@class='a-button-inner']")
	private WebElement goToCart;
	@FindBy(xpath="//span[@class='a-button-text a-declarative']")
	private WebElement quantity;
	@FindBy(xpath="//ul[@role='listbox']//child::li")
	private WebElement increment;
	@FindBy(xpath="//input[@value='Delete']")
	private WebElement delete;
	
	public ResultPageFactory(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	public void addToCart() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(addToCart));
		elementClick(addToCart);
		
	}
	
	public void goToCart() {
		 wait.until(ExpectedConditions.elementToBeClickable(goToCart));
		elementClick(goToCart);

	}
	
	public void increaseQuantity(String in) throws InterruptedException {
		WebElement quant = wait.until(ExpectedConditions.elementToBeClickable(quantity));
		//javaScriptExecutor(quantity,0, "click");
		elementClick(quant);
		Thread.sleep(3000);
		elementSendKeys(increment, in);
	}
	public void delete() {
		//wait.until(ExpectedConditions.elementToBeClickable(delete));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		elementClick(delete);
	}
	

}
