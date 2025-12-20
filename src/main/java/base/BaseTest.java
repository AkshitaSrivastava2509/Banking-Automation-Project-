package base;

import java.time.Duration;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;

public class BaseTest {

	protected WebDriver driver;
	protected Properties prop;
	protected ConfigReader reader;
	protected final Logger log = LogManager.getLogger("BankLogger");
	

	@BeforeMethod
	public void initDriver(ITestContext context) {
		reader = new ConfigReader();

		prop = reader.initProp();
		String browserName = prop.getProperty("browser");
		String url = prop.getProperty("url");

		switch (browserName.toLowerCase()) {

		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		default:
			throw new RuntimeException("Invalid browser name: " + browserName);
		}
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get(url);
		context.setAttribute("driver", driver);	

	}

	@AfterMethod
	public void quitDriver() {
		if (driver != null) {
			driver.quit();
		}

	}

}
