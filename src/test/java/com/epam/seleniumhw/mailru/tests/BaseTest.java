package com.epam.seleniumhw.mailru.tests;


import com.epam.seleniumhw.mailru.pageobject.MailRuLogInPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;

import java.lang.reflect.Method;
import java.util.List;

import static com.epam.seleniumhw.mailru.utils.SecretPasswordHandler.handlingPassword;


public class BaseTest {

    protected Logger logger = Logger.getLogger(BaseTest.class);
    protected WebDriver driver;


    @BeforeClass
    @Parameters({"urlAddress", "emailName", "emailPassword"})
    public void setUp(String urlAddress, String emailName, String emailPassword) {


        String browserType = "CHROME"; // FIREFOX  CHROME

        if (System.getProperty("BROWSER") != null &&
                System.getProperty("BROWSER").equalsIgnoreCase("FIREFOX")) {
            browserType = "FIREFOX";
        }

        driver = WebDriverManager.getInstance(DriverManagerType.valueOf(browserType.toUpperCase())).create();
        driver.manage().window().maximize();
        logger.info("+++++ AT Test was started for browser = " + browserType + " +++++");

        MailRuLogInPage mailRuLogInPage = new MailRuLogInPage(driver);
        mailRuLogInPage.doLogIn(urlAddress, emailName, handlingPassword(emailPassword));

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @BeforeMethod
    public void logTestStart(Method method, Object[] parameters) {
        logger.info("********************************************************************************");
        logger.info("<<< Dummy method: " + method.getName() + " with parameters " + List.of(parameters) + " was started >>>");
        logger.info("********************************************************************************");

    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method method) {
        saveScreenshotPNG();

        logger.info("********************************************************************************");
        logger.info("<<< Dummy method: " + method.getName() + " was finished >>>");
        logger.info("********************************************************************************");

    }


    @Attachment(value = "Page Screenshot", type = "image/png")
    public byte[] saveScreenshotPNG() {
        System.out.println("Attaching screenshot to Allure report");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }


}