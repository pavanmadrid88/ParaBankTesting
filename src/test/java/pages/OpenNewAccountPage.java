package pages;

import framework.BaseTest;
import org.openqa.selenium.By;

public class OpenNewAccountPage extends BaseTest {

    By accountTypeSelect = By.id("type");
    By fromAccountSelect = By.id("fromAccountId");
    By openNewAccountButton = By.xpath("//input[@value='Open New Account' and @class='button']");
    By accountOpenedTitle = By.xpath("//h1[text()='Account Opened!']");
    By accountIsNowOpenSuccessMessage = By.xpath("//p[text()='Congratulations, your account is now open.']");
    By newAccountId = By.xpath("//a[@id='newAccountId' and contains(@href,'activity')]");
    By openNewAccountTitle = By.xpath("//h1[text() = 'Open New Account']");


    public void openNewAccount(String accountType, String fromAccountId) {
        selectDropdownItem(accountTypeSelect, accountType);
        selectDropdownItem(fromAccountSelect, fromAccountId);
        clickElement(openNewAccountButton);
    }

    public Boolean isNewAccountOpenedMessageDisplayed() {
        return isElementDisplayed(accountIsNowOpenSuccessMessage,10);
    }

    public Boolean isAccountOpenedTitleDisplayed() {
        return isElementDisplayed(accountOpenedTitle,10);
    }

    public String getNewAccountNumber() {
        return getText(newAccountId);
    }

    public Boolean isOpenNewAccountPageDisplayed() {
        return isElementDisplayed(openNewAccountTitle,10);
    }

    public AccountActivityPage clickNewAccountIdLink() {
        clickElement(newAccountId);
        return new AccountActivityPage();
    }

}
