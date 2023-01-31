package com.epam.seleniumhw.mailru.tests;

import com.epam.seleniumhw.mailru.pageobject.MailRUMainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


@Epic("Selenium_HW")
@Feature("Test_MailRu_Box")
@Tag("UI_AT_Tests")
public class EmailLoginTest extends BaseTest {

    @Story("Test Email box login")
    @Test(testName = "email_login_test")
    @Parameters({"emailName"})
    public void logInTest(String emailName) {

        MailRUMainPage mailRUMainPage = new MailRUMainPage(driver);
        String userEmailLogInAccountName = mailRUMainPage.checkUserLogInName();

        assertThat(userEmailLogInAccountName)
                .as("Wrong email credentials OR page doesn't exist")
                .isEqualTo(emailName);
    }
}
