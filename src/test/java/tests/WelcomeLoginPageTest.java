package tests;

import framework.BaseClass;
import framework.CustomListeners;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.AccountsOverviewPage;
import pages.ErrorPage;
import pages.WelcomeLoginPage;



@Listeners(CustomListeners.class)
public class WelcomeLoginPageTest extends BaseClass {

    WelcomeLoginPage welcomeLoginPage;
    AccountsOverviewPage accountsOverviewPage;
    Logger logger = LoggerFactory.getLogger(WelcomeLoginPageTest.class);



    @Parameters({"username","password"})
    @Test(enabled = true)
    public AccountsOverviewPage validLoginTest(String username,String password){
        try {
            logger.info("Starting Login Test");
            welcomeLoginPage = new WelcomeLoginPage();
            accountsOverviewPage = welcomeLoginPage.login(username, password);
            Assert.assertTrue(accountsOverviewPage.isAccountsOverviewPageDisplayed());

        }catch (Exception e){
            logger.info("Exception in valid login test");
            Assert.fail(e.getMessage());
        }finally {
            logger.info("Ending Login Test");
        }

        return accountsOverviewPage;
    }

    @Parameters({"invalidUsername","invalidPassword"})
    @Test(enabled = true)
    public void invalidUsernameAndPasswordLoginTest(String invalidUsername,String invalidPassword){
        try {
            welcomeLoginPage = new WelcomeLoginPage();
            welcomeLoginPage.login(invalidUsername, invalidPassword);
            Assert.assertTrue(new ErrorPage().isUsernamePasswordErrorDisplayed());
        }catch (Exception e){
            Assert.fail(e.getMessage());
        }finally {
            logger.info("Ending invalidUsernameAndPasswordLoginTest");
        }
    }


    @Parameters({"invalidUsername","password"})
    @Test(enabled = true)
    public void invalidUsernameAndValidPasswordLoginTest(String invalidUsername,String invalidPassword){
        try {
            welcomeLoginPage = new WelcomeLoginPage();
            welcomeLoginPage.login(invalidUsername, invalidPassword);
            Assert.assertTrue(new ErrorPage().isUsernamePasswordErrorDisplayed());
        }catch (Exception e){
            Assert.fail(e.getMessage());
        }finally {
            logger.info("Ending invalidUsernameAndValidPasswordLoginTest");
        }
    }

    @Parameters({"username","invalidPassword"})
    @Test(enabled = true)
    public void validUsernameAndInvalidPasswordLoginTest(String invalidUsername,String invalidPassword){
        try {
            welcomeLoginPage = new WelcomeLoginPage();
            welcomeLoginPage.login(invalidUsername, invalidPassword);
            Assert.assertTrue(new ErrorPage().isUsernamePasswordErrorDisplayed());
        }catch (Exception e){
            Assert.fail(e.getMessage());
        }
        finally {
            logger.info("Ending validUsernameAndInvalidPasswordLoginTest");
        }
    }
}
