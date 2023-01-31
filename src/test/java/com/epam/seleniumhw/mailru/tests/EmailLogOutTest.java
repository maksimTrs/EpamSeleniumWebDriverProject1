package com.epam.seleniumhw.mailru.tests;


import com.epam.seleniumhw.mailru.pageobject.MailRUMainPage;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("1")
@Feature("Api for users")
@Tag("rest")
public class EmailLogOutTest extends BaseTest {


    @Step("Создание пользователя1")
    @Story("Create user1")
    @Description("Проверяет отправку запроса на создлание пользователя и что в системе создался юзер с указанным именем1")
    @Test(testName = "mail_page_log_out_test")
    public void logOutTest() {

        MailRUMainPage mailRUMainPage = new MailRUMainPage(driver);

        boolean logoutStatus = mailRUMainPage.doLogOut().checkLogOut();

        assertThat(logoutStatus)
                .as("The user still doesn't log out!")
                .isTrue();
    }


}
