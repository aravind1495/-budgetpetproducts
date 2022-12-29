package com.bpp.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cart extends PageHeader{
	
	public Cart(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[text()='Proceed to Checkout']")
	private WebElement proceedToCheckout;
	
	@FindBy(xpath="//a[contains(text(), 'Continue shopping')]")
	private WebElement continueShopping;
	
	@FindBy(xpath="(//input[@id='email'])[2]")
	private WebElement emailInCart;
	
	@FindBy(xpath="(//input[@id='password'])[2]")
	private WebElement passInCart;
	
	@FindBy(xpath="(//div[@class='modal-body']/div/div[last()])[2]")
	private WebElement loginBtnInCart;
	
	@FindBy(xpath="//div[@id='cart-items-container']/table//tr")
	private List<WebElement> todayShoppingList;

	public WebElement getProceedToCheckout() {
		return proceedToCheckout;
	}

	public WebElement getContinueShopping() {
		return continueShopping;
	}

	public WebElement getEmailInCart() {
		return emailInCart;
	}

	public WebElement getPassInCart() {
		return passInCart;
	}

	public WebElement getLoginBtnInCart() {
		return loginBtnInCart;
	}

	public List<WebElement> getTodayShoppingList() {
		return todayShoppingList;
	}

}
