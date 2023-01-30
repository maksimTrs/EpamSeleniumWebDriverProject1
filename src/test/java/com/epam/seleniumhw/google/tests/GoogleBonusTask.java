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

public class GoogleBonusTask {


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


        driver.get(urlAddress);
        driver.findElement(By.name("q")).sendKeys("Banana Song", Keys.ENTER);
        driver.findElement(By.xpath("//a[contains(@href, 'youtube')][contains(., 'Despicable Me')]")).click();


        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='start']//a[@id='logo']")));
        String pageTitle = driver.getTitle();

        assertThat(pageTitle).contains("YouTube");

        WebElement videoCountOfView = driver.findElement(By.xpath("//div[@id='description']//*[@id='info']/span[1]"));

        String videoCountOfViewText = videoCountOfView.getText();

        String pattern = "^\\d+";

        Pattern intNumberOfViewsPattern =  Pattern.compile(pattern);

        Matcher matcher = intNumberOfViewsPattern.matcher(videoCountOfViewText);

        int countOfVideoView = 0;
        while (matcher.find()) {
            System.out.println("Found a number: " + matcher.group());
            countOfVideoView = Integer.parseInt(matcher.group());
        }

        assertThat(countOfVideoView)
                .as("video was watched less than 50 million times!")
                .isGreaterThan(50);


        String pageSource = driver.getPageSource();


        // Write the page source to a file in the specified directory
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
