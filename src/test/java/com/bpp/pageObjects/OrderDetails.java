package com.bpp.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderDetails extends PageHeader {

	public OrderDetails(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//table[@id='cart-items-container']/preceding-sibling::h4")
	private WebElement orderID;
	
	@FindBy(xpath="//button[text()='Delete This Order']")
	private WebElement deleteThisOrder;

	public WebElement getOrderID() {
		return orderID;
	}

	public WebElement getDeleteThisOrder() {
		return deleteThisOrder;
	}
		
}
