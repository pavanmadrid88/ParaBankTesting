package pages;

import framework.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import tests.OpenNewAccountPageTest;

public class CommonPage extends BaseTest {

    By openNewAccountLink = By.linkText("Open New Account");
    By logoutLink = By.linkText("Log Out");
    By welcomeUsernameLabel = By.xpath("//b[text()='Welcome']");
    By billPayLink = By.linkText("Bill Pay");
    By accountsOverviewLink = By.linkText("Accounts Overview");

    public WelcomeLoginPage doLogout(){
        clickElement(logoutLink);
        return new WelcomeLoginPage();
    }

    public BillPayPage clickBillPayLink(){
        clickElement(billPayLink);
        return new BillPayPage();

    }

    public AccountsOverviewPage clickAccountsOverviewLink(){
        clickElement(accountsOverviewLink);
        return new AccountsOverviewPage();
    }

    public OpenNewAccountPage clickOpenNewAccountLink(){
        clickElement(openNewAccountLink);
        return new OpenNewAccountPage();

    }

    public Boolean isUserLoggedIn(){
        boolean state = false;
        try {
            if (isElementDisplayed(welcomeUsernameLabel)) {
                state = true;
            }
        }catch (Exception e){
            state = false;
        }
        return state;
    }




}
