package com.QAmp.HarisJasarevic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class NavigationPage extends BasePage {

    private static WebDriverWait wait;
    private final WebDriver driver;
    private final static By USER_ROLE = By.id("user-role");
    private final static By TRAFFIC_NAVBAR_BTN = By.id("traffic-dashboard-nav-item");
    private final static By REPORTS_NAVBAR_BTN = By.id("queries-nav-item");
    private final static By DROPDOWN_BTN = By.cssSelector("#user-name > i");
    private final static By SIGN_OUT_BTN = By.linkText("Sign out");
    private final static By CREATE_MENU_DROPDOWN = By.id("create-menu");
    private final static By ROAD_ANALYSIS_BTN = By.id("roadanalysis");

    public NavigationPage(final WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void validateUserIsLoggedIn (final String expectedUserRole) {
        final String actualAdminUserRole = driver.findElement(USER_ROLE).getText();
        Assert.assertEquals(actualAdminUserRole, expectedUserRole, "Validate user role for the logged in user");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(TRAFFIC_NAVBAR_BTN)));
        final boolean isTrafficNavbarBtnDisplayed = driver.findElement(TRAFFIC_NAVBAR_BTN).isDisplayed();
        Assert.assertTrue(isTrafficNavbarBtnDisplayed, "Validate is traffic dashboard button displayed");
        final boolean isReportsNavbarButtonDisplayed = driver.findElement(REPORTS_NAVBAR_BTN).isDisplayed();
        Assert.assertTrue(isReportsNavbarButtonDisplayed, "Validate is reports button displayed");
    }

    public void userSignOut () {
        Assert.assertTrue(driver.findElement(DROPDOWN_BTN).isDisplayed(), "Validate dropdown button is displayed");
        driver.findElement(DROPDOWN_BTN).click();
        Assert.assertTrue(driver.findElement(SIGN_OUT_BTN).isDisplayed(), "Validate sign out button is displayed");
        driver.findElement(SIGN_OUT_BTN).click();
    }

    public void navigationCreateReportMenu () {
        Assert.assertTrue(driver.findElement(CREATE_MENU_DROPDOWN).isDisplayed(), "Validate create menu dropdown is displayed");
        waitUntilClickable(CREATE_MENU_DROPDOWN).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(ROAD_ANALYSIS_BTN)));
        Assert.assertTrue(driver.findElement(ROAD_ANALYSIS_BTN).isDisplayed(), "Validate single place search button is displayed");
        waitUntilClickable(ROAD_ANALYSIS_BTN).click();
    }
}
