package com.epam.seleniumhw.mailru.pageobjecthelper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JscriptExecutorHelper {

    public static void clickOnSpecifiedElementHelper(JavascriptExecutor jscriptExecutor, WebElement webElement) {
        jscriptExecutor.executeScript("arguments[0].click();", webElement);
    }

    public static String getSpecifiedElementAttributeText(JavascriptExecutor jscriptExecutor,
                                                          String webElementAttribute, WebElement webElement) {
       return  (String) jscriptExecutor
               .executeScript((String.format("return arguments[0].getAttribute('%s');", webElementAttribute)), webElement);
    }
}
