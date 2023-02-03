package com.epam.seleniumhw.mailru.pageobjecthelper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class ActionHelper {

    public  void moveToElementAndRightClickHelper(WebDriver driver, WebElement webElement) {
        new Actions(driver)
                .moveToElement(webElement)
                .pause(Duration.ofSeconds(1))
                .contextClick(webElement)
                .pause(Duration.ofSeconds(2))
                .perform();
    }
}
