package com.web.amazon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

	Properties p;
	public ConfigurationReader() throws IOException {
		File file=new File("C:\\Users\\anand\\eclipse-workspace\\Selenium_projects\\src\\main\\java\\com\\web\\amazonProperty\\Amazon.properties");
		FileInputStream fi= new FileInputStream(file);
		p=new Properties();
		p.load(fi);
	}
	
	public String getBrowser() {
		String browser=p.getProperty("browser");
		return browser;
	}
	public String getUrl() {
		String url=p.getProperty("url");
		return url;
	}
}
