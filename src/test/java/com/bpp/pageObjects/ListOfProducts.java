package com.bpp.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ListOfProducts extends PageHeader{
	
	public ListOfProducts(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[starts-with(@class, 'product-list-item')]")
	private List<WebElement> productList;
	
	@FindBy(xpath="//div[starts-with(@class, 'product-list-item')]//div[@class='title']")
	private List<WebElement> productTitle;

	public List<WebElement> getProductList() {
		return productList;
	}

	public List<WebElement> getProductTitle() {
		return productTitle;
	}

}
