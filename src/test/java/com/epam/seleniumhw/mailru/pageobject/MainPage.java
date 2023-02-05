package com.epam.seleniumhw.mailru.pageobject;

import com.epam.seleniumhw.mailru.pageobject.pageobjecthelper.ActionHelper;
import com.epam.seleniumhw.mailru.utils.MailTypeEnum;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static com.epam.seleniumhw.mailru.pageobject.pageobjecthelper.JscriptExecutorHelper.*;
import static com.epam.seleniumhw.mailru.utils.MailTypeEnum.DRAFT;
import static com.epam.seleniumhw.mailru.utils.MailTypeEnum.SENT;
import static com.epam.seleniumhw.mailru.utils.TestHelper.getStringEmailListFromWebElementList;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPage extends BasePage {

    private static String toWhomElementDraftList = "//a[contains(@href, '/drafts/')]//div[@class='llc__content']/div[1]/span[1]";
    private static String toWhomElementSentList = "//a[contains(@href, '/sent/')]//div[@class='llc__content']/div[1]/span[1]";
    @FindBy(xpath = "//div[@data-testid='whiteline-account']")
    private WebElement userMailAccountSection;
    @FindBy(xpath = "//div[@data-testid='whiteline-account-exit']//div[text()='Выйти']")
    private WebElement mailExitButton;
    @FindBy(xpath = "//div[@data-testid='whiteline-account']/span[2]")
    private WebElement userMailAccountName;
    @FindBy(xpath = "//a[@href='/compose/']")
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
    @FindBy(xpath = "//a[contains(@href, '/drafts/')]//div[@class='llc__container']")
    private List<WebElement> draftEmailList;
    @FindBy(xpath = "//a[contains(@href, '/sent/')]//div[@class='llc__container']")
    private List<WebElement> sentEmailList;
    @FindBy(xpath = "//div[@aria-label='grid']")
    private WebElement emailListBlock;
    @FindBy(xpath = "//div[@data-test-id='underlay-wrapper']//button/preceding-sibling::span")
    private WebElement toWhomAddressEmailDraftField;
    @FindBy(xpath = "//div[@role='textbox']//div[contains(@id, 'BODY')]/div/div[1]")
    private WebElement bodyEmailDraftField;
    @FindBy(xpath = "//button[@data-test-id='send']")
    private WebElement sendEmailButton;
    @FindBy(xpath = "//div/a[text()='Письмо отправлено']")
    private WebElement sendEmailPopUpTextMsg;
    @FindBy(css = "span[title='Закрыть']")
    private WebElement sendEmailCloseButton;
    @FindBy(xpath = "//a[contains(@href, '/sent')]")
    private WebElement sentEmailPartition;
    @FindBy(xpath = "//a[contains(@href, 'drafts')]/ancestor::div[@role='rowgroup']")
    private WebElement draftEmailListWaiter;

    @FindBy(xpath = "//a[contains(@href, 'drafts')]//following::div[@data-qa-id='clear']//span[text()='Очистить содержимое']")
    private WebElement clearDraftEmailsButton;

    @FindBy(xpath = "//div[contains(@class, 'submit-button')]//span/div[text()='Очистить']")
    private WebElement clearEmailsPartitionConfirmButton;

    @FindBy(xpath = "//a[contains(@href, 'sent')]//following::div[@data-qa-id='clear']//span[text()='Очистить содержимое']")
    private WebElement clearSentEmailsButton;

    @FindBy(xpath = "//tbody//span[@data-title-shortcut='Ctrl+A'][@title='Выделить все']")
    private WebElement selectAllEmailsButton;

    @FindBy(xpath = "//tbody//span[@data-title-shortcut='Del']")
    private WebElement deleteAllEmailsButton;

    @FindBy(xpath = "//div/span[@class='octopus__title']")
    private WebElement deleteMessageText;

    @FindBy(xpath = "//a[contains(@href, '/inbox/?')]")
    private WebElement incomingEmailsPartition;

    @FindBy(xpath = "//div[@id='application']//div[@role='presentation']/.")
    private WebElement feedbackPopUpCloseButton;

    @FindBy(xpath = "//iframe[contains(@src, 'feedback')]")
    private WebElement feedbackPopUp;


    private String toWhomElementPattern = "//a[contains(@href, 'drafts')]//div//span[@title='%s']";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public String getUserLogInName() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(userMailAccountSection));
        return userMailAccountName.getText();
    }

    public void createNewDraftEmail(String toWhomAddressEmail, String subjectEmail, String messageEmail) {
        JavascriptExecutor jscriptExecutor = (JavascriptExecutor) driver;
        webDriverWait.until(ExpectedConditions.elementToBeClickable(userMailAccountSection));
        driver.navigate().refresh();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(createEmailButton));
        try {
            createEmailButton.click();
        } catch (Exception e) {
            driver.navigate().refresh();
            webDriverWait.until(ExpectedConditions.elementToBeClickable(createEmailButton));
            createEmailButton.click();
        }

        webDriverWait.until(ExpectedConditions.elementToBeClickable(toWhomAddressEmailField));
        toWhomAddressEmailField.sendKeys(toWhomAddressEmail);
        subjectEmailField.sendKeys(subjectEmail);

        try {
            messageEmailField.sendKeys(messageEmail);
        } catch (Exception e) {
            addTextToEmailMessageField(jscriptExecutor, messageEmail);
            // jscriptExecutor.executeScript(String.format("document.querySelector(\"div[role='textbox'] > div:first-of-type\").innerHTML='%s'", messageEmail));
        }

        emailSaveButton.click();
        emailClosePopUpButton.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(draftEmailPartition));
    }

    public void openDraftEmail(String toWhomUser) {
        //driver.navigate().refresh();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(draftEmailPartition));
        draftEmailPartition.click();

        //webDriverWait.until(ExpectedConditions.visibilityOfAllElements(emailListBlock));
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(draftEmailListWaiter));

        driver.findElement(By.xpath(String.format(toWhomElementPattern, toWhomUser))).click();
    }

    public List<String> getDraftEmailInternalFields() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(subjectEmailField));

        List<String> draftEmailInternalData = new ArrayList<>();
        draftEmailInternalData.add(toWhomAddressEmailDraftField.getText());
        draftEmailInternalData.add(subjectEmailField.getAttribute("value"));
        draftEmailInternalData.add(bodyEmailDraftField.getText());

        return draftEmailInternalData;
    }


    public void sendDraftEmail(String toWhomUser) {

        openDraftEmail(toWhomUser);

        try {
            if (feedbackPopUp.isEnabled() || feedbackPopUp.isDisplayed()) {
                driver.switchTo().frame((feedbackPopUp));
                feedbackPopUpCloseButton.click();
                driver.switchTo().defaultContent();
            }
            webDriverWait.until(ExpectedConditions.elementToBeClickable(subjectEmailField));
            sendEmailButton.click();
        } catch (Exception e) {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(subjectEmailField));
            sendEmailButton.click();
        }


        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(sendEmailPopUpTextMsg));
            sendEmailCloseButton.click();
        } catch (Exception e) {
            driver.navigate().refresh();
        }

        webDriverWait.until(ExpectedConditions.elementToBeClickable(sentEmailPartition));
    }

    public List<WebElement> getEmailWebelementList(MailTypeEnum mailTypeEnum) {

        List<WebElement> textValuesOfEmail = new ArrayList<>();

        if (mailTypeEnum == DRAFT) {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(draftEmailPartition));
            draftEmailPartition.click();

            webDriverWait.until(ExpectedConditions.visibilityOfAllElements(emailListBlock));

            textValuesOfEmail = new ArrayList<>();
            for (WebElement element : draftEmailList) {
                textValuesOfEmail = element.findElements(By.xpath(toWhomElementDraftList));
            }

            System.out.println("Aggregated Email Addresses:");
            for (WebElement webElement : textValuesOfEmail) {
                System.out.println(webElement.getText());
            }

        } else if (mailTypeEnum == SENT) {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(sentEmailPartition));
            sentEmailPartition.click();

            webDriverWait.until(ExpectedConditions.visibilityOfAllElements(emailListBlock));

            textValuesOfEmail = new ArrayList<>();
            for (WebElement element : sentEmailList) {
                textValuesOfEmail = element.findElements(By.xpath(toWhomElementSentList));
            }

            System.out.println("Aggregated Email Addresses:");
            for (WebElement webElement : textValuesOfEmail) {
                System.out.println(webElement.getText());
            }

        }

        return textValuesOfEmail;
    }


    public LogInPage doLogOut() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(userMailAccountSection));
        userMailAccountSection.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(mailExitButton));
        mailExitButton.click();

        return new LogInPage(driver);
    }


    public void validateEmailLogIn(String expectedLogInAccountName) {
        String actualLogInAccountName = this.getUserLogInName();

        assertThat(actualLogInAccountName)
                .as("Wrong email credentials OR page doesn't exist")
                .isEqualTo(expectedLogInAccountName);
    }

    public void validateEmailListWithCurrentUser(String toWhomAddressEmailField, MailTypeEnum mailTypeEnum) {
        List<String> listOfDraftUsers = getStringEmailListFromWebElementList(this, mailTypeEnum);

        assertThat(listOfDraftUsers).as("Wrong mapping data!")
                .isNotEmpty()
                .contains(toWhomAddressEmailField);
    }

    public void validateDraftEmailFieldsData(String toWhomAddressEmailField, String subjectEmailField, String messageEmailField) {
        List<String> draftEmailFieldsData = this.getDraftEmailInternalFields();

        System.out.println("List Filtering data = " + draftEmailFieldsData);

        assertThat(draftEmailFieldsData).as("Wrong mapping data!")
                .isNotEmpty()
                .containsExactly(toWhomAddressEmailField, subjectEmailField, messageEmailField);
    }

    public void validateLogOut() {
        boolean logoutStatus = this.doLogOut().validateLogOut();

        assertThat(logoutStatus)
                .as("The user still doesn't log out!")
                .isTrue();
    }


    public void deleteEmails(MailTypeEnum mailTypeEnum) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(userMailAccountSection));
        JavascriptExecutor jscriptExecutor = (JavascriptExecutor) driver;
        jscriptExecutor.executeScript("history.go(0)");

        if (mailTypeEnum == DRAFT) {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(draftEmailPartition));
            new ActionHelper().moveToElementAndRightClickHelper(driver, draftEmailPartition);

            clickOnSpecifiedElementHelper(jscriptExecutor, clearDraftEmailsButton);
            clickOnSpecifiedElementHelper(jscriptExecutor, clearEmailsPartitionConfirmButton);
        } else if (mailTypeEnum == SENT) {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(sentEmailPartition));
            new ActionHelper().moveToElementAndRightClickHelper(driver, sentEmailPartition);

            clickOnSpecifiedElementHelper(jscriptExecutor, clearSentEmailsButton);
            clickOnSpecifiedElementHelper(jscriptExecutor, clearEmailsPartitionConfirmButton);
        }
    }

    public void validateEmptyEmailPartition(MailTypeEnum mailTypeEnum) {

        JavascriptExecutor jscriptExecutor = (JavascriptExecutor) driver;
        jscriptExecutor.executeScript("history.go(0)");
        String text = null;

        if (mailTypeEnum == DRAFT) {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(draftEmailPartition));
            // text = (String) jscriptExecutor.executeScript("return arguments[0].getAttribute('data-title');", draftEmailPartition);
            text = getSpecifiedElementAttributeText(jscriptExecutor, "title", draftEmailPartition);

            assertThat(text)
                    .as("Real value = " + text)
                    .isEqualTo("Черновики, нет писем");

            System.out.println("Result of deletion Draft Emails: " + text);
        } else if (mailTypeEnum == SENT) {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(sentEmailPartition));
            //text = (String) jscriptExecutor.executeScript("return arguments[0].getAttribute('data-title');", sentEmailPartition);
            text = getSpecifiedElementAttributeText(jscriptExecutor, "title", sentEmailPartition);

            assertThat(text)
                    .as("Real value = " + text)
                    .isEqualTo("Отправленные, нет писем");

            System.out.println("Result of deletion Sent Emails: " + text);
        }
    }


    public void deleteEmailsFromIncomingPartition() {

        webDriverWait.until(ExpectedConditions.elementToBeClickable(userMailAccountSection));
        driver.navigate().refresh();
        JavascriptExecutor jscriptExecutor = (JavascriptExecutor) driver;

        webDriverWait.until(ExpectedConditions.elementToBeClickable(incomingEmailsPartition));
        incomingEmailsPartition.click();

        new ActionHelper().moveToElementAndClickOnElementHelper(driver, selectAllEmailsButton, deleteAllEmailsButton);
        clickOnSpecifiedElementHelper(jscriptExecutor, clearEmailsPartitionConfirmButton);
    }

    public void validateEmptyEmailIncomingPartition() {
        JavascriptExecutor jscriptExecutor = (JavascriptExecutor) driver;
        String text = getSpecifiedElementText(jscriptExecutor, deleteMessageText);

        assertThat(text.trim())
                .as("Real value = " + text.trim())
                .isEqualTo("Писем нет");

        System.out.println("Result of deletion Input Emails: " + text.trim());
    }
}
