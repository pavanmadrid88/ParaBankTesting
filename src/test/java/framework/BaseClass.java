package framework;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import pages.AccountsOverviewPage;
import pages.WelcomeLoginPage;


public class BaseClass extends BaseTest{

    @BeforeSuite
    public void beforeClass(){

        reports = new ExtentReports();
        spark = new ExtentSparkReporter("reports/ExtentSparkReport_" + getCurrentTimestamp() + ".html");

        //reports = new ExtentReports("reports/ExtentReport_" + getCurrentTimestamp() + ".html");
        extentTest = reports.createTest("ParaBank Tests");


    }

    @BeforeMethod
    public void beforeMethod(){
        init();
    }


    @AfterMethod
    public void afterMethod(){
        teardown();
    }

    @AfterSuite
    public void afterClass(){
        reports.attachReporter(spark);
        reports.flush();
    }
}
