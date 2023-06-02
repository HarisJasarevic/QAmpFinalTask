package com.QAmp.HarisJasarevic.pages;

import com.QAmp.HarisJasarevic.utils.GlobalValues;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

public class QueriesPage extends BasePage {

    private WebDriver driver;
    private static WebDriverWait wait;
    private RoadAnalysisPage roadAnalysisPage;
    private final static String EXPECTED_REPORTS_PAGE_TITLE = "PlaceLab - demo";
    private final static By LANDED_REPORTS_PAGE = By.id("queries-nav-item");
    private final static By REPORT_PAGE_HEADER = By.linkText("Reports");
    private final static By REPORT_PAGE_TABLE = By.xpath("//div[@class='main-content']");
    private final static By DELETE_ICON = By.cssSelector("#action-delete > a > i");
    private final static By CONFIRM_DELETE_REPORT = By.xpath("//*[@id='confirm-link']");
    private final static By REPORT_CHECKBOXES = By.cssSelector("#table_queries > tbody > tr > td.large > div");
    private final static By DELETE_CONFIRMATION_MESSAGE = By.xpath("//div[@class='alert alert-success']");

    public QueriesPage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.roadAnalysisPage = new RoadAnalysisPage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    public void validateQueriesPageIsOpen () {
        wait.until(ExpectedConditions.urlToBe(GlobalValues.QUERIES_URL));
        final String actualReportsPageTitle = getPageTitle();
        final boolean isReportPageLanded = driver.findElement(LANDED_REPORTS_PAGE).isDisplayed();
        final boolean isReportPageHeaderDisplayed = driver.findElement(REPORT_PAGE_HEADER).isDisplayed();
        final boolean isReportsPageTableDisplayed = driver.findElement(REPORT_PAGE_TABLE).isDisplayed();
        Assert.assertEquals(actualReportsPageTitle, EXPECTED_REPORTS_PAGE_TITLE, "Validate page title is correct");
        Assert.assertTrue(isReportPageLanded, "Validate that we landed on reports page");
        Assert.assertTrue(isReportPageHeaderDisplayed, "Validate reports page header is displayed");
        Assert.assertTrue(isReportsPageTableDisplayed, "Validate reports page table is displayed");
    }

    public void deleteReport( ) {
        getReportCheckbox(ReportIDHolder.getReportID()).click();
        waitUntilClickable(DELETE_ICON).click();
        waitUntilClickable(CONFIRM_DELETE_REPORT).click();
        final boolean isDeleteConfirmationMessageDisplayed = driver.findElement(DELETE_CONFIRMATION_MESSAGE).isDisplayed();
        Assert.assertTrue(isDeleteConfirmationMessageDisplayed, "Validate delete report confirmation message is displayed");
    }

    private WebElement getReportCheckbox (final String reportID) {
        final List<WebElement> checkboxes = driver.findElements(REPORT_CHECKBOXES);
        for (final WebElement checkbox : checkboxes) {
            if (checkbox.findElement(By.tagName("input")).getAttribute("value").equals(reportID)) {
                return checkbox;
            }
        }
        throw new NotFoundException();
    }
}
