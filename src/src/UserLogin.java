package src;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UserLogin {
	
	WebDriver driver;
	
	@BeforeTest
	public void LoadBrowser() {
		
		driver= new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1376,768));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.navigate().to("https://staging.luxdraws.com/");
	}
	
	@Test
	public void ClickSignIn() throws InterruptedException {
				
		//LuxDraws_Properties.SignIn.click();
		driver.findElement(By.linkText("Sign In")).click();
		
		Set<String>handles = driver.getWindowHandles();//To handle multiple windows
		String firstWinHandle = driver.getWindowHandle();
		handles.remove(firstWinHandle);
		String winHandle=handles.iterator().next();
		if (winHandle!=firstWinHandle){
		     String secondWinHandle=winHandle;
		driver.switchTo().window(secondWinHandle);   //Switch to popup window
		Thread.sleep(2000);
		}
		}
	@Test
	public void EnterCredentials() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("venkata.t@uandme.org");
		Thread.sleep(1000);
		driver.findElement(By.id("login_password")).sendKeys("Test@123");
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='recaptcha-anchor']/div/div/div/div/div")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("login_btn")).click();
	}
	@AfterTest
	public void CloseBrowser() {
		driver.quit();
	}

}
