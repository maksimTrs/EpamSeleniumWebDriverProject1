package com.epam.seleniumhw.mailru.tests;


import com.epam.seleniumhw.mailru.pageobject.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Selenium_HW")
@Feature("Test_MailRu_Box")
@Tag("UI_AT_Tests")
public class EmailLogOutTest extends BaseTest {

    @Story("Email logout test")
    @Test(testName = "email_log_out_test")
    public void logOutTest() {

        boolean logoutStatus = mainPage.doLogOut().checkLogOut();

        assertThat(logoutStatus)
                .as("The user still doesn't log out!")
                .isTrue();
    }


}
