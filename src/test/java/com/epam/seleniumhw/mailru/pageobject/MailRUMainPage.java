package com.epam.seleniumhw.mailru.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class MailRUMainPage extends AbstractPage {


    @FindBy(xpath = "//div[@data-testid='whiteline-account']")
    private WebElement userMailAccountSection;

    @FindBy(xpath = "//div[@data-testid='whiteline-account']/span[2]")
    private WebElement userMailAccountName;

    @FindBy(xpath = "//a[@title='Написать письмо']")
    private WebElement createEmailButton;

    @FindBy(xpath = "//div[contains(@class, 'contactsContainer')]//input")
    private WebElement toWhomAddressEmailField;

    @FindBy(xpath = "//div[contains(@class, 'subject__wrapper')]//input")
    private WebElement subjectEmailField;

    @FindBy(xpath = "//div[contains(@class, 'editor_container')]//div[@role='textbox']/div[1]")
    private WebElement messageEmailField;


    @FindBy(css = "button[data-test-id='save']")
    private WebElement emailSaveButton;

    @FindBy(css = "button[title='Закрыть']")
    private WebElement emailClosePopUpButton;


    @FindBy(xpath = "//a[contains(@href, '/drafts')]")
    private WebElement draftEmailPartition;


    @FindBy(xpath = "//div[@class='llc__container']")
    private List<WebElement> draftEmailList;


    private static String toWhomElementPattern = "//div[@class='llc__content']/div/span[text()='%s']";


    public MailRUMainPage(WebDriver driver) {
        super(driver);
    }


    public String checkUserLogInName() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(userMailAccountSection));
        return userMailAccountName.getText();
    }


    public void createNewDraftEmail(String toWhomAddressEmail, String subjectEmail, String messageEmail) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(userMailAccountSection));
        driver.navigate().refresh();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(createEmailButton));
        createEmailButton.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(toWhomAddressEmailField));
        toWhomAddressEmailField.sendKeys(toWhomAddressEmail);
        subjectEmailField.sendKeys(subjectEmail);
        messageEmailField.sendKeys(messageEmail);

        emailSaveButton.click();
        emailClosePopUpButton.click();
    }

    public List<WebElement> getDraftEmailList() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(draftEmailPartition));
        draftEmailPartition.click();

        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(draftEmailList));

        List<WebElement> textValuesOfEmail = new ArrayList<>();
        for (WebElement element : draftEmailList) {
            textValuesOfEmail = element.findElements(By.xpath("//div[@class='llc__content']/div[1]/span[1]"));
        }

        System.out.println("Aggregated Email Addresses:");
        for (WebElement webElement:textValuesOfEmail) {
            System.out.println(webElement.getText());
        }

        return textValuesOfEmail;
    }

    public void openDraftEmail(String toWhomUser) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(draftEmailPartition));
        draftEmailPartition.click();

        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(draftEmailList));
        driver.findElement(By.xpath(String.format(toWhomElementPattern, toWhomUser))).click();
    }

}
