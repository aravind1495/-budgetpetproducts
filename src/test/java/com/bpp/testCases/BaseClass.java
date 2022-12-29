package com.bpp.testCases;

import java.time.Duration;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	static WebDriver driver;

	@BeforeSuite
	@Parameters({"browser"})
	public void getDriver(@Optional ("Chrome") String browser) {

		switch (browser.toLowerCase()) {

		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
			
		default:
			System.out.println("Invalid Browser");

		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@BeforeTest
	@Parameters({"url"})
	public void openURL(String url) {
		driver.get(url);
	}
	
	@AfterSuite (enabled = true)
	public void exit() {
		driver.quit();
	}

	public void staleHandling(WebElement ele) {

		for (int i = 0; i <= 10; i++) {
			try {
				ele.click();
				break;
			} catch (StaleElementReferenceException e) {
				if (i >= 10) {
					System.out.println("Reached maximum retry limit looking for the element " + ele);
					e.printStackTrace();
					break;
				}
			}
		}
	}

}
