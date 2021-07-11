package tests;

import framework.BaseClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;

import java.text.NumberFormat;


public class BillPayTest extends BaseClass {
    Logger logger = LoggerFactory.getLogger(BillPayTest.class);

    @Parameters({"username","password","fromAccountId","balance","payeeName","payeeAddress","payeeCity","payeeState","payeeZipCode","payeePhone","payeeAmount"})
    @Test(enabled = true)
    public void do200$BillPay(String username, String password, String fromAccountId, String balance
            , String payeeName, String payeeAddress, String payeeCity, String payeeState, String payeeZipCode, String payeePhone,
                              String payeeAmount) {
        try {

            //login
            WelcomeLoginPage welcomeLoginPage = new WelcomeLoginPage();
            welcomeLoginPage.login(username, password);

            //accounts overview page
            AccountsOverviewPage accountsOverviewPage = new AccountsOverviewPage();
            Assert.assertTrue(accountsOverviewPage.isAccountsOverviewPageDisplayed());

            //Click on Open New Account Link
            CommonPage commonPage = new CommonPage();
            commonPage.clickOpenNewAccountLink();

            //Open new account
            OpenNewAccountPage openNewAccountPage = new OpenNewAccountPage();
            openNewAccountPage.openNewAccount("SAVINGS", fromAccountId);
            String savingsAccountNumber = openNewAccountPage.getNewAccountNumber();

            //Open new Checking Account
            commonPage.clickOpenNewAccountLink();
            openNewAccountPage.openNewAccount("CHECKING", fromAccountId);
            String checkingAccountNumber = openNewAccountPage.getNewAccountNumber();

            //click on accounts overview and land on accounts overview page of checking account
            commonPage.clickAccountsOverviewLink();
            accountsOverviewPage.clickAccountIdLink(checkingAccountNumber);

            //capture the previous balance of checking account
            AccountActivityPage accountActivityPage = new AccountActivityPage();
            Assert.assertTrue(accountActivityPage.isAccountDetailsSectionDisplayed());
            Double previousFromAccountBalance = accountActivityPage.getBalance();
            Double previousFromAccountAvailableBalance = accountActivityPage.getAvailableBalance();

            //capture the previous balance of savings account
            commonPage.clickAccountsOverviewLink();
            accountActivityPage = accountsOverviewPage.clickAccountIdLink(savingsAccountNumber);

            Double previousToAccountBalance = accountActivityPage.getBalance();
            Double previousToAccountAvailableBalance = accountActivityPage.getAvailableBalance();


            //click on bill pay
            BillPayPage billPayPage = commonPage.clickBillPayLink();

            //fill payee info
            billPayPage.fillPayeeInfo(payeeName, payeeAddress, payeeCity, payeeState, payeeZipCode, payeePhone,
                    savingsAccountNumber, savingsAccountNumber, payeeAmount, checkingAccountNumber);

            //validate payment complete
            Assert.assertTrue(billPayPage.isBillPaymentCompleteTitleDisplayed());
            Assert.assertEquals(billPayPage.getBillCompleteFromAccountId(), checkingAccountNumber);
            Assert.assertEquals(billPayPage.getBillCompletePayeeAmount(), Double.valueOf(payeeAmount));
            Assert.assertEquals(billPayPage.getBillCompletePayeeName(), payeeName);

            //click on accountOverviewPage
            accountsOverviewPage = commonPage.clickAccountsOverviewLink();
            //click on fromAccountId
            accountActivityPage = accountsOverviewPage.clickAccountIdLink(checkingAccountNumber);
            Assert.assertTrue(accountActivityPage.checkForTransaction("Bill Payment to " + payeeName));
            Double newFromAccountBalance = accountActivityPage.getBalance();
            Double newFromAccountAvailableBalance = accountActivityPage.getAvailableBalance();
            logger.info("previousFromAccountBalance:" + previousFromAccountBalance);
            logger.info("payeeAmount:" + payeeAmount);
            Assert.assertEquals(newFromAccountBalance, previousFromAccountBalance - Double.parseDouble(payeeAmount), 0.0);

            logger.info("newFromAccountBalance:" + newFromAccountBalance);
            logger.info("newFromAccountAvailableBalance:" + newFromAccountAvailableBalance);
            logger.info("previousFromAccountBalance:" + previousFromAccountBalance);
            logger.info("payeeAmount:" + payeeAmount);

            //click on accountOverviewPage
            accountsOverviewPage = commonPage.clickAccountsOverviewLink();

            accountsOverviewPage.clickAccountIdLink(savingsAccountNumber);
            Assert.assertTrue(accountActivityPage.checkForTransaction("Funds Transfer Received"));


        }catch (Exception e){
            logger.error("Exception in Bill Pay Test:" + e.getMessage());
            Assert.fail("Exception in Bill Pay Test:" + e.getMessage());
        }



    }
}
