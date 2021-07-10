package pages;

import framework.BaseTest;
import org.openqa.selenium.By;

public class WelcomeLoginPage extends BaseTest {

    By usernameField = By.name("username");
    By passwordField = By.name("password");
    By loginButton = By.xpath("//input[@value='Log In' and @class='button']");

    public AccountsOverviewPage login(String userName, String password){
        enterText(usernameField,userName);
        enterText(passwordField,password);
        clickElement(loginButton);
        return new AccountsOverviewPage();
    }
}
