package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage{
	
	
	private final WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	

	@FindBy (name = "uid")
	WebElement userId;
	
	@FindBy(name ="password")
	WebElement password;
	
	@FindBy(name="btnLogin")
	WebElement login;
	
	
	public HomePage login(String userid, String pwd) {
		userId.sendKeys(userid);
		password.sendKeys(pwd);
		login.click();	
		return new HomePage(driver);
	}
	

}
