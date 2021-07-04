package framework;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import com.aventstack.extentreports.*;
import pages.CommonPage;
import pages.WelcomeLoginPage;


public class BaseTest {

    public static WebDriver driver;
    public static Properties properties;
    public static ExtentReports reports;
    public static ExtentTest extentTest;
    public static ExtentSparkReporter spark;



    public BaseTest() {
        try {
            InputStream fileInputStream = BaseTest.class.getResourceAsStream("/config.properties");
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void init(){
        String browser = properties.getProperty("browser");
        switch (browser.trim().toUpperCase()) {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "EDGE":
                WebDriverManager.edgedriver();
                driver = new EdgeDriver();
                break;

            default:
                Assert.fail("Invalid Browser name specified");
        }

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(properties.getProperty("url"));



    }

    public void enterText(By textFieldLocator, String text){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(textFieldLocator));
        driver.findElement(textFieldLocator).clear();
        driver.findElement(textFieldLocator).sendKeys(text);
    }

    public void clickElement(By elementToBeClickedLocator){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(elementToBeClickedLocator)).click();
    }

    public Boolean isElementDisplayed(By elementLocator){
        WebDriverWait wait = new WebDriverWait(driver,10);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator)).isDisplayed();
    }

    public void selectDropdownItem(By selectElement,String item){
        Select select = new Select(driver.findElement(selectElement));
        select.selectByVisibleText(item);
    }

    public String getPageTitle(){
        return driver.getTitle().toString();
    }

    public String getText(By elementLocator){
        WebDriverWait wait = new WebDriverWait(driver,20);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator)).getText();
    }

    public String failed(String methodName) throws IOException{
        String sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
        return sourceFile;
    }

    public static String getCurrentTimestamp(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Date now = new Date();
        return simpleDateFormat.format(now);
    }



    public static void teardown(){
        CommonPage commonPage = new CommonPage();


        if(commonPage.isUserLoggedIn()){
           WelcomeLoginPage welcomeLoginPage= commonPage.doLogout();
           Assert.assertTrue(welcomeLoginPage.getPageTitle().contains("Welcome"));
        }
        driver.close();
        driver.quit();
    }


}
