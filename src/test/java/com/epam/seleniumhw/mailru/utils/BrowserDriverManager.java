package com.epam.seleniumhw.mailru.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;

public class BrowserDriverManager {

    public WebDriver createInstance(String browser, BrowserType browserType, String host) {

        if (System.getProperty("HUB_HOST") != null && (!System.getProperty("HUB_HOST").isEmpty())) {
            host = System.getProperty("HUB_HOST");
        }
        if (System.getProperty("BROWSER") != null &&
                System.getProperty("BROWSER").equalsIgnoreCase("EDGE")) {
            browser = System.getProperty("BROWSER");
        }

        String path = "http://" + host + ":4444/wd/hub";
        WebDriver driver = null;
        DriverManagerType driverManagerType = DriverManagerType.valueOf(browser.toUpperCase());

        if (browserType.equals(BrowserType.SELENIUM_GRID)) {
            driver = WebDriverManager.getInstance(driverManagerType).remoteAddress(path).create();

        } else if (browserType.equals(BrowserType.LOCAL)) {
            driver = WebDriverManager.getInstance(driverManagerType).create();
        }

        driver.manage().window().maximize();
        return driver;
    }

    public enum BrowserType {
        LOCAL, SELENIUM_GRID
    }
}
