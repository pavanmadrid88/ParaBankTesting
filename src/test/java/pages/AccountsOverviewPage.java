package pages;

import framework.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountsOverviewPage extends BaseTest {

    By accountsOverviewTitle = By.xpath("//h1[text()='Accounts Overview']");

    public Boolean isAccountsOverviewPageDisplayed(){
        return isElementDisplayed(accountsOverviewTitle);
    }

    public AccountActivityPage clickAccountIdLink(String accountId){
        clickElement(By.xpath("//a[@href='activity.htm?id='" + accountId + "]"));
        return new AccountActivityPage();
    }

}
