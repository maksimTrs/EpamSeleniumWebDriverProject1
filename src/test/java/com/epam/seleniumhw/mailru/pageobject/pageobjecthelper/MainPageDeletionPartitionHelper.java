package com.epam.seleniumhw.mailru.pageobject.pageobjecthelper;

import com.epam.seleniumhw.mailru.utils.MailTypeEnum;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.epam.seleniumhw.mailru.pageobject.pageobjecthelper.JscriptExecutorHelper.getSpecifiedElementAttributeText;
import static com.epam.seleniumhw.mailru.pageobject.pageobjecthelper.JscriptExecutorHelper.getSpecifiedElementText;
import static com.epam.seleniumhw.mailru.tests.BaseTest.logger;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPageDeletionPartitionHelper {

    private static final String DRAFT_TEXT_RESULT = "Черновики, нет писем";
    private static final String SENT_TEXT_RESULT = "Отправленные, нет писем";
    private static final String INBOX_TEXT_RESULT = "Входящие, нет писем";

    public static void validateEmailDeletedPartition(MailTypeEnum mailTypeEnum,
                                                     WebDriverWait webDriverWait, JavascriptExecutor jscriptExecutor,
                                                     WebElement...webElements) {

        String text;

        switch (mailTypeEnum) {
            case DRAFT -> {
                text = getEmailPartitionData(webDriverWait, jscriptExecutor, webElements[0]);

                assertThat(text)
                        .as("Real value = " + text)
                        .isEqualTo(DRAFT_TEXT_RESULT);

                logger.debug("Result of deletion Draft Emails: " + text);
            }
            case SENT -> {
                text = getEmailPartitionData(webDriverWait, jscriptExecutor, webElements[1]);

                assertThat(text)
                        .as("Real value = " + text)
                        .isEqualTo(SENT_TEXT_RESULT);

                logger.debug("Result of deletion Sent Emails: " + text);
            }
            case INBOX -> {
                text = getEmailPartitionData(webDriverWait, jscriptExecutor, webElements[2]);

                assertThat(text.trim())
                        .as("Real value = " + text.trim())
                        .isEqualTo(INBOX_TEXT_RESULT);

                logger.debug("Result of deletion Input Emails: " + text.trim());
            }
        }
    }

    private static String getEmailPartitionData(WebDriverWait webDriverWait, JavascriptExecutor jscriptExecutor, WebElement element) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        // text = (String) jscriptExecutor.executeScript("return arguments[0].getAttribute('data-title');", draftEmailPartition);
        return getSpecifiedElementAttributeText(jscriptExecutor, "title", element);
    }
}
