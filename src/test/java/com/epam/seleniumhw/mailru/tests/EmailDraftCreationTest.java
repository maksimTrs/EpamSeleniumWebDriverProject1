package com.epam.seleniumhw.mailru.tests;


import com.epam.seleniumhw.mailru.utils.TestDataProvider;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;

@Epic("2")
@Feature("Api for users2")
@Tag("rest")
public class EmailDraftCreationTest extends BaseTest {

    @Step("Создание пользователя1")
    @Story("Create user1")
    @Description("Проверяет отправку запроса на создлание пользователя и что в системе создался юзер с указанным именем1")
    @Test(dataProvider = "data-provider", dataProviderClass = TestDataProvider.class,
            testName = "test_create_addressBook_group")
    public void createDraftEmailTest(String toWhomAddressEmailField, String subjectEmailField, String messageEmailField) {


    }
}
