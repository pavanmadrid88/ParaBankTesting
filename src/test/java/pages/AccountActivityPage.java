package pages;

import framework.BaseTest;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.OpenNewAccountPageTest;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class AccountActivityPage extends BaseTest {

    By accountDetailsTitle = By.xpath("//h1[text()='Account Details']");
    By accountIdElement = By.id("accountId");
    By accountTypeElement = By.id("accountType");
    By balanceElement = By.xpath("//td[@id='balance']");
    By availableBalanceElement = By.id("availableBalance");
    By accountActivityTitle = By.xpath("//h1[text()='Account Activity']");
    By transactionTable = By.id("transactionTable");
    By transactionTableRows = By.xpath("//table[@id='transactionTable']//tr");
    Logger logger = LoggerFactory.getLogger(AccountActivityPage.class);

    public Boolean isAccountDetailsSectionDisplayed() {
        return isElementDisplayed(accountDetailsTitle, 10);
    }

    public Boolean isAccountIdDisplayed() {
        return isElementDisplayed(accountIdElement, 10);
    }

    public Boolean isTransactionTableDisplayed() {
        return isElementDisplayed(transactionTable, 10);
    }

    public String getAccountNumber() {
        return getText(accountIdElement);

    }

    public String getAccountType() {
        return getText(accountTypeElement);
    }

    public Double getBalance() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Double balance = Double.parseDouble(getText(balanceElement).replace("$", ""));
        return balance;
    }

    public Double getAvailableBalance() {
        Double availableBalance = Double.parseDouble(getText(availableBalanceElement).replace("$", ""));
        return availableBalance;
    }

    private int getTableRowCount() {
        logger.info("Transaction Table Rows:" + driver.findElements(transactionTableRows).size());
        return driver.findElements(transactionTableRows).size();
    }

    private List<String> getAllTransactions() {
        List<String> transactionTexts = new ArrayList<>();
        int rows = getTableRowCount();

        for (int i = 1; i < rows; i++) {
            String transactionText = getText(By.xpath("(//table[@id='transactionTable']//td//a[contains(@href,'transaction.htm')])[" + i + "]"));
            transactionTexts.add(transactionText);
        }
        return transactionTexts;
    }

    public Boolean checkForTransaction(String transactionToLook) {
        logger.info("Checking for transaction:" + transactionToLook);
        List<String> listOfTransactions = getAllTransactions();
        for (int i = 0; i < listOfTransactions.size(); i++) {
            String transaction = listOfTransactions.get(i);
            if (transaction.trim().equals(transactionToLook.toString().trim())) {
                return true;
            }
        }
        return false;
    }


}
