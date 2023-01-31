package com.epam.seleniumhw.mailru.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected WebDriverWait webDriverWait;


    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }
}
