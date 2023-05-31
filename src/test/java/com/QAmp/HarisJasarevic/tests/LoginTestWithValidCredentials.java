package com.QAmp.HarisJasarevic.tests;

import com.QAmp.HarisJasarevic.pages.LoginPage;
import com.QAmp.HarisJasarevic.pages.NavigationPage;
import com.QAmp.HarisJasarevic.utils.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTestWithValidCredentials extends BaseTest {

    private LoginPage loginPage;
    private NavigationPage navigationPage;


    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void initPages() {
        this.loginPage = new LoginPage(driver);
        this.navigationPage = new NavigationPage(driver);
        driver.manage().window().maximize();
    }

    @Parameters( { "email", "password" } )
    @Test(priority = 1, description = "Validate user is able to login with valid credentials")
    public void testPositiveLogin (final String email, final String password) {

        final String expectedUserRole = "Group Admin";

        //Validate login page is open
        loginPage.validateLoginPageContent();

        //Populate login form
        loginPage.enterCredentials(email, password);
        loginPage.clickSubmitLoginButton();

        //Verify user is logged in
        navigationPage.validateUserIsLoggedIn(expectedUserRole);

        //User Sign Out
        navigationPage.userSignOut();
    }
}
