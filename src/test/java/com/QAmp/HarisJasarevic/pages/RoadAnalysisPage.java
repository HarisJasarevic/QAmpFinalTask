package com.QAmp.HarisJasarevic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoadAnalysisPage extends BasePage {

    private static WebDriverWait wait;
    private WebDriver driver;
    private final Random random = new Random();
    private String reportID;
    private final static String EXPECTED_PAGE_TITLE = "PlaceLab - demo";
    private final static By ROAD_ANALYSIS_HEADER = By.xpath("//div[@class='create-report-header']");
    private final static By ROAD_ANALYSIS_REPORT_FORM = By.id("road_dialog");
    private final static By REPORT_NAME = By.id("upload_query_name");
    private final static By FILE_DROPZONE = By.id("road-browse-btn");
    private final static By CREATE_REPORT_BTN = By.xpath("//button[@class='btn large-btn run-btn run-all-btn']");

    public RoadAnalysisPage (final WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public void validateRoadAnalysisPageContent() {
        final String actualPageTitle = getPageTitle();
        final boolean isRoadAnalysisHeaderDisplayed = driver.findElement(ROAD_ANALYSIS_HEADER).isDisplayed();
        final boolean isRoadAnalysisReportFormDisplayed = driver.findElement(ROAD_ANALYSIS_REPORT_FORM).isDisplayed();
        Assert.assertEquals(actualPageTitle, EXPECTED_PAGE_TITLE, "Validate is page title correct");
        Assert.assertTrue(isRoadAnalysisHeaderDisplayed, "Validate road analysis header is displayed");
        Assert.assertTrue(isRoadAnalysisReportFormDisplayed, "Validate road analysis report form is displayed");
    }

    public void populateRoadAnalysisReportForm () {
        driver.findElement(REPORT_NAME).sendKeys("Road Analysis Report " + random.nextInt(9999));
//        String filePath = "C:\\Users\\haris\\OneDrive\\Desktop\\Workspace\\QAmpFinalTask\\src\\test\\resources\\roads_sample.txt";
        String filePath = "C:\\Users\\haris\\OneDrive\\Desktop\\Workspace\\QAmpFinalTask\\src\\test\\resources\\roads_sample_NewYork.txt";
//        String filePath = "C:\\Users\\haris\\OneDrive\\Desktop\\Workspace\\QAmpFinalTask\\src\\test\\resources\\roads_sample_Sarajevo.txt";
        driver.findElement(FILE_DROPZONE).sendKeys(filePath);
        waitUntilClickable(CREATE_REPORT_BTN).click();

        String urlPattern = "https://demo.placelab.com/progress/(\\d+)";
        Pattern pattern = Pattern.compile(urlPattern);

        while (true) {
            String currentUrl = driver.getCurrentUrl();
            Matcher matcher = pattern.matcher(currentUrl);

            if (matcher.find()) {
                reportID = matcher.group(1);
                ReportIDHolder.setReportID(reportID);

                System.out.println("REPORT ID IS: " + reportID);
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
