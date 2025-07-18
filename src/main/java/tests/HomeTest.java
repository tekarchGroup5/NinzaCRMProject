package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.MyProfilePage;

public class HomeTest extends BaseTest {
	HomePage hp;
	WebDriver driver;
	@BeforeMethod
	public void login() throws FileNotFoundException, IOException {
		WebDriver driver = getBrowser();
		LoginPage lp = new LoginPage(driver);
		hp = lp.loginToApp(driver);
	}

	@Test
	public void verifyMyProfileOption_TC06() throws FileNotFoundException, IOException, InterruptedException {
		hp.clickUserMenu();
		Assert.assertTrue(hp.verifyUserMenuOptions(), "User Menu options should be verified");
	    MyProfilePage profilePage = hp.selectMyProfilePage(driver);
	    profilePage.clickEditProfile(driver);
	    Assert.assertTrue(profilePage.verifyContactIframeAvailability(driver), "");
	    Assert.assertTrue(profilePage.verifyAboutTab(driver), "");
	    Assert.assertTrue(profilePage.verifyLastNameChange(),"");
	    Assert.assertTrue(profilePage.verifyCreatePost(driver, "Hello Team"));
	    Assert.assertTrue(profilePage.verifyFileUpload(driver));
	    profilePage.clickOnAddPhoto(driver);
	    Assert.assertTrue(profilePage.verifyAddPhoto(driver));
	}

}
