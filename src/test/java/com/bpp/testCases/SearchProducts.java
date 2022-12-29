package com.bpp.testCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.bpp.pageObjects.Cart;
import com.bpp.pageObjects.LandingPage;
import com.bpp.pageObjects.ListOfProducts;
import com.bpp.pageObjects.ProductFocus;

public class SearchProducts extends BaseClass{
	
	@Test
	@Parameters({"searchKey"})
	public void verifySearchSuggestion(String searchKey) {
		
		SoftAssert assertion = new SoftAssert();
		LandingPage home = new LandingPage(driver);
		
		home.getSearchBox().sendKeys(searchKey);
		
		List<WebElement> searchSuggestion = home.getSearchSuggesstion();
		
		boolean checkSearchSuggestion = false;
		for(WebElement search : searchSuggestion){
			
			if (search.getText().toLowerCase().startsWith(searchKey.toLowerCase())) {
				checkSearchSuggestion = true;
				continue;
			} else {
				checkSearchSuggestion = false;
				break;
			}
			
		}
		
		assertion.assertTrue(checkSearchSuggestion, 
				"Search Suggesstion contains result that do not start with "+searchKey);
		assertion.assertAll();
	}
	
	@Test
	@Parameters({"searchKey", "selectSearchSuggestion", "prodToSelect"})
	public void searchAndSelectProdFromSuggesstion(String searchKey, String selectSearchSuggestion, String prodToSelect) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		SoftAssert assertion = new SoftAssert();
		
		LandingPage home = new LandingPage(driver);
		
		home.getSearchBox().sendKeys(searchKey);
		
		List<WebElement> searchSuggestion = home.getSearchSuggesstion();
		
		boolean verifySuggesstion = false;
		for(WebElement search : searchSuggestion){
			
			if (search.getText().equalsIgnoreCase(selectSearchSuggestion)) {
				search.click();
				verifySuggesstion = true;
				break;
			}
		}
		
		assertion.assertTrue(verifySuggesstion, 
				"Suggestion list doesn't contain "+selectSearchSuggestion);
		
		selectProduct(prodToSelect);
		
		ProductFocus prodFocus = new ProductFocus(driver);
		
		wait.until(ExpectedConditions.visibilityOf(prodFocus.getPayPalPopup()));
		
		prodFocus.getAddToCart().click();
		prodFocus.getViewCart().click();
		prodFocus.getCartIcon().click();
		
		Cart cart = new Cart(driver);
		
		List<WebElement> todayShoppingList = cart.getTodayShoppingList();
		
		boolean cartVerify = false;
		for (int i=1; i<todayShoppingList.size(); i++) {
			
			if (todayShoppingList.get(i).findElement(By.tagName("td")).getText().contains(prodToSelect)) {
				cartVerify = true;
				break;
			}
		}
		
		assertion.assertTrue(cartVerify, 
				"Today's Shopping List doesn't contains the product "+prodToSelect);
		assertion.assertAll();
	}
	
	public void selectProduct(String prodName) {

		ListOfProducts prodListPage = new ListOfProducts(driver);

		List<WebElement> prodTitle = prodListPage.getProductTitle();

		for (int i = 0; i < prodTitle.size(); i++) {

			if (prodTitle.get(i).getText().toLowerCase().contains(prodName.toLowerCase())) {
				prodTitle.get(i).click();
				break;
			}
		}
	}

}
