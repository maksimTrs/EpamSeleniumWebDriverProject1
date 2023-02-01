package com.epam.seleniumhw.mailru.tests;


import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;

@Epic("Selenium_HW")
@Feature("Test_MailRu_Box")
@Tag("UI_AT_Tests")
public class EmailLogOutTest extends BaseTest {

    @Story("Email logout test")
    @Test(testName = "email_log_out_test")
    public void logOutTest() {

        mainPage.validateLogOut();
    }


}
