package com.epam.seleniumhw.mailru.tests;


import com.epam.seleniumhw.mailru.pageobject.MailRUMainPage;
import com.epam.seleniumhw.mailru.utils.TestDataProvider;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;

import java.util.List;

import static com.epam.seleniumhw.mailru.utils.MailPartitionNameList.DRAFT;
import static com.epam.seleniumhw.mailru.utils.TestHelper.getEmailListDataTestHelper;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("2")
@Feature("Api for users2")
@Tag("rest")
public class EmailDraftCreationTest extends BaseTest {

    @Step("Создание пользователя2")
    @Story("Create user2")
    @Description("Проверяет отправку запроса на создлание пользователя и что в системе создался юзер с указанным именем2")
    @Test(dataProvider = "data-provider", dataProviderClass = TestDataProvider.class,
            testName = "test_check_draft_email_exists")
    public void createDraftEmailTest(String toWhomAddressEmailField, String subjectEmailField, String messageEmailField) {

        MailRUMainPage mailRUMainPage = new MailRUMainPage(driver);
        mailRUMainPage.createNewDraftEmail(toWhomAddressEmailField, subjectEmailField, messageEmailField);

        List<String> listOfDraftUsers = getEmailListDataTestHelper(mailRUMainPage, DRAFT);

        assertThat(listOfDraftUsers).as("Wrong mapping data!")
                .isNotEmpty()
                .contains(toWhomAddressEmailField);


    }
}
