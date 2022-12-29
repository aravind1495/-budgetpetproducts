package com.bpp.testCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.bpp.pageObjects.Cart;
import com.bpp.pageObjects.Checkout;
import com.bpp.pageObjects.LandingPage;
import com.bpp.pageObjects.ListOfProducts;
import com.bpp.pageObjects.MyAccountOrderHistory;
import com.bpp.pageObjects.OrderDetails;
import com.bpp.pageObjects.ProductFocus;

public class VerifyOrder extends BaseClass {

	SoftAssert assertion;
	
	@Test
	@Parameters({ "prodToSelect", "email", "password" })
	public void navigateProductAndVerifyOrder(String prodToSelect, String email, String pass) {

		assertion = new SoftAssert();
		Actions act = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LandingPage home = new LandingPage(driver);

		//staleHandling(home.getShopByPet());
		
		home.getShopByPet().click();
		home.getShopByPetBird().click();
		home.getFoodTreatsViewAll().click();

		assertion.assertTrue(driver.getTitle().contains("Food And Treats"), "Navigated to incorrect page");

		selectProduct(prodToSelect);
		
		
		ProductFocus prodFocus = new ProductFocus(driver);
		
		//In the Product page, PayPal popup is loading few seconds after the page load
		//Sometimes its casuing Stale Exception when clicking AddToCart
		//So waiting for the PayPal popup to come and then clicking AddToCart
		wait.until(ExpectedConditions.visibilityOf(prodFocus.getPayPalPopup()));
		
		prodFocus.getAddToCart().click();
		prodFocus.getViewCart().click();

		Cart cart = new Cart(driver);
		assertion.assertTrue(cart.getCartCount().getText().equals("1"), "Mismatch in Cart counts");
		cart.getProceedToCheckout().click();

		login(cart, email, pass);

		Checkout checkout = new Checkout(driver);
		act.moveToElement(checkout.getSaveAndPayLater()).perform();
		checkout.getSaveAndPayLater().click();
		checkout.getSaveAndPayLaterOk().click();

		OrderDetails orderDetails = new OrderDetails(driver);
		String orderID = orderDetails.getOrderID().getText();

		orderDetails.getMyAccount().click();
		orderDetails.getAccountOrderHistory().click();

		verifyOrderedProduct(orderID);

		assertion.assertAll();

	}

	public void login(Cart loginWindow, String email, String pass) {

		loginWindow.getEmailInCart().sendKeys(email);
		loginWindow.getPassInCart().sendKeys(pass);
		loginWindow.getLoginBtnInCart().click();

	}

	public void selectProduct(String prodName) {

		ListOfProducts prodListPage = new ListOfProducts(driver);

		// List<WebElement> prodList = prodListPage.getProductList();
		List<WebElement> prodTitle = prodListPage.getProductTitle();

		for (int i = 0; i < prodTitle.size(); i++) {

			if (prodTitle.get(i).getText().toLowerCase().contains(prodName.toLowerCase())) {
				prodTitle.get(i).click();
				break;
			}
		}
	}

	public void verifyOrderedProduct(String orderID) {

		MyAccountOrderHistory orderHistory = new MyAccountOrderHistory(driver);

		//Getting each rows from Recent Order table
		List<WebElement> rows = orderHistory.getRecentOrders();

		boolean orderflag = false;
		//Starting from row of [1] and ignoring row of [0] as its a Table header
		for (int i = 1; i < rows.size(); i++) {

			//Getting each columns from the row
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
			//Order ID is the first column which is cols[0] so not Iterating through entire columns
			String order = cols.get(0).getText();
			
			if (orderID.contains(order)) {
				orderflag = true;
				break;
			}
		}

		assertion.assertTrue(orderflag, "Order is not present in the Recent orders list");

	}
}
