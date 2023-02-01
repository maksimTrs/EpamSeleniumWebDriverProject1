package com.epam.seleniumhw.mailru.tests;


import com.epam.seleniumhw.mailru.pageobject.LogInPage;
import com.epam.seleniumhw.mailru.pageobject.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.epam.seleniumhw.mailru.utils.SecretPasswordHandler.handlingPassword;


public abstract class BaseTest {

    protected static Logger logger = Logger.getLogger(BaseTest.class);
    public LogInPage logInPage;
    public MainPage mainPage;
    protected WebDriver driver;

    @BeforeClass
    @Parameters({"urlAddress", "emailName", "emailPassword"})
    public void setUp(String urlAddress, String emailName, String emailPassword) {

        String browserType = "CHROME";

        if (System.getProperty("BROWSER") != null &&
                System.getProperty("BROWSER").equalsIgnoreCase("FIREFOX")) {
            browserType = "FIREFOX";
        }

        driver = WebDriverManager.getInstance(DriverManagerType.valueOf(browserType.toUpperCase())).create();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        logger.info("+++++ AT Test was started for browser = " + browserType + " +++++");

        logInPage = new LogInPage(driver);
        mainPage = new MainPage(driver);

        logInPage.doLogIn(urlAddress, emailName, handlingPassword(emailPassword));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        logInPage = null;
        mainPage = null;
        driver.quit();
    }

    @BeforeMethod
    public void logTestStart(Method method, Object[] parameters) {
        logger.info("********************************************************************************");
        logger.info("<<< Test method: " + method.getName() + " with parameters " + List.of(parameters) + " was started >>>");
        logger.info("********************************************************************************");

    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method method) {
        saveScreenshotPNG();

        logger.info("********************************************************************************");
        logger.info("<<< Test method: " + method.getName() + " was finished >>>");
        logger.info("********************************************************************************");

    }


    @Attachment(value = "Page Screenshot", type = "image/png")
    public byte[] saveScreenshotPNG() {
        logger.info("Attaching screenshot to Allure report");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }


}
