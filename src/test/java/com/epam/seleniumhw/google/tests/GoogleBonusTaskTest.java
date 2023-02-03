package com.epam.seleniumhw.google.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


/**
 * NOTE: to start this suite correctly, please change the path DIR in
 * <google-bonus-task.xml> : name="directory" value="{path_dirname}"
 */

public class GoogleBonusTaskTest {

    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = WebDriverManager.firefoxdriver().create();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    @Parameters({"urlAddress", "directory"})
    public void checkBananaSong(String urlAddress, String directory) {


        // Open Google Search
        driver.get(urlAddress);

        // Search for <Banana Song>
        driver.findElement(By.name("q")).sendKeys("Banana Song", Keys.ENTER);

        // Find a YouTube link (<href> contains <youtube.com>) with <Despicable Me> in the text, follow this link
        driver.findElement(By.xpath("//a[contains(@href, 'youtube')][contains(., 'Despicable Me')]")).click();

        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='start']//a[@id='logo']")));
        String pageTitle = driver.getTitle();

        // Assert that we are on YouTube site (page title);
        assertThat(pageTitle).contains("YouTube");

        // Get data that video was watched more than 50 million times (regex will help you)
        WebElement videoCountOfView = driver.findElement(By.xpath("//div[@id='description']//*[@id='info']/span[1]"));
        String videoCountOfViewText = videoCountOfView.getText();

        String pattern = "^\\d+";
        Pattern intNumberOfViewsPattern = Pattern.compile(pattern);
        Matcher matcher = intNumberOfViewsPattern.matcher(videoCountOfViewText);
        int countOfVideoView = 0;
        while (matcher.find()) {
            System.out.println("Found a number: " + matcher.group());
            countOfVideoView = Integer.parseInt(matcher.group());
        }

        // Assert that video was watched more than 50 million times
        assertThat(countOfVideoView)
                .as("video was watched less than 50 million times!")
                .isGreaterThan(50);


        // Save a script as “<YOUR_NAME>_BANANA.html” to your homework directory
        String pageSource = driver.getPageSource();
        try {
            FileUtils.writeStringToFile(new File(directory + "HW" + "_BANANA.html"), pageSource, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }


}
