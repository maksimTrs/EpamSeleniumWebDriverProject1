package com.epam.seleniumhw.mailru.tests;


import com.epam.seleniumhw.mailru.pageobject.MailRUMainPage;
import com.epam.seleniumhw.mailru.utils.TestDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;

import java.util.List;

import static com.epam.seleniumhw.mailru.utils.MailPartitionNameList.DRAFT;
import static com.epam.seleniumhw.mailru.utils.MailPartitionNameList.SENT;
import static com.epam.seleniumhw.mailru.utils.TestHelper.getEmailListDataTestHelper;
import static org.assertj.core.api.Assertions.assertThat;


@Epic("HW")
@Feature("Test mail box")
@Tag("UI_AT_Tests")
public class EmailSendDraftEmailTest extends BaseTest {


    @Story("Check the sending of the Draft Email message")
    @Description("Checking that Draft Email message was sent to the user")
    @Test(dataProvider = "send-data-provider", dataProviderClass = TestDataProvider.class,
            testName = "test_check_draft_email_main_fields")
    public void sendDraftEmailToUserTest(String toWhomAddressEmailField, String subjectEmailField, String messageEmailField) {

        MailRUMainPage mailRUMainPage = new MailRUMainPage(driver);
        mailRUMainPage.createNewDraftEmail(toWhomAddressEmailField, subjectEmailField, messageEmailField);

        mailRUMainPage.sendDraftEmail(toWhomAddressEmailField);


        List<String> listOfSentUsers = getEmailListDataTestHelper(mailRUMainPage, SENT);

        assertThat(listOfSentUsers).as("Wrong mapping data!")
                .isNotEmpty()
                .contains(toWhomAddressEmailField);


        List<String> listOfDraftUsers = getEmailListDataTestHelper(mailRUMainPage, DRAFT);


        assertThat(listOfDraftUsers).as("Draft Email is  still located on Draft Partition!")
                .doesNotContain(toWhomAddressEmailField);
    }

}
