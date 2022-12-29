package com.bpp.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Checkout extends PageHeader{
	
	public Checkout(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//u[text()='save order and pay later']")
	private WebElement saveAndPayLater;
	
	@FindBy(xpath="//div[@class='swal2-actions']/button[1]")
	private WebElement saveAndPayLaterOk;
	
	@FindBy(xpath="//div[@class='swal2-actions']/button[2]")
	private WebElement saveAndPayLaterCancel;

	public WebElement getSaveAndPayLater() {
		return saveAndPayLater;
	}

	public WebElement getSaveAndPayLaterOk() {
		return saveAndPayLaterOk;
	}

	public WebElement getSaveAndPayLaterCancel() {
		return saveAndPayLaterCancel;
	}
	
	
}
