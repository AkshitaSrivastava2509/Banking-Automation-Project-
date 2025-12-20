package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import utils.DataProviderUtils;
import utils.GenericUtils;

public class LoginPageTest extends BaseTest {

	private GenericUtils utils;
	private LoginPage loginPage;

	@BeforeMethod(alwaysRun = true)
	public void setUpPages(){
		loginPage= new LoginPage(driver);
		utils = new GenericUtils(driver);
	}
	 
	@Test(description = "Verify valid login with correct credentials")
	public void validLoginTest() {
		
		log.info("Starting Valid Login Test");
		HomePage homePage = loginPage.login(prop.getProperty("userId"), prop.getProperty("password"));
		log.info("Login successful, checking HomePage title");
		String actualTitle = homePage.getTitle();
		String loginText = homePage.getManagerIdText();
		log.info("HomePage title : "+ actualTitle);
		log.info("Success Login Text:- " + loginText);
		Assert.assertTrue(actualTitle.contains("HomePage"));
		Assert.assertTrue(loginText.contains(prop.getProperty("userId")));
		log.info("Valid Login Test Passed");
	}

	@Test(dataProvider = "invalidLoginData",
			dataProviderClass = DataProviderUtils.class,
			description = "Verify negative login functionality")
	public void invalidLoginTest(String user, String password, String errorMessage) {
		log.info("Starting Invalid Login Test");
		loginPage.login(user, password);
		if(utils.waitForAlert() != null) {
		String alertText = utils.getAlertText();
		log.info(alertText);
		Assert.assertEquals(alertText,errorMessage);
		utils.acceptAlert();
		log.info("Invalid Login Test Passed");
		} else {
			log.warn("Alert not displayed for invalid login scenario");
	        Assert.fail("Expected alert was not displayed for invalid login");
		}
	}
}
