package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.ExtentReportManager;
import utils.ScreenshotUtil;

public class ExtentListener implements ITestListener{

	private static final ExtentReports extent = ExtentReportManager.getExtentReport();
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	
	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extendTest = extent.createTest(result.getMethod().getMethodName());
		test.set(extendTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().pass("Test Passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.get().fail(result.getThrowable());
		WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
		
		String filePath = ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());
		test.get().addScreenCaptureFromPath(filePath);
		
	}


	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
