package com.epam.seleniumhw.mailru.pageobject;

import com.epam.seleniumhw.mailru.model.User;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.epam.seleniumhw.mailru.pageobject.pageobjecthelper.JscriptExecutorHelper.colorWebElementBorder;
import static com.epam.seleniumhw.mailru.tests.BaseTest.logger;
import static com.epam.seleniumhw.mailru.utils.SecretPasswordHandler.handlingPassword;
import static org.assertj.core.api.Assertions.assertThat;

public class LogInPage extends BasePage {

    private static String logInIFrame = "//iframe[contains(@src, 'mail.ru/login')]";

    @FindBy(xpath = "//div[@data-testid='logged-out-one-click']/button[text()='Войти']")
    private WebElement logInButton;

    @FindBy(xpath = "//div[@data-testid='whiteline']//button[text()='Войти'] | //div[@data-testid='whiteline']//button[text()='Log in']")
    private WebElement logInButton2;
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


    public void doLogIn(String url, User user) {
        driver.get(url);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(logInButton));
        logInButton.click();

        driver.switchTo().frame(driver.findElement(By.xpath(logInIFrame)));

        webDriverWait.until(ExpectedConditions.elementToBeClickable(userNameLogInField));
        userNameLogInField.sendKeys(user.getUsername());

        webDriverWait.until(ExpectedConditions.elementToBeClickable(userPasswordLogInButton));
        userPasswordLogInButton.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(userPasswordLogInField));
        userPasswordLogInField.sendKeys(handlingPassword(user.getPassword()));

        logInSubmitButton.click();

        driver.switchTo().defaultContent();
    }


    public LogInPage openLogInFrame(String url) {
        driver.get(url);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(logInButton));
        logInButton.click();
        driver.switchTo().frame(driver.findElement(By.xpath(logInIFrame)));

        return this;
    }

    public LogInPage setUsernameDataWithEnterAction(User user) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(userNameLogInField));
        userNameLogInField.sendKeys(user.getUsername(), Keys.ENTER);

        JavascriptExecutor jscriptExecutor = (JavascriptExecutor) driver;
        colorWebElementBorder(jscriptExecutor, userPasswordLogInButton);
        return this;
    }

    public LogInPage setUsernameDataWithClickButtonAction(User user) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(userNameLogInField));
        userNameLogInField.sendKeys(user.getUsername());
        webDriverWait.until(ExpectedConditions.elementToBeClickable(userPasswordLogInButton));

        JavascriptExecutor jscriptExecutor = (JavascriptExecutor) driver;
        colorWebElementBorder(jscriptExecutor, userPasswordLogInButton);
        userPasswordLogInButton.click();
        return this;
    }

    public void setPasswordDataWithEnterAction(User user) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(userPasswordLogInField));
        userPasswordLogInField.sendKeys(handlingPassword(user.getPassword()), Keys.ENTER);

        JavascriptExecutor jscriptExecutor = (JavascriptExecutor) driver;
        colorWebElementBorder(jscriptExecutor, logInSubmitButton);
        driver.switchTo().defaultContent();
    }

    public void setPasswordDataWithClickButtonAction(User user) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(userPasswordLogInField));
        userPasswordLogInField.sendKeys(handlingPassword(user.getPassword()));

        JavascriptExecutor jscriptExecutor = (JavascriptExecutor) driver;
        colorWebElementBorder(jscriptExecutor, logInSubmitButton);
        logInSubmitButton.click();
        driver.switchTo().defaultContent();
    }

    public void validateLogIn() {
        webDriverWait.until(ExpectedConditions.titleContains("Входящие"));

        String redirectUrl = driver.getCurrentUrl();
        assertThat(redirectUrl)
                .isNotEqualTo("https://mail.ru/")
                .contains("inbox");

        logger.debug("Actual URL = <" + redirectUrl + ">");
    }


    public boolean validateLogOut() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(logInButton2));

        return (logInButton2.isDisplayed() || logInButton2.isEnabled());
    }
}
