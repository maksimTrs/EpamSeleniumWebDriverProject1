package com.epam.seleniumhw.mailru.tests;


import com.epam.seleniumhw.mailru.utils.TestDataProvider;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;

import static com.epam.seleniumhw.mailru.utils.MailTypeEnum.SENT;


@Epic("Selenium_HW")
@Feature("Test_MailRu_Box")
@Tag("UI_AT_Tests")
public class EmailSendDraftEmailTest extends BaseTest {


    @Story("Check the sending Email from Draft to Sent partition")
    @Test(dataProvider = "send-data-provider", dataProviderClass = TestDataProvider.class,
            testName = "test_check_send_draft_email")
    public void sendDraftEmailToUserTest(String toWhomAddressEmailField, String subjectEmailField, String messageEmailField) {

        mainPage.createNewDraftEmail(toWhomAddressEmailField, subjectEmailField, messageEmailField);
        mainPage.sendDraftEmail(toWhomAddressEmailField);
        mainPage.validateEmailListWithCurrentUser(toWhomAddressEmailField, SENT);
    }
}
