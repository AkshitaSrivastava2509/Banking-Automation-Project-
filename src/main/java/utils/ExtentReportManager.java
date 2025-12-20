package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
	
	private static  ExtentReports extent;
	
	public static ExtentReports getExtentReport() {
		
		if(extent==null) {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String reportPath = System.getProperty("user.dir")+"/report/ExtentReport_"+timestamp+".html";
			
			ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
			spark.config().setReportName("Banking Automation Test Report");
			spark.config().setDocumentTitle("Automation Execution Report");
			
			extent = new ExtentReports();
			extent.attachReporter(spark);
			
			extent.setSystemInfo("Project", "Banking Automation");
            extent.setSystemInfo("Tester", "Akshita");
		}
		return extent;
	}

}
