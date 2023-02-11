package com.epam.seleniumhw.mailru.pageobject.pageobjecthelper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JscriptExecutorHelper {

    public static void clickOnSpecifiedElement(JavascriptExecutor jscriptExecutor, WebElement webElement) {
        jscriptExecutor
                .executeScript("arguments[0].click();", webElement);
    }

    public static String getSpecifiedElementAttributeText(JavascriptExecutor jscriptExecutor,
                                                          String webElementAttribute, WebElement webElement) {
        return (String) jscriptExecutor
                .executeScript((String.format("return arguments[0].getAttribute('%s');", webElementAttribute)), webElement);
    }

    public static String getSpecifiedElementText(JavascriptExecutor jscriptExecutor, WebElement webElement) {
        //  return  (String) jscriptExecutor.executeScript(("return arguments[0].value;"), webElement);
        return (String) jscriptExecutor
                .executeScript(("return arguments[0].innerHTML;"), webElement);
    }


    public static void addTextToEmailMessageField(JavascriptExecutor jscriptExecutor, String text) {
        jscriptExecutor.
                executeScript(String.format("document.querySelector(\"div[role='textbox'] > div:first-of-type\").innerHTML='%s'", text));
    }
}
