package com.web.amazon;

import java.io.IOException;

import org.junit.Rule;
import org.junit.rules.TestRule;
import org.openqa.selenium.WebDriver;

import com.base.pack.Base;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefenition extends Base {

	public WebDriver driver;
	SingetonDesignPattern sdp=new SingetonDesignPattern(driver);
	public StepDefenition(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	@Rule
	public TestRule testWatcher= new FailureWatcher(driver);
	@Given("user on HOME page")
	public void user_on_home_page() throws IOException {
		String url = ConfigurationReaderHelp.getInstance().getUrl();

	}

	@When("user select\"Baby\" and {string}")
	public void user_select_baby_and(String string) throws InterruptedException {
		sdp.homePage().selectFromDropDown("Baby");
		sdp.homePage().searchBox("toys");

	}

	@Then("user click on minimum price product")
	public void user_click_on_minimum_price_product() {
		sdp.homePage().minimumPrice();
	}

	@Then("user do Add to Cart")
	public void user_do_add_to_cart() throws InterruptedException {
		sdp.resultPage().addToCart();
		sdp.resultPage().goToCart();
		sdp.resultPage().increaseQuantity("2");
		sdp.resultPage().delete();

	}

	
}
