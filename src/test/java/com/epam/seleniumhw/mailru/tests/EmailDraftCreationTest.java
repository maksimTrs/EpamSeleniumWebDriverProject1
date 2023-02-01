package com.epam.seleniumhw.mailru.tests;


import com.epam.seleniumhw.mailru.pageobject.MainPage;
import com.epam.seleniumhw.mailru.utils.TestDataProvider;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;

import java.util.List;

import static com.epam.seleniumhw.mailru.utils.MailPartitionNameList.DRAFT;
import static com.epam.seleniumhw.mailru.utils.TestHelper.getEmailListDataTestHelper;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("Selenium_HW")
@Feature("Test_MailRu_Box")
@Tag("UI_AT_Tests")
public class EmailDraftCreationTest extends BaseTest {

    @Story("Check that Email was moved to the Draft Partition")
    @Test(dataProvider = "data-provider", dataProviderClass = TestDataProvider.class,
            testName = "test_check_draft_email_exists")
    public void createDraftEmailTest(String toWhomAddressEmailField, String subjectEmailField, String messageEmailField) {

        mainPage.createNewDraftEmail(toWhomAddressEmailField, subjectEmailField, messageEmailField);

        List<String> listOfDraftUsers = getEmailListDataTestHelper(mainPage, DRAFT);

        assertThat(listOfDraftUsers).as("Wrong mapping data!")
                .isNotEmpty()
                .contains(toWhomAddressEmailField);


    }
}
