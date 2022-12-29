package com.bpp.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountOrderHistory extends PageHeader{
	
	public MyAccountOrderHistory(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="((//table)[2]//tr)")
	private List<WebElement> recentOrders;

	public List<WebElement> getRecentOrders() {
		return recentOrders;
	}
	
	
}
