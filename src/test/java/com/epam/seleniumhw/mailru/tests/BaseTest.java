package com.epam.seleniumhw.mailru.tests;


import com.epam.seleniumhw.mailru.decorator.BasicLoginComponent;
import com.epam.seleniumhw.mailru.decorator.BasicLoginDecorator;
import com.epam.seleniumhw.mailru.decorator.LoginComponent;
import com.epam.seleniumhw.mailru.model.User;
import com.epam.seleniumhw.mailru.pageobject.LogInPage;
import com.epam.seleniumhw.mailru.pageobject.MainPage;
import com.epam.seleniumhw.mailru.service.UserCreator;
import com.epam.seleniumhw.mailru.utils.DriverFactory;
import com.epam.seleniumhw.mailru.utils.TestListener;
import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.epam.seleniumhw.mailru.utils.DriverFactory.BrowserRunType.LOCAL;


@Listeners({TestListener.class})  // Change the Browser type:  LOCAL <-> SELENIUM_GRID
public abstract class BaseTest {

    public static Logger logger = Logger.getLogger(BaseTest.class);
    public User testUser;
    protected LogInPage logInPage;
    protected MainPage mainPage;
    protected WebDriver driver;


    @BeforeSuite
    private static void executePreConditions() {

        Path path = FileSystems.getDefault().getPath("//target/allure-results");
        try {
            Files.deleteIfExists(path);
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", path);
        } catch (IOException ix) {
            ix.printStackTrace();
        }
    }

    @BeforeClass
    @Parameters({"urlAddress"})
    public void setUp(String urlAddress) {

        driver = DriverFactory.getDriver(LOCAL);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        logger.info("|||+++++||| AT Test was started for Browser: <"
                + ((RemoteWebDriver) driver).getCapabilities().getBrowserName().toUpperCase()
                + "> and Browser version: " + ((RemoteWebDriver) driver).getCapabilities().getVersion()
                + " |||+++++|||");

        logInPage = new LogInPage(driver);
        mainPage = new MainPage(driver);
        testUser = UserCreator.withCredentialsFromProperty();

        // logInPage.doLogIn(urlAddress, testUser);
        LoginComponent loginComponent = new BasicLoginComponent();
        if (Boolean.getBoolean("DECORATOR")) {
            loginComponent.login(logInPage, urlAddress, testUser);
        } else {
            loginComponent = new BasicLoginDecorator(loginComponent);
            loginComponent.login(logInPage, urlAddress, testUser);
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        logInPage = null;
        mainPage = null;
        DriverFactory.closeDriver();
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
