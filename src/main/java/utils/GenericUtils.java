package utils;

import base.BaseTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GenericUtils{

	public WebDriverWait wait;
	public WebDriver driver;

	public GenericUtils(WebDriver driver){
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));;
	}
	public Alert waitForAlert() {

		try{
		return  wait.until(ExpectedConditions.alertIsPresent());
		}catch(TimeoutException e) {
			System.out.println("No alert appear with give duration");
			return null;
		}
	}

	public String getAlertText() {
		 Alert alert = waitForAlert();
		 if(alert!= null) {
			 return alert.getText();
		 }
			 return null;
	}

	public void acceptAlert() {

		Alert alert = waitForAlert();
		if (alert != null) {
			alert.accept();

		}

	}
}
