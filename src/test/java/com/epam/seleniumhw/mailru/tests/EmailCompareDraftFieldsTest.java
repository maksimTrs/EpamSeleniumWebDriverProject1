package com.epam.seleniumhw.mailru.tests;


import com.epam.seleniumhw.mailru.pageobject.MailRUMainPage;
import com.epam.seleniumhw.mailru.utils.TestDataProvider;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("HW")
@Feature("Test mail box")
@Tag("UI_AT_Tests")
public class EmailCompareDraftFieldsTest extends BaseTest {

    @Story("Check Input Email data VS Draft Email data")
    @Description("Checking that input toWhomAddressEmailField, subjectEmailField, messageEmailField field" +
            " values have the same result in Draft Email")
    @Test(dataProvider = "data-provider", dataProviderClass = TestDataProvider.class,
            testName = "test_check_draft_email_main_fields")
    public void checkEmailFieldsTest(String toWhomAddressEmailField, String subjectEmailField, String messageEmailField) {

        MailRUMainPage mailRUMainPage = new MailRUMainPage(driver);
        mailRUMainPage.createNewDraftEmail(toWhomAddressEmailField, subjectEmailField, messageEmailField);

        mailRUMainPage.openDraftEmail(toWhomAddressEmailField);
        List<String> draftEmailFieldsData = mailRUMainPage.checkDraftEmailInternalFields();

        logger.info("List Filtering data = " + draftEmailFieldsData);

        assertThat(draftEmailFieldsData).as("Wrong mapping data!")
                .isNotEmpty()
                .containsExactly(toWhomAddressEmailField, subjectEmailField, messageEmailField);
    }
}
