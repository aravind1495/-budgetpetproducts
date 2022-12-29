package com.bpp.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends PageHeader{
	
	public LandingPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}
