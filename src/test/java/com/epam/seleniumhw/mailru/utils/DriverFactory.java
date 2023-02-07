package com.epam.seleniumhw.mailru.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.config.WebDriverManagerException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.WebDriver;

public class DriverFactory {

    public WebDriver createInstance(BrowserType browserType, String host) {


        if (System.getProperty("HUB_HOST") != null && (!System.getProperty("HUB_HOST").isEmpty())) {
            host = System.getProperty("HUB_HOST");
        }
        String path = "http://" + host + ":4444/wd/hub";

        DriverManagerType driverManagerType;
        if (System.getProperty("BROWSER") != null &&
                System.getProperty("BROWSER").equalsIgnoreCase("FIREFOX")) {
            driverManagerType = DriverManagerType.valueOf(BrowserFactory.FIREFOX.getBrowserType());
        } else {
            driverManagerType = DriverManagerType.valueOf(BrowserFactory.CHROME.getBrowserType());
        }

        WebDriver driver;
        switch (browserType) {
            case LOCAL -> driver = WebDriverManager.getInstance(driverManagerType).create();
            case SELENIUM_GRID -> driver = WebDriverManager.getInstance(driverManagerType).remoteAddress(path).create();
            default -> throw new WebDriverManagerException("Invalid driver manager type");
        }
        driver.manage().window().maximize();

        return driver;
    }

    public enum BrowserType {
        LOCAL, SELENIUM_GRID
    }

    @Getter
    @AllArgsConstructor
    public enum BrowserFactory {
        CHROME("CHROME"),
        FIREFOX("FIREFOX");

        private final String browserType;
    }
}
