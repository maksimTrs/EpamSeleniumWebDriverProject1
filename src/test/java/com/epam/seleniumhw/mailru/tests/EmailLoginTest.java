package com.epam.seleniumhw.mailru.tests;

import com.epam.seleniumhw.mailru.pageobject.MailRUMainPage;
import com.epam.seleniumhw.mailru.utils.TestDataProvider;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.testng.ISuite;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;



@Epic("1")
@Feature("Api for users")
@Tag("rest")
public class EmailLoginTest extends  BaseTest {

    private static final String emailFullUserName = "hokagemax777" + "@mail.ru";

    @Step("Создание пользователя1")
    @Story("Create user1")
    @Description("Проверяет отправку запроса на создлание пользователя и что в системе создался юзер с указанным именем1")
    @Test(groups = "smoke", testName = "mail_page_login_test")
    public void logInTest() {

        MailRUMainPage mailRUMainPage = new MailRUMainPage(driver);
        String userEmailLogInAccountName = mailRUMainPage.checkUserLogInName();

        assertThat(userEmailLogInAccountName)
                .as("Wrong email credentials OR page doesn't exist")
                .isEqualTo(emailFullUserName);
    }
}
