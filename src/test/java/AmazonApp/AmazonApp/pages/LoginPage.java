package AmazonApp.AmazonApp.pages;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


import java.io.File;

public class LoginPage {

	WebDriver driver;
	ExtentTest test;

	public LoginPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}

	public void Page(String un, String pwd) throws InterruptedException {
  
		try {
			
		
		// Open Google
		driver.get("https://www.google.com");
		Thread.sleep(1000);
		test.log(Status.PASS, "Launch google");

		// opening Amazon
		driver.get("https://www.amazon.com");
		Thread.sleep(1000);
		test.log(Status.PASS, "opening amazon website");
		driver.get(
				"https://www.amazon.in/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2F%3Fref_%3Dnav_ya_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&");
		Thread.sleep(2000);
		test.log(Status.PASS, "Open Amazon website");

		// login to account
		driver.findElement(By.id("ap_email")).sendKeys(un);
		driver.findElement(By.id("continue")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("ap_password")).sendKeys(pwd);
		driver.findElement(By.id("signInSubmit")).click();
		Thread.sleep(1000);
		test.log(Status.PASS, "login to account");
		driver.get(
				"https://www.amazon.in/gp/yourstore/home?path=%2Fgp%2Fyourstore%2Fhome&signIn=1&useRedirectOnSuccess=1&action=sign-out&ref_=nav_AccountFlyout_signout&");

		// searching for product
		WebElement searchboxmob = driver.findElement(By.id("twotabsearchtextbox"));
		searchboxmob.sendKeys("iphone x");
		searchboxmob.submit();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Apple iPhone XR (128GB) - Coral")).click();
		switchToWindow();
		Thread.sleep(2000);
		test.log(Status.PASS, "Searching first product");

		WebElement dropdown = driver.findElement(By.xpath("//select[@name='quantity']"));
		Select selectObject = new Select(dropdown);
		// selectObject.selectByIndex(1);
		selectObject.selectByValue("2");
		Thread.sleep(4000);
		test.log(Status.PASS, "search product");

		// add to cart
		WebElement addcart = driver
				.findElement(By.xpath("//span[@id='submit.add-to-cart']//input[@value='Add to Cart']"));
		addcart.click();
		// ((JavascriptExecutor)driver).executeScript("arguments[0].click()",addcart);
		Thread.sleep(3000);
		driver.findElement(By.id("attach-close_sideSheet-link")).click();
		Thread.sleep(3000);
		test.log(Status.PASS, "search product");
		// opening cart
		driver.get("https://www.amazon.in/gp/cart/view.html?ref_=nav_cart");
		Thread.sleep(3000);
		test.log(Status.PASS, "add to cart");
		test.log(Status.PASS, "Product added to cart");

		// purchasing Laptop
		WebElement searchboxlaptop = driver.findElement(By.id("twotabsearchtextbox"));
		searchboxlaptop.sendKeys("Laptop");
		searchboxlaptop.submit();
		Thread.sleep(3000);
		driver.findElement(By.linkText(
				"Hp 14S 11Th Gen Intel Core I3- 8Gb Ram/256Gb Ssd 14 Inches Fhd,Micro-Edge,Anti-Glare,IPS Display/Uhd Graphics/ Windows 11 Home/ Ms Office/ Alexa Built-in/ 1.46Kg/ Natural Silver - 14S-Dy2506Tu"))
				.click();
		switchToWindow();
		Thread.sleep(3000);
		test.log(Status.PASS, "Search for second product");

		// add to cart

		WebElement addcartlaptop = driver
				.findElement(By.xpath("//span[@id='submit.add-to-cart']//input[@value='Add to Cart']"));
		addcartlaptop.click();
		Thread.sleep(3000);
		// ((JavascriptExecutor)driver).executeScript("arguments[0].click()",addcartlaptop);
		driver.findElement(By.id("attach-close_sideSheet-link")).click();
		Thread.sleep(3000);
		driver.get("https://www.amazon.in/gp/cart/view.html?ref_=nav_cart");
		Thread.sleep(3000);
		test.log(Status.PASS, "added to cart");
		takeScreenshot("cart_item_screenshot");
		// Delete product
		WebElement deletecart = driver.findElement(By.xpath("//*[@value='Delete']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", deletecart);
		Thread.sleep(3000);
		
		test.log(Status.PASS, "deleting one product");
	}
		catch(Exception e){
			test.log(Status.FAIL, "Test Cases failed");
			try {
				takeScreenshot("Screenshot");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void takeScreenshot(String fileName) throws IOException
	{
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("C:\\AmazonProject\\AmazonApp\\test-output\\Screenshot\\"+fileName+".jpg"));
		
		
	}

	public boolean switchToWindow() {
		String pw = driver.getWindowHandle();
		String cw = null;
		Set<String> avaiblewindows = driver.getWindowHandles();
		Iterator<String> handle = avaiblewindows.iterator();
		while (handle.hasNext()) {
			cw = handle.next();
		}
		driver.switchTo().window(cw);
		return true;
	}
}
