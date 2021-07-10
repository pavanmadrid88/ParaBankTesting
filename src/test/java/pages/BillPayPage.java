package pages;

import framework.BaseTest;
import org.openqa.selenium.By;

public class BillPayPage extends BaseTest {


    By payeeNameField = By.name("payee.name");
    By payeeAddressField = By.name("payee.address.street");
    By payeeCityField = By.name("payee.address.city");
    By payeeStateField = By.name("payee.address.state");
    By payeeZipcodeField = By.name("payee.address.zipCode");
    By payeePhoneField = By.name("payee.phoneNumber");
    By payeeAccountNumberField = By.name("payee.accountNumber");
    By payeeVerifyAccountNumberField = By.name("verifyAccount");
    By payeeAmountField = By.name("amount");
    By payeeFromAccountSelect = By.name("fromAccountId");
    By sendPaymentButton = By.xpath("//input[@value= 'Send Payment']");
    By billPaymentCompleteTitle = By.xpath("//h1[text()='Bill Payment Complete']");
    By payeeNameBillCompleteField = By.xpath("//span[@id='payeeName']");
    By payeeAmountBillCompleteField = By.xpath("//span[@id='amount']");
    By payeeFromAccountIdBillCompleteField = By.xpath("//span[@id='fromAccountId']");

    public void fillPayeeInfo(String payeeName,String payeeAddress,String payeeCity,String payeeState,String payeeZipCode,String payeePhone,String payeeAccountNumber,String payeeVerifyAccountNumber,String payeeAmount,String payeeFromAccountId){
        enterText(payeeNameField,payeeName);
        enterText(payeeAddressField,payeeAddress);
        enterText(payeeCityField,payeeCity);
        enterText(payeeStateField,payeeState);
        enterText(payeeZipcodeField,payeeZipCode);
        enterText(payeePhoneField,payeePhone);
        enterText(payeeAccountNumberField,payeeAccountNumber);
        enterText(payeeVerifyAccountNumberField,payeeVerifyAccountNumber);
        enterText(payeeAmountField,payeeAmount);
        selectDropdownItem(payeeFromAccountSelect,payeeFromAccountId);
        clickElement(sendPaymentButton);
    }

    public Boolean isBillPaymentCompleteTitleDisplayed(){
        return isElementDisplayed(billPaymentCompleteTitle,10);
    }

    public String getBillCompletePayeeName(){
        return getText(payeeNameBillCompleteField);
    }

    public Double getBillCompletePayeeAmount(){
       String billPayeeAmount = getText(payeeAmountBillCompleteField);
       billPayeeAmount = billPayeeAmount.replace("$","");
       return Double.valueOf(billPayeeAmount);
    }


    public String getBillCompleteFromAccountId(){
        return getText(payeeFromAccountIdBillCompleteField);
    }






}
