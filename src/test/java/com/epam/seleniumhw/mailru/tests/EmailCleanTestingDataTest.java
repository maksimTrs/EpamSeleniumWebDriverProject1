package com.epam.seleniumhw.mailru.tests;


import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;

import static com.epam.seleniumhw.mailru.utils.MailTypeEnum.DRAFT;
import static com.epam.seleniumhw.mailru.utils.MailTypeEnum.SENT;

@Epic("Selenium_HW")
@Feature("Test_MailRu_Box")
@Tag("UI_AT_Tests")
public class EmailCleanTestingDataTest extends BaseTest{


    @Story("Delete emails from mailbox")
    @Test(testName = "email_delete_emails_test")
    public void logOutTest() {

        mainPage.deleteEmails(DRAFT);
        mainPage.validateEmptyEmailPartition(DRAFT);

        mainPage.deleteEmails(SENT);
        mainPage.validateEmptyEmailPartition(SENT);

    }

}
