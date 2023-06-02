package com.QAmp.HarisJasarevic.tests;

import com.QAmp.HarisJasarevic.pages.LoginPage;
import com.QAmp.HarisJasarevic.pages.NavigationPage;
import com.QAmp.HarisJasarevic.pages.QueriesPage;
import com.QAmp.HarisJasarevic.pages.RoadAnalysisPage;
import com.QAmp.HarisJasarevic.utils.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RoadAnalysisTest extends BaseTest {

    private LoginPage loginPage;
    private NavigationPage navigationPage;
    private RoadAnalysisPage roadAnalysisPage;
    private QueriesPage queriesPage;

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setup(@Optional("chrome") final String browser) {
        this.loginPage = new LoginPage(driver);
        this.navigationPage = new NavigationPage(driver);
        this.roadAnalysisPage = new RoadAnalysisPage(driver);
        this.queriesPage = new QueriesPage(driver);
        driver.manage().window().maximize();
    }

    @Parameters( { "email", "password" } )
    @Test(priority = 2, description = "SMOKE TEST, Login with valid credentials, create and delete Road Analysis report")
    public void roadAnalysisReport (final String email, final String password) {

        final String expectedUserRole = "Group Admin";

        //Validate PlaceLab login page is open and running
        loginPage.validateLoginPageContent();

        //Populate PlaceLab login form with valid credentials and log in
        loginPage.enterCredentials(email, password);
        loginPage.clickSubmitLoginButton();

        //Verify user is logged in
        navigationPage.validateUserIsLoggedIn(expectedUserRole);

        //Validate create menu is displayed in navigation bar and enter Road Analysis page
        navigationPage.navigationCreateReportMenu();

        //Validate Road Analysis page content
        roadAnalysisPage.validateRoadAnalysisPageContent();

        //Populate Road Analysis form and create a report
        roadAnalysisPage.populateRoadAnalysisReportForm();

        //Validate queries page content
        queriesPage.validateQueriesPageIsOpen();

        //Delete report
        queriesPage.deleteReport();

        //Sign out
        navigationPage.userSignOut();
    }
}
