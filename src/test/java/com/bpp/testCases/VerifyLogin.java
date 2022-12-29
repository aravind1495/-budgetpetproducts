package com.bpp.testCases;

import java.time.Duration;

import org.openqa.selenium.StaleElementReferenceException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.bpp.pageObjects.LandingPage;
import com.bpp.utilities.ExcelData;

//This class verifies Login functionality of the page with positive and negative inputs
public class VerifyLogin extends BaseClass {

	static int index = 1;
	
	//Simple method verifies Login function with valid email and password
	
	@Test (enabled = false)
	@Parameters({"email", "password"})
	public void login(String email, String pass) {

		SoftAssert assertion = new SoftAssert();
		LandingPage home = new LandingPage(driver);

		home.getLogin().click();
		home.getEmail().sendKeys(email);
		home.getPass().click();
		home.getPass().sendKeys(pass);
		home.getLoginBtn().click();

		assertion.assertTrue(home.getMyAccount().isDisplayed(), "My Account is not visible");
		assertion.assertAll();

	}

	
	//Verifies Login function with Positive and Negative inputs
	
	@Test(dataProvider = "credentials", dataProviderClass = ExcelData.class, enabled = true)
	public void verifyLogin(String email, String pass) throws InterruptedException {
		
		boolean myAccountIsVisible = false;
		String errorOutput = "";
		
		SoftAssert assertion = new SoftAssert();
		
		LandingPage home = new LandingPage(driver);
		
		//Click Login , enter credentials and click Login in the Login window
		
		//Login button is causing stale element exception 
		//hence using for loop and try catch to retry looking for the element 10 times
		for (int i = 0; i <= 10; i++) {
			try {
				home.getLogin().click();
				break;
			} catch (StaleElementReferenceException e) {
				if (i >= 10) {
					System.out.println("Reached maximum retry limit looking for the element " + home.getLogin());
					e.printStackTrace();
					break;
				}
			}
		}

		home.getEmail().click();
		home.getEmail().sendKeys(email);
		home.getPass().click();
		home.getPass().sendKeys(pass);;
		home.getLoginBtn().click();
		

		//Check if MyAccount is visible to confirm if login is successful or not
		//If visible assign 'true' to myaccount
		//If not visible, login is not successful. Capture the error and assign to errorOutput
		
		//Changing implict wait time to '5' seconds to avoid waiting '10' seconds to find MyAccount
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		try {
			errorOutput = home.getLoginAlert().getText();
		} catch (Exception e) {
			myAccountIsVisible = home.getMyAccount().isDisplayed();
		}
		
		//Changing implict wait time back to '10' seconds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	
	
		//If myaccount is true, logout from the page and write the output to the excel
		//If my account is false, write the output to the excel and refresh the page to exit from Login window
		
		if (myAccountIsVisible) {
			
			assertion.assertTrue(myAccountIsVisible, "My Account is not visible");
			//Create method in BaseClass with for loop try catch to handle stale elements
			staleHandling(home.getLogout());
			ExcelData.writeData(index++, "Success", "Hooray!!! No Errors");
			
		} else {
			
			if (!email.isEmpty() && !pass.isEmpty()) {
				assertion.assertEquals(errorOutput, "These credentials do not match our records.", 
						"Login error alert message mismatch");				
			}else if (email==null || email.isEmpty()) {
				assertion.assertEquals(errorOutput, "The username field is required.", "Login error alert message mismatch");
			} else if (pass==null || pass.isEmpty()){
				assertion.assertEquals(errorOutput, "The password field is required.", "Login error alert message mismatch");
			} else {
				assertion.assertEquals(errorOutput, "The username field is required.The password field is required.", 
						"Login error alert message mismatch");
			}
			
			ExcelData.writeData(index++, "Failed", errorOutput);
			
			driver.navigate().refresh();
			
		}
		
		assertion.assertAll();
	
	}

}
