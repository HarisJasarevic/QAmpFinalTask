package com.QAmp.HarisJasarevic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage extends BasePage {

    private final static By LOGIN_HEADER = By.cssSelector("div#login > p.headline");
    private final static By LOGIN_FORM = By.id("login_form");
    private final static String EXPECTED_PAGE_TITLE = "PlaceLab";
    private final static  By EMAIL_INPUT = By.id("email");
    private final static  By PASSWORD_INPUT = By.id("password");
    private final static  By LOGIN_SUBMIT_BTN = By.xpath("//input[@type='submit']");

    private final WebDriver driver;
    private final BasePage basePage;

    public LoginPage (final WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
        this.basePage = new BasePage(driver);
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
    }

    public void validateLoginPageContent () {
        final String actualPageTitle = basePage.getPageTitle();
        final boolean isHeaderDisplayed = driver.findElement(LOGIN_HEADER).isDisplayed();
        final boolean isLoginFormDisplayed = driver.findElement(LOGIN_FORM).isDisplayed();
        Assert.assertTrue(isLoginFormDisplayed, "Validate is login form displayed");
        Assert.assertTrue(isHeaderDisplayed, "Validate is header text displayed");
        Assert.assertEquals(actualPageTitle, EXPECTED_PAGE_TITLE, "Validate page title is correct");
    }

    public void enterCredentials (final String email, final String password) {
        driver.findElement(EMAIL_INPUT).sendKeys(email);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    public void clickSubmitLoginButton () {
        final boolean isLogInButtonDisplayed = driver.findElement(LOGIN_SUBMIT_BTN).isDisplayed();
        Assert.assertTrue(isLogInButtonDisplayed, "Validate Log In button is displayed");
        waitUntilClickable(LOGIN_SUBMIT_BTN).click();
    }
}
