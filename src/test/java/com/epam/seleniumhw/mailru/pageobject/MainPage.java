package com.epam.seleniumhw.mailru.pageobject;

import com.epam.seleniumhw.mailru.utils.MailTypeEnaum;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static com.epam.seleniumhw.mailru.utils.MailTypeEnaum.DRAFT;
import static com.epam.seleniumhw.mailru.utils.MailTypeEnaum.SENT;
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
    @FindBy(css = "button[data-test-id='send']")
    private WebElement sendEmailButton;
    @FindBy(xpath = "//div/a[text()='Письмо отправлено']")
    private WebElement sendEmailPopUpTextMsg;
    @FindBy(css = "span[title='Закрыть']")
    private WebElement sendEmailCloseButton;
    @FindBy(xpath = "//a[contains(@href, '/sent')]//div[text()='Отправленные']")
    private WebElement sentEmailPartition;
    @FindBy(xpath = "//a[contains(@href, 'drafts')]/ancestor::div[@role='rowgroup']")
    private WebElement draftEmailListWaiter;


    private String toWhomElementPattern = "//a[contains(@href, 'drafts')]//div//span[@title='%s']";


    public MainPage(WebDriver driver) {
        super(driver);
    }

    public String getUserLogInName() {
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


    public void openDraftEmail(String toWhomUser) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(draftEmailPartition));
        draftEmailPartition.click();

        //webDriverWait.until(ExpectedConditions.visibilityOfAllElements(emailListBlock));

        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(draftEmailListWaiter));

        driver.findElement(By.xpath(String.format(toWhomElementPattern, toWhomUser))).click();
    }

    public List<String> checkDraftEmailInternalFields() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(subjectEmailField));

        List<String> draftEmailInternalData = new ArrayList<>();
        draftEmailInternalData.add(toWhomAddressEmailDraftField.getText());
        draftEmailInternalData.add(subjectEmailField.getAttribute("value"));
        draftEmailInternalData.add(bodyEmailDraftField.getText());

        return draftEmailInternalData;
    }


    public void sendDraftEmail(String toWhomUser) {
        openDraftEmail(toWhomUser);

        webDriverWait.until(ExpectedConditions.elementToBeClickable(subjectEmailField));

        sendEmailButton.click();
        webDriverWait.until(ExpectedConditions.visibilityOf(sendEmailPopUpTextMsg));
        sendEmailCloseButton.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(sentEmailPartition));

    }

    public List<WebElement> getEmailWebelementList(MailTypeEnaum mailTypeEnaum) {

        List<WebElement> textValuesOfEmail = new ArrayList<>();

        if (mailTypeEnaum == DRAFT) {
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

        }

        if (mailTypeEnaum == SENT) {
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



    public  void validateEmailLogIn(String expectedLogInAccountName) {
        String actualLogInAccountName = this.getUserLogInName();

        assertThat(actualLogInAccountName)
                .as("Wrong email credentials OR page doesn't exist")
                .isEqualTo(expectedLogInAccountName);
    }

    public void validateEmailListWithCurrentUser(String toWhomAddressEmailField, MailTypeEnaum mailTypeEnaum) {
        List<String> listOfDraftUsers = getStringEmailListFromWebElementList(this, mailTypeEnaum);

        assertThat(listOfDraftUsers).as("Wrong mapping data!")
                .isNotEmpty()
                .contains(toWhomAddressEmailField);
    }

    public void validateDraftEmailFieldsData(String toWhomAddressEmailField, String subjectEmailField, String messageEmailField) {
        List<String> draftEmailFieldsData = this.checkDraftEmailInternalFields();

        System.out.println("List Filtering data = " + draftEmailFieldsData);

        assertThat(draftEmailFieldsData).as("Wrong mapping data!")
                .isNotEmpty()
                .containsExactly(toWhomAddressEmailField, subjectEmailField, messageEmailField);
    }
}
