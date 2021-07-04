package pages;

import framework.BaseTest;
import org.openqa.selenium.By;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class AccountActivityPage extends BaseTest {

    By accountDetailsTitle = By.xpath("//h1[text()='Account Details']");
    By accountIdElement = By.id("accountId");
    By accountTypeElement = By.id("accountType");
    By balanceElement = By.id("balance");
    By availableBalanceElement = By.id("availableBalance");
    By accountActivityTitle = By.xpath("//h1[text()='Account Activity']");
    By transactionTable = By.id("transactionTable");
    By transactionTableRows = By.xpath("//table[@id='transactionTable']//tr");

    public Boolean isAccountDetailsSectionDisplayed() {
        return isElementDisplayed(accountDetailsTitle);
    }

    public Boolean isTransactionTableDisplayed() {
        return isElementDisplayed(transactionTable);
    }

    public String getAccountNumber() {
        return getText(accountIdElement);

    }

    public String getAccountType() {
        return getText(accountTypeElement);

    }

    public String getBalance() {
        return getText(balanceElement);

    }

    public String getAvailableBalance() {
        return getText(availableBalanceElement);
    }

    private int getTableRowCount(){
        return driver.findElements(transactionTableRows).size();
    }

    private List<String> getAllTransactions(){
        List<String> transactionTexts = new ArrayList<>();
        int rows = getTableRowCount();
        for(int i=0;i<rows;i++){
            String transactionText = getText(By.xpath("(//table[@id='transactionTable']//td//a[contains(@href,'transaction.htm')])[" + i+1 +"]" ));
            transactionTexts.add(transactionText);
        }

        return transactionTexts;


    }
    public Boolean checkForTransaction(String transactionToLook){
        List<String> listOfTransactions = getAllTransactions();
        for(int i=0;i<listOfTransactions.size();i++){
            String transaction = listOfTransactions.get(i);
            if(transaction.equals(transactionToLook)){
                return true;
            }
        }
        return false;
    }


}
