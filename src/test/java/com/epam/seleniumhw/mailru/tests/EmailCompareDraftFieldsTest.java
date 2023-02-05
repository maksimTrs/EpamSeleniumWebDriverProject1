package com.epam.seleniumhw.mailru.tests;


import com.epam.seleniumhw.mailru.utils.TestDataProvider;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;

@Epic("Selenium_HW")
@Feature("Test_MailRu_Box")
@Tag("UI_AT_Tests")
public class EmailCompareDraftFieldsTest extends BaseTest {

    @Story("Check Input New Email data fields VS Draft Email data fields")
    @Test(dataProvider = "data-provider-compare-email", dataProviderClass = TestDataProvider.class,
            testName = "test_check_draft_email_main_fields")
    public void checkEmailFieldsTest(String toWhomAddressEmailField, String subjectEmailField, String messageEmailField) {

        mainPage.createNewDraftEmail(toWhomAddressEmailField, subjectEmailField, messageEmailField);
        mainPage.openDraftEmail(toWhomAddressEmailField);
        mainPage.validateDraftEmailFieldsData(toWhomAddressEmailField, subjectEmailField, messageEmailField);
    }
}
