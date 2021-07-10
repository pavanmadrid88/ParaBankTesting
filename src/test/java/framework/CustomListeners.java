package framework;

import com.aventstack.extentreports.*;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class CustomListeners extends BaseClass implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult iTestResult){
        extentTest.pass(iTestResult.getMethod().getXmlTest().getName() + "-" + iTestResult.getMethod().getMethodName() + " PASSED!");
    }
    @Override
    public void onTestFailure(ITestResult iTestResult){
        try {
            failed(iTestResult.getMethod().getMethodName());
            extentTest.fail(iTestResult.getMethod().getXmlTest().getName() +" FAILED! " + iTestResult.getThrowable(),MediaEntityBuilder.createScreenCaptureFromBase64String(failed(iTestResult.getMethod().getMethodName())).build());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
