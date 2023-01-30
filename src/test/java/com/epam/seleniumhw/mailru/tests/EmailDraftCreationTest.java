package com.epam.seleniumhw.mailru.tests;


import com.epam.seleniumhw.mailru.pageobject.MailRUMainPage;
import com.epam.seleniumhw.mailru.utils.TestDataProvider;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.seleniumhw.mailru.utils.MailPartitions.DRAFT;
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


        List<String> listOfUsersToSend = mailRUMainPage.getEmailList(DRAFT)
                .stream().map(WebElement::getText)
                .filter(row -> row.contains(toWhomAddressEmailField))
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .collect(Collectors.toList());

        logger.info("List Filtering data = " + listOfUsersToSend);


        assertThat(listOfUsersToSend).as("Wrong mapping data!")
                .isNotEmpty()
                .containsOnly(toWhomAddressEmailField);


    }
}
