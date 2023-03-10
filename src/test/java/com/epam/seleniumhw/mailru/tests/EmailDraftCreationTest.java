package com.epam.seleniumhw.mailru.tests;


import com.epam.seleniumhw.mailru.utils.TestDataProvider;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;

import static com.epam.seleniumhw.mailru.utils.MailTypeEnum.DRAFT;

@Epic("Selenium_HW")
@Feature("Test_MailRu_Box")
@Tag("UI_AT_Tests")
public class EmailDraftCreationTest extends BaseTest {

    @Story("Check that Email was moved to the Draft Partition")
    @Test(dataProvider = "data-provider", dataProviderClass = TestDataProvider.class,
            testName = "test_check_draft_email_exists")
    public void createDraftEmailTest(String toWhomAddressEmailField, String subjectEmailField, String messageEmailField) {

        mainPage.createNewDraftEmail(toWhomAddressEmailField, subjectEmailField, messageEmailField);
        mainPage.validateEmailListWithCurrentUser(toWhomAddressEmailField, DRAFT);
    }
}
