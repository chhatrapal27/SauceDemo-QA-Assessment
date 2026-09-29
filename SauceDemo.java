package com.saucedemo.tests;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class SauceDemo {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.saucedemo.com/");
		
		// Login 
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		
		// Page validation after login
		String expectedLoginTitle ="Swag Labs";
		String actualLoginTitle = driver.getTitle();
		
		Assert.assertEquals(actualLoginTitle,expectedLoginTitle);
		System.out.println("Login Page Validation Passed");

        // Add product to cart
		driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
		
		// Validate cart count
		String expectedCartCount  = "Cart, 1 items";
		String actualCartCount = driver.findElement(
		        By.xpath("//a[@data-test='shopping-cart-link']")
		).getAttribute("aria-label");
		
		Assert.assertEquals(actualCartCount,expectedCartCount);
		System.out.println("Cart Count Validation Passed");

        // Open cart
		driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).click();
		
		// Validate Cart Page
		String expectedCartTitle ="Your Cart";
		String actualCartTitle = driver.findElement(By.xpath("//span[@class='title']")).getText();
		
		Assert.assertEquals(actualCartTitle,expectedCartTitle);
		System.out.println("Cart Page Validation Passed");

		// Checkout
		driver.findElement(By.xpath("//button[@id='checkout']")).click();
		//Validate Checkout Page
		String expectedCheckoutTitle  = "Checkout: Your Information";
		String actualCheckoutTitle = driver.findElement(
		        By.xpath("//span[@class='title']")
		).getText();
		
		Assert.assertEquals(actualCheckoutTitle,expectedCheckoutTitle);
		System.out.println("Checkout Information Page Validation Passed");


		// Checkout Information
		WebElement firstName = driver.findElement(By.xpath("//input[@id='first-name']"));
		firstName.clear();
		firstName.sendKeys("Chhatrapal");
		
		WebElement lastName = driver.findElement(By.xpath("//input[@id='last-name']"));
		lastName.clear();
		lastName.sendKeys("Maurya");

		WebElement postalAdd = driver.findElement(By.xpath("//input[@id='postal-code']"));
		postalAdd.clear();
		postalAdd.sendKeys("110062");
		
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		
		// Validate Checkout Information Page
		String expectedOverviewTitle = "Checkout: Overview";
		String actualOverviewTitle = driver.findElement(
		        By.xpath("//span[@class='title']")
		).getText();
		Assert.assertEquals(actualOverviewTitle,expectedOverviewTitle);
		System.out.println("Checkout Overview Page Validation Passed");
		
		// Finish Order
		driver.findElement(By.xpath("//button[@id='finish']")).click();
		
		// Validate Order Complete Page
		String expectedCompleteMessage = "Thank you for your order!";
		String actualCompleteMessage = driver.findElement(
		        By.xpath("//h2[@class='complete-header']")
		).getText();
		
		Assert.assertEquals(actualCompleteMessage, expectedCompleteMessage);
		System.out.println("Order Completion Validation Passed");

		// Close browser
		driver.quit();
	}

}
