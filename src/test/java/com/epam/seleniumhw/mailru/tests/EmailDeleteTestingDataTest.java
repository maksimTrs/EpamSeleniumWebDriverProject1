package com.epam.seleniumhw.mailru.tests;


import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;

import static com.epam.seleniumhw.mailru.utils.MailTypeEnum.*;


/**
 * test ONLY for chrome browser !!!
 */


@Epic("Selenium_HW")
@Feature("Test_MailRu_Box")
@Tag("UI_AT_Tests")
public class EmailDeleteTestingDataTest extends BaseTest {

    //@Ignore
    @Story("Delete emails from mailbox")
    @Test(testName = "email_delete_emails_test")
    public void deleteEmailsTest() {

        mainPage.deleteEmails(DRAFT);
        mainPage.validateEmptyEmailPartition(DRAFT);

        mainPage.deleteEmails(SENT);
        mainPage.validateEmptyEmailPartition(SENT);

        mainPage.deleteEmails(INBOX);
        mainPage.validateEmptyEmailPartition(INBOX);
    }
}
