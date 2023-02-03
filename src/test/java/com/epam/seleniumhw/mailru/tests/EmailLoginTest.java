package com.epam.seleniumhw.mailru.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


@Epic("Selenium_HW")
@Feature("Test_MailRu_Box")
@Tag("UI_AT_Tests")
public class EmailLoginTest extends BaseTest {

    @Story("Test Email box login")
    @Test(testName = "email_login_test")
    @Parameters({"emailName"})
    public void logInTest(String expectedLogInAccountName) {

        mainPage.validateEmailLogIn(expectedLogInAccountName);
    }
}
