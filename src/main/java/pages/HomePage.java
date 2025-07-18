package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.FileUtils;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h2[text()='Community']")
	public WebElement communityPanel;

	@FindBy(id = "userNavButton")
	public WebElement userMenu;

	@FindBy(xpath = "//*[@id='userNav-menuItems']/a")
	public List<WebElement> userMenuOptions;
	
	@FindBy(xpath = "//*[@id='userNav-menuItems']/a[1]")
	public WebElement myProfile;

	public boolean isHomePage() {
		return this.communityPanel.isDisplayed();
	}

	public void clickUserMenu() {
		this.userMenu.click();
	}

	public MyProfilePage selectMyProfilePage(WebDriver driver) {
		this.myProfile.click();
		return new MyProfilePage(driver);
	}

	/**
	 * This function will verify user menu options
	 * @return boolean true if all options are verified
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public boolean verifyUserMenuOptions() throws FileNotFoundException, IOException {
		boolean isUserMenuOptionsVerified = true;
		String[] expectedUserMenuOptions = FileUtils.readHomePropertiesFile("usermenu.options").split(",");
		for (int i = 0; i < expectedUserMenuOptions.length; i++) {
			if (expectedUserMenuOptions[i].equals(userMenuOptions.get(i).getText())) {
				System.out.println(
						"Expected: " + expectedUserMenuOptions[i] + " Actual: " + userMenuOptions.get(i).getText());
			} else {
				isUserMenuOptionsVerified = false;
			}
		}
		return isUserMenuOptionsVerified;
	}
}
