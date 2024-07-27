package com.utils;
import java.util.Base64;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportListener extends Baseclass implements ITestListener  {

    private static final String REPORT_FOLDER = System.getProperty("user.dir")+"//extent-reports//";
    private ExtentReports extent;
    private ExtentSparkReporter sparkreporter;
    private ExtentTest test ;
    private String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());


   

    @Override
    public void onTestStart(ITestResult result) {
    	
//    	sparkreporter = new ExtentSparkReporter(getReportPath(REPORT_FOLDER));
//    	extent = new ExtentReports();
//    	extent.attachReporter(sparkreporter);
//        test = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
            }

    @Override
    public void onTestSuccess(ITestResult result) {
    	test = extent.createTest(result.getName());
        test.log(Status.PASS, "<font color=\"green\">Test passed</font>");
        test.pass(result.getTestName());

        addScreenshotToReport(result);

        
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	test = extent.createTest(result.getName());
        test.log(Status.FAIL, "<font color=\"red\">Test failed</font>");
        test.fail(result.getThrowable());
        addScreenshotToReport(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    	test = extent.createTest(result.getName());
        test.log(Status.SKIP, "Test skipped");
        test.skip(result.getThrowable());
    }

    @Override
    public void onStart(ITestContext context) {
    	sparkreporter = new ExtentSparkReporter(getReportPath(REPORT_FOLDER));
    	extent = new ExtentReports();
    	extent.attachReporter(sparkreporter);
    	sparkreporter.config().setDocumentTitle("login  Automation");
    	sparkreporter.config().setReportName("login  Automation");
    	sparkreporter.config().setTheme(Theme.DARK);

    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
    
    private void addScreenshotToReport(ITestResult result) {
       // WebDriver driver = ((WebDriver)result.getTestContext().getAttribute("driver"));
        if (driver instanceof TakesScreenshot) {
            //String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
            //String screenshotPath = REPORT_FOLDER + ".//screenshots//" + timestamp + ".png";
            try {
                TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
                byte[] screenshot = screenshotDriver.getScreenshotAs(OutputType.BYTES);
                test.addScreenCaptureFromBase64String(Base64.getEncoder().encodeToString(screenshot), "Screenshot"+timestamp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


   

        private  String getReportPath(String fileName) {
            return fileName + "_" + timestamp + ".html";
        }
    
}

