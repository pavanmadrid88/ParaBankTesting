package tests;

import framework.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.AccountActivityPage;
import pages.AccountsOverviewPage;
import pages.BillPayPage;
import pages.CommonPage;

import java.util.Collections;
import java.util.HashMap;

public class BillPayTest extends BaseClass {

    @Parameters({"username","password","fromAccountId","balance","payeeName","payeeAddress","payeeCity","payeeState","payeeZipCode","payeePhone","payeeAmount"})
    @Test
    public void do200$BillPay(String username, String password, String fromAccountId, String balance
            , String payeeName, String payeeAddress, String payeeCity, String payeeState, String payeeZipCode, String payeePhone,
                              String payeeAmount) {


        //create savings account
        OpenNewAccountPageTest openNewAccountPageTest = new OpenNewAccountPageTest();
        String newSavingsAccountId = openNewAccountPageTest.openNewAccount(username, password, "SAVINGS", fromAccountId, balance);

        //create checking account
        String newCheckingAccountId = openNewAccountPageTest.openNewAccount(username, password, "CHECKING", fromAccountId, balance);

        //click on accountOverviewPage
        CommonPage commonPage = new CommonPage();
        AccountsOverviewPage accountsOverviewPage = commonPage.clickAccountsOverviewLink();
        AccountActivityPage accountActivityPage = accountsOverviewPage.clickAccountIdLink(newCheckingAccountId);
        String previousFromAccountBalance = accountActivityPage.getBalance();
        String previousFromAccountAvailableBalance = accountActivityPage.getAvailableBalance();

        commonPage.clickAccountsOverviewLink();
        accountActivityPage = accountsOverviewPage.clickAccountIdLink(newSavingsAccountId);
        String previousToAccountBalance = accountActivityPage.getBalance();
        String previousToAccountAvailableBalance = accountActivityPage.getAvailableBalance();


        //click on bill pay
        BillPayPage billPayPage = commonPage.clickBillPayLink();

        //fill payee info
        billPayPage.fillPayeeInfo(payeeName, payeeAddress, payeeCity, payeeState, payeeZipCode, payeePhone,
                newSavingsAccountId, newSavingsAccountId, payeeAmount, newCheckingAccountId);

        //validate payment complete
        Assert.assertTrue(billPayPage.isBillPaymentCompleteTitleDisplayed());
        Assert.assertEquals(billPayPage.getBillCompleteFromAccountId(), newCheckingAccountId);
        Assert.assertEquals(billPayPage.getBillCompletePayeeAmount(), payeeAmount);
        Assert.assertEquals(billPayPage.getBillCompletePayeeName(), payeeName);

        //click on accountOverviewPage
        accountsOverviewPage = commonPage.clickAccountsOverviewLink();
        //click on fromAccountId
        accountActivityPage = accountsOverviewPage.clickAccountIdLink(newCheckingAccountId);
        Assert.assertTrue(accountActivityPage.checkForTransaction("Bill Payment to" + payeeName));
        String newFromAccountBalance = accountActivityPage.getBalance();
        String newFromAccountAvailableBalance = accountActivityPage.getAvailableBalance();
        Assert.assertEquals(newFromAccountBalance, Float.parseFloat(String.valueOf(previousFromAccountBalance)) - Float.parseFloat(String.valueOf(payeeAmount)));


    }
}
