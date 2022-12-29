package com.bpp.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductFocus extends PageHeader{
	
	public ProductFocus(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//span[text()='Add To Cart'])[1]")
	private WebElement addToCart;
	
	@FindBy(xpath="//a[text()='View Cart']")
	private WebElement viewCart;
	
	@FindBy(xpath="//button[text()='Continue shopping']")
	private WebElement continueShopping;
	
	@FindBy(xpath="(//div[@class='card'])[2]/div")
	private WebElement payPalPopup;

	public WebElement getAddToCart() {
		return addToCart;
	}

	public WebElement getViewCart() {
		return viewCart;
	}

	public WebElement getContinueShopping() {
		return continueShopping;
	}

	public WebElement getPayPalPopup() {
		return payPalPopup;
	}

}
