package pages;

import framework.BaseTest;
import org.openqa.selenium.By;

public class ErrorPage extends BaseTest {

    By usernamePasswordErrorMessage = By.xpath("//p[text()='The username and password could not be verified.']");

    public Boolean isUsernamePasswordErrorDisplayed(){
       return isElementDisplayed(usernamePasswordErrorMessage);
    }


}
