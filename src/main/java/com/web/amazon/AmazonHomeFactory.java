package com.web.amazon;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.pack.Base;

public class AmazonHomeFactory extends Base {

	private static WebDriver driver;
	int seconds=5;
	String Title;
	@FindBy(id="searchDropdownBox")
	private WebElement dropDown;
	
	@FindBy(xpath="//select[@id='searchDropdownBox']//child::option")
	private List<WebElement> select;
	@FindBy(id="twotabsearchtextbox")
	private WebElement searchBox;
	@FindBy(xpath="//div[@data-index]//*//span[@class='a-price-whole']")
	private List<WebElement> minimunPrice;
	@FindBy(xpath="//div[@data-index]//*//span[@class='a-price-whole']")
	private List<WebElement>products;
	private By showBox=By.xpath("//div[@class='autocomplete-results-container']//*//div[@class='s-suggestion s-suggestion-ellipsis-direction']");
	
//	public AmazonHome(WebDriver driver,int seconds) {
//		this(driver);
//		this.driver=driver;
//		PageFactory.initElements(driver, this);
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
//	}
	public AmazonHomeFactory(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	


	public  void visit(String url) {
		 
		 try {
			getUrl(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		 
	}
	
	public void selectFromDropDown(String name) {
		select.stream().filter(c->c.getText().equalsIgnoreCase(name)).forEach(c->c.click());
	}
	
	public void searchBox(String productName) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		elementSendKeys(searchBox, productName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		Thread.sleep(3000);
		List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(showBox));
		products.stream().filter(c->c.getText().equalsIgnoreCase(productName)).collect(Collectors.toList()).get(0).click();
		Title=driverMethods("title");
	}
	
	public void minimumPrice() {
		//List<WebElement> products=sdp.ahf.getProducts();
		WebElement webElement = products.stream()
				.filter(c -> !StringUtils.isEmpty(c.getText()))
				.min(Comparator.comparingInt(c->Integer.valueOf(c.getText().replaceAll(",", "")))).get();
		System.out.println("gettext "+webElement.getText());
		webElement.click();
		List<String> tab=new LinkedList<String>(driver.getWindowHandles());
		tab.stream().filter(a-> !driver.switchTo().window(a).getTitle().equalsIgnoreCase(Title)).forEach(a-> driver.switchTo().window(a));
	}
}


