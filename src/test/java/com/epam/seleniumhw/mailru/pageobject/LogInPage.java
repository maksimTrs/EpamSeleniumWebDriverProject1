package com.epam.seleniumhw.mailru.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LogInPage extends BasePage {

    private static String logInIFrame = "//iframe[contains(@src, 'mail.ru/login')]";

    @FindBy(xpath = "//div[@data-testid='logged-out-one-click']/button[text()='Войти']")
    private WebElement logInButton;
    @FindBy(css = "input[name='username']")
    private WebElement userNameLogInField;
    @FindBy(xpath = "//button[@data-test-id='next-button']")
    private WebElement userPasswordLogInButton;
    @FindBy(css = "input[name='password']")
    private WebElement userPasswordLogInField;
    @FindBy(xpath = "//button[@data-test-id='submit-button']")
    private WebElement logInSubmitButton;


    public LogInPage(WebDriver driver) {
        super(driver);
    }


    public void doLogIn(String url, String username, String password) {
        driver.get(url);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(logInButton));
        logInButton.click();

        driver.switchTo().frame(driver.findElement(By.xpath(logInIFrame)));

        webDriverWait.until(ExpectedConditions.elementToBeClickable(userNameLogInField));
        userNameLogInField.sendKeys(username);


        webDriverWait.until(ExpectedConditions.elementToBeClickable(userPasswordLogInButton));
        userPasswordLogInButton.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(userPasswordLogInField));
        userPasswordLogInField.sendKeys(password);

        logInSubmitButton.click();

        driver.switchTo().defaultContent();
    }

    public boolean checkLogOut() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(logInButton));

        return (logInButton.isDisplayed() || logInButton.isEnabled());
    }
}
