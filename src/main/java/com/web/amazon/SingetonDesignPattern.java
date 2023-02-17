package com.web.amazon;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.base.pack.Base;

public class SingetonDesignPattern {
	public static WebDriver driver;
	public AmazonHomeFactory ahf;
	public ResultPageFactory rpf;
	public SingetonDesignPattern(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver , this );

	}
	
	public AmazonHomeFactory homePage() {
		if(this.ahf== null) {
			ahf= new AmazonHomeFactory(driver);
		}
		return this.ahf;
	}
	
	public ResultPageFactory resultPage() {
		if(rpf==null) {
			rpf=new ResultPageFactory(driver);
		}
		return this.rpf;
	}

}
