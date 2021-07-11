package tests;

import framework.BaseClass;
import framework.CustomListeners;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import org.testng.annotations.Test;


@Listeners(CustomListeners.class)
public class OpenNewAccountPageTest extends BaseClass {

    WelcomeLoginPage welcomeLoginPage;
    AccountsOverviewPage accountsOverviewPage;
    WelcomeLoginPageTest welcomeLoginPageTest;
    OpenNewAccountPage openNewAccountPage;

    CommonPage commonPage;
    Logger logger = LoggerFactory.getLogger(OpenNewAccountPageTest.class);


    @Parameters({"username", "password", "accountType", "fromAccountId", "balance"})
    @Test()
    public void openNewAccount(String username, String password, String accountType, String fromAccountId, String balance) {
        try {
            welcomeLoginPageTest = new WelcomeLoginPageTest();
            commonPage = new CommonPage();

            //login
            WelcomeLoginPage welcomeLoginPage = new WelcomeLoginPage();
            welcomeLoginPage.login(username, password);

            //land on openNewAccountPage
            openNewAccountPage = commonPage.clickOpenNewAccountLink();
            Assert.assertTrue(openNewAccountPage.isOpenNewAccountPageDisplayed());

            //open newAccount
            openNewAccountPage.openNewAccount(accountType, fromAccountId);
            Assert.assertTrue(openNewAccountPage.isAccountOpenedTitleDisplayed() && openNewAccountPage.isNewAccountOpenedMessageDisplayed());

            //getNewAccountId
            String newAccountId = openNewAccountPage.getNewAccountNumber().toString();
            logger.info("New AccountID Created of type:" + accountType + ":" + newAccountId);

            //click on newly created AccountId
            AccountActivityPage accountActivityPage = openNewAccountPage.clickNewAccountIdLink();

            //validate details in accountActivity page
            Assert.assertTrue(accountActivityPage.isAccountDetailsSectionDisplayed());
            Assert.assertTrue(accountActivityPage.isAccountIdDisplayed());
            Assert.assertTrue(accountActivityPage.isTransactionTableDisplayed());
            Assert.assertEquals(accountType, accountActivityPage.getAccountType());
            Assert.assertEquals(accountActivityPage.getAvailableBalance(), Double.parseDouble(balance.replace("$", "")), 0.0);
            Assert.assertEquals(accountActivityPage.getBalance(), Double.parseDouble(balance.replace("$", "")), 0.0);
        }catch (Exception e){
            logger.error("Exception in OpenNewAccountTest:" + e.getMessage());
            Assert.fail("Exception in OpenNewAccountTest:" + e.getMessage());
        }

    }
}
