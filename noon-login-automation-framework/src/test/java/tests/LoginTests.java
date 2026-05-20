package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    //VALID LOGIN TESTS

    @DataProvider(name = "validLoginData")
    public Object[][] validLoginData() {
        return new Object[][]{
                {"testuser@gmail.com", "correctpassword"},
                {"TeSTuser@gmail.com", "correctpassword"}
        };
    }

    @Test(dataProvider = "validLoginData")
    public void validLoginTest(String email, String password) {

        LoginPage login = new LoginPage(driver);

        login.openSignIn();
        login.enterEmail(email);
        login.clickContinue();
        login.choosePasswordLogin();
        login.enterPassword(password);
        login.clickLogin();

        Assert.assertTrue(login.isUserLoggedIn());
    }



    // ❌ INVALID LOGIN TESTS


    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][]{
                {"testuser@gmail.com", "wrongpassword"},
                {"testuser@gmail.com", "        "},
                {"        ", "correctpassword"},
                {"test  user@gmail.com", "correctpassword"}
        };
    }

    @Test(dataProvider = "invalidLoginData")
    public void invalidLoginTest(String email, String password) {

        LoginPage login = new LoginPage(driver);

        login.openSignIn();
        login.enterEmail(email);
        login.clickContinue();
        login.choosePasswordLogin();
        login.enterPassword(password);
        login.clickLogin();

        Assert.assertFalse(login.isUserLoggedIn());
    }


    //EMAIL FORMAT TESTS


    @Test
    public void emailWithSpacesTest() {

        LoginPage login = new LoginPage(driver);

        login.openSignIn();
        login.enterEmail("test  user@gmail.com");
        login.clickContinue();

        Assert.assertTrue(login.isEmailErrorDisplayed());
    }
}


//package tests;
//
//import base.BaseTest;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import pages.LoginPage;
//import org.testng.annotations.DataProvider;
//
//public class LoginTests extends BaseTest {
//
//    @DataProvider(name = "invalidLoginData")
//    public Object[][] invalidLoginData() {
//        return new Object[][]{
//                {"testuser@gmail.com", "wrongpassword"},
//                {"testuser@gmail.com", "        "},
//                {"        ", "correctpassword"},
//                {"test  user@gmail.com", "correctpassword"}
//        };
//    }
//
//    @Test
//    public void validLoginTest() {
//
//        LoginPage login = new LoginPage(driver);
//
//        login.openSignIn();
//        login.enterEmail("testuser@gmail.com");
//        login.clickContinue();
//        login.choosePasswordLogin();
//        login.enterPassword("correctpassword");
//        login.clickLogin();
//
//        // NOTE: replace this with real validation later
//        Assert.assertTrue(login.isUserLoggedIn());
//    }
//
//    @Test
//    public void emailCaseTest()
//    {
//        LoginPage login = new LoginPage(driver);
//
//        login.openSignIn();
//        login.enterEmail("TeSTuser@gmail.com");
//        login.clickContinue();
//        login.choosePasswordLogin();
//        login.enterPassword("correctpassword");
//        login.clickLogin();
//
//        // NOTE: replace this with real validation later
//        Assert.assertTrue(login.isUserLoggedIn());
//    }
//
//
//    @Test
//    public void invalidPasswordTest() {
//
//        LoginPage login = new LoginPage(driver);
//
//        login.openSignIn();
//        login.enterEmail("testuser@gmail.com");
//        login.clickContinue();
//        login.choosePasswordLogin();
//        login.enterPassword("wrongpassword");
//        login.clickLogin();
//
//        // NOTE: replace this with real validation later
//        Assert.assertFalse(login.isUserLoggedIn());
//    }
//
//    @Test
//    public void emailWithSpacesTest() {
//        LoginPage login = new LoginPage(driver);
//
//        login.openSignIn();
//        login.enterEmail("test  user@gmail.com");
//        login.clickContinue();
//        // NOTE: replace this with real validation later
//        Assert.assertTrue(login.isEmailErrorDisplayed());
//    }
//
//    @Test
//    public void emptyPasswordTest()
//    {
//        LoginPage login = new LoginPage(driver);
//
//        login.openSignIn();
//        login.enterEmail("testuser@gmail.com");
//        login.clickContinue();
//        login.choosePasswordLogin();
//        login.enterPassword("        ");
//        login.clickLogin();
//
//        // NOTE: replace this with real validation later
//        Assert.assertTrue(login.isEmptyPasswordMessageDisplayed());
//    }
//    @Test
//    public void emptyEmailTest()
//    {
//        LoginPage login = new LoginPage(driver);
//
//        login.openSignIn();
//        login.enterEmail("        ");
//        login.clickContinue();
//        // NOTE: replace this with real validation later
//        Assert.assertTrue(login.isEmailErrorDisplayed());
//    }
//}