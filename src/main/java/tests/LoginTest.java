package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import listeners.ListenersSFDC;
import pages.HomePage;
import pages.LoginPage;
import utils.CommonUtils;
import utils.FileUtils;

@Listeners(ListenersSFDC.class)
public class LoginTest extends BaseTest {

	@Test()
	public void loginErrorMessageTC01() throws InterruptedException, FileNotFoundException, IOException {
		WebDriver driver = getBrowser();
		logger.info("Browser instance launched");
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		lp = new LoginPage(driver);
		LoginPage lp = new LoginPage(driver);
		driver.navigate().to(FileUtils.readLoginPropertiesFile("prod.url"));
		test.get().info("App launched");
		String expectedUsername = FileUtils.readLoginPropertiesFile("valid.username");
		lp.enterUsername(expectedUsername);
		test.get().info("User name entered");
		String actualUsername = lp.getValueAttribute(lp.userName);
		Assert.assertEquals(expectedUsername, actualUsername, "The actual and expected usernames should be same");
		lp.password.clear();
		String actualPassword = lp.getValueAttribute(lp.password);
		Assert.assertEquals("", actualPassword, "The actual and expected passwords should be same");
		lp.clickLogin();
		test.get().info("Login button clicked");
		CommonUtils.captureScreenshot(driver);
		Assert.assertEquals(lp.getErrorMessage(), FileUtils.readLoginPropertiesFile("error.text"),
				"Error message should be same");
		logger.info("loginErrorMessageTC01: Finished");
	}

	@Test()
	public void loginToSalesforceTC02() throws InterruptedException, FileNotFoundException, IOException {
		WebDriver driver = getBrowser();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		lp = new LoginPage(driver);
		LoginPage lp = new LoginPage(driver);
		SoftAssert sa = new SoftAssert();
		driver.navigate().to(FileUtils.readLoginPropertiesFile("prod.url"));
		String expectedUsername = FileUtils.readLoginPropertiesFile("valid.username");
		lp.enterUsername(expectedUsername);
		String actualUsername = lp.getValueAttribute(lp.userName);
		sa.assertEquals(expectedUsername, actualUsername, "The actual and expected usernames should be same");
		lp.enterPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		lp.clickLogin();
		sa.assertEquals(driver.getTitle(), FileUtils.readLoginPropertiesFile("homepage.title"));
		System.out.println("Reached last line");
		sa.assertAll();
		throw new ElementClickInterceptedException("");
	}

//	@Test()
	public void loginToSalesforce() throws InterruptedException, FileNotFoundException, IOException {
		WebDriver driver = getBrowser();
		LoginPage lp = new LoginPage(driver);
		driver.navigate().to(FileUtils.readLoginPropertiesFile("prod.url"));
		String expectedUsername = FileUtils.readLoginPropertiesFile("valid.username");
		HomePage hPage = lp.loginToApp(driver, expectedUsername, FileUtils.readLoginPropertiesFile("valid.password"));
		Assert.assertEquals(driver.getTitle(), FileUtils.readLoginPropertiesFile("homepage.title"));
		Assert.assertTrue(hPage.isHomePage(), "User should be in home page");
	}

//	@Test(dataProvider = "ValidAccounts", dataProviderClass = CommonUtils.class)
	public void loginToSalesforceAccounts(String username, String pass)
			throws InterruptedException, FileNotFoundException, IOException {
		WebDriver driver = getBrowser();
		LoginPage lp = new LoginPage(driver);
		driver.navigate().to(FileUtils.readLoginPropertiesFile("prod.url"));
		HomePage hPage = lp.loginToApp(driver, username, pass);
//		Assert.assertEquals(driver.getTitle(), FileUtils.readLoginPropertiesFile("homepage.title"));
//		Assert.assertTrue(hPage.isHomePage(), "User should be in home page");
	}

		
	@DataProvider(name = "InvalidAccounts")
	public Object loginTestDataInValid() {
//		To read those user accounts logic
		return new Object[][] { {"mithun@tek.com", "12345" }, { "deek@tek.com", "12345" },
				{ "dean@tek.com", "12345" } };
	}
	
	@DataProvider(name = "AccountNames")
	public String[] accounts() {
//		To read those user accounts logic
		return new String[] {"", ""};
	}
	

}
