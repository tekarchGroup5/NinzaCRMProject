package seleniumautomationframeworkjul24;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ActionUtils;
import utils.WaitUtils;

public class LaunchApp {

	public static void main(String[] args) throws InterruptedException, AWTException {

		ChromeOptions co = new ChromeOptions();
		WebDriver driver = new ChromeDriver(co);
//		driver.get("https://login.salesforce.com/");
		driver.navigate().to("https://selenium-prd.firebaseapp.com/");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WaitUtils.explicitlyWaitForVisibility(driver, driver.findElement(By.id("email_field")));
		WebElement userName = driver.findElement(By.id("email_field"));
		js.executeScript("arguments[0].value='admin123@gmail.com';", userName);
		WebElement password = driver.findElement(By.id("password_field"));
		password.sendKeys("admin123");
		password.sendKeys(Keys.ENTER);
		
		WebElement loginButton = driver.findElement(By.xpath("//button[text()='Login to Account']"));
		js.executeScript("arguments[0].click();", loginButton);
		loginButton.click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Home")).click();
		Thread.sleep(2000);
		WebElement name = driver.findElement(By.id("name"));
		js.executeScript("arguments[0].setAttribute('id','fullname');", name);
		Thread.sleep(200000);
		js.executeScript("window.scrollBy(0,1000);");
		String url = js.executeScript("return document.domain;").toString();
		System.out.println(url);
		Thread.sleep(1000);
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		driver.close();
	}

}
