package com.web.amazon;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;

public class FailureWatcher extends TestWatcher {
	FailureManager manager;

public  FailureWatcher(WebDriver driver) {
	manager= new FailureManager(driver);
}
public void fails(Throwable throwable, Description description) {
		manager.takeScreen(description.getDisplayName());
}
}
