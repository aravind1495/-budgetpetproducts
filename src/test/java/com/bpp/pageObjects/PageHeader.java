package com.bpp.pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageHeader {

	@FindBy(xpath="(//a[text()='Login'])[1]")
	private WebElement login;
	
	@FindBy(xpath="//span[text()='Logout']")
	private WebElement logout;
	
	@FindBy(xpath="//input[@id='email']")
	private WebElement email;
	
	@FindBy(xpath="//input[@id='password']")
	private WebElement pass;
	
	@FindBy(xpath="//div[@class='modal-body']/div/div[last()]")
	private WebElement loginBtn;
	
	@FindBy(xpath="//div[@class='modal-body']/div/div[contains(@class, 'alert-dange')]")
	private WebElement loginAlert;
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccount;
	
	@FindBy(xpath="//div[@id='main_nav3']//span[@id='menu-shop-by-pet']")
	private WebElement shopByPet;
	
	@FindBy(xpath="//span[@id='nav-bird-tab']")
	private WebElement shopByPetBird;
	
	@FindBy(xpath="//div[@id='nav-bird-tab']/descendant::h5[text()='Food And Treats']/following-sibling::div//a")
	private WebElement foodTreatsViewAll;
	
	@FindBy(xpath="(//div[starts-with(@class, 'nav-cart-btn')])[2]/a")
	private WebElement cartIcon;
	
	@FindBy(xpath="(//div[starts-with(@class, 'nav-cart-btn')])[2]/span")
	private WebElement cartCount;
	
	@FindBy(xpath="//div[@id='dropdownAccountDropdown']/a[1]")
	private WebElement accountSummary;
	
	@FindBy(xpath="//div[@id='dropdownAccountDropdown']/a[2]")
	private WebElement accountOrderHistory;
	
	@FindBy(xpath="//div[@id='dropdownAccountDropdown']/a[3]")
	private WebElement accountAutoDelivery;
	
	@FindBy(xpath="//div[@id='dropdownAccountDropdown']/a[4]")
	private WebElement accountMyPets;
	
	@FindBy(xpath="//div[@id='dropdownAccountDropdown']/a[5]")
	private WebElement accountMyAddress;
	
	@FindBy(xpath="//div[@id='dropdownAccountDropdown']/a[6]")
	private WebElement accountFavourites;
	
	@FindBy(xpath="//input[@id='autocomplete-0-input']")
	private WebElement searchBox;
	
	@FindBy(xpath="(//ul[@id='autocomplete-0-list'])[1]/li")
	private List<WebElement> searchSuggesstion;
	
	public WebElement getLogin() {
		return login;
	}

	public WebElement getEmail() {
		return email;
	}

	public WebElement getPass() {
		return pass;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}

	public WebElement getLoginAlert() {
		return loginAlert;
	}

	public WebElement getMyAccount() {
		return myAccount;
	}

	public WebElement getLogout() {
		return logout;
	}
	
	public WebElement getShopByPet() {
		return shopByPet;
	}

	public WebElement getShopByPetBird() {
		return shopByPetBird;
	}

	public WebElement getFoodTreatsViewAll() {
		return foodTreatsViewAll;
	}

	public WebElement getCartIcon() {
		return cartIcon;
	}

	public WebElement getCartCount() {
		return cartCount;
	}

	public WebElement getAccountSummary() {
		return accountSummary;
	}

	public WebElement getAccountOrderHistory() {
		return accountOrderHistory;
	}

	public WebElement getAccountAutoDelivery() {
		return accountAutoDelivery;
	}

	public WebElement getAccountMyPets() {
		return accountMyPets;
	}

	public WebElement getAccountMyAddress() {
		return accountMyAddress;
	}

	public WebElement getAccountFavourites() {
		return accountFavourites;
	}

	public WebElement getSearchBox() {
		return searchBox;
	}

	public List<WebElement> getSearchSuggesstion() {
		return searchSuggesstion;
	}

}
