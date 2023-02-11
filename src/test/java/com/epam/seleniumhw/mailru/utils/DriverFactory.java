package com.epam.seleniumhw.mailru.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.config.WebDriverManagerException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.WebDriver;


/**
 * @see <a href="https://testomat.io/blog/singleton-design-pattern-how-to-use-it-in-test-automation">Singleton pattern</a>
 * @see <a href="https://medium.com/javarevisited/factory-pattern-for-test-automation-828b09499dab">Factory Pattern For Test Automation</a>
 */

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static String host = "localhost";

    private DriverFactory() {
    }

    public static WebDriver getDriver(BrowserRunType browserRunType) {
        if (driver.get() == null) {
            if (System.getProperty("HUB_HOST") != null && (!System.getProperty("HUB_HOST").isEmpty())) {
                host = System.getProperty("HUB_HOST");
            }
            String path = "http://" + host + ":4444/wd/hub";

            DriverManagerType driverManagerType;
            if (System.getProperty("BROWSER") != null &&
                    System.getProperty("BROWSER").equalsIgnoreCase("FIREFOX")) {
                driverManagerType = DriverManagerType.valueOf(BrowserTypes.FIREFOX.getBrowserType());
            } else {
                driverManagerType = DriverManagerType.valueOf(BrowserTypes.CHROME.getBrowserType());
            }

            switch (browserRunType) {
                case LOCAL -> driver.set(WebDriverManager.getInstance(driverManagerType).create());
                case SELENIUM_GRID ->
                        driver.set(WebDriverManager.getInstance(driverManagerType).remoteAddress(path).create());
                default -> throw new WebDriverManagerException("<Invalid browserRunType>");
            }
            driver.get().manage().window().maximize();
        }
        return driver.get();
    }

    public static void closeDriver() {
        driver.get().quit();
        driver.remove();
    }

    public enum BrowserRunType {
        LOCAL,
        SELENIUM_GRID
    }

    @Getter
    @AllArgsConstructor
    public enum BrowserTypes {
        CHROME("CHROME"),
        FIREFOX("FIREFOX"),
        EDGE("EDGE");

        private final String browserType;
    }
}
