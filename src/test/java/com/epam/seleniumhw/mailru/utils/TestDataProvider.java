package com.epam.seleniumhw.mailru.utils;

import org.testng.annotations.DataProvider;

import static com.epam.seleniumhw.mailru.utils.NumberGenerator.getIntRandomValue;
import static com.epam.seleniumhw.mailru.utils.NumberGenerator.getRandomFirstDomainLvl;


public class TestDataProvider {

    private static String toWhomAddressEmailField = "TestUser_" + getIntRandomValue() + "@test." + getRandomFirstDomainLvl();
    private static String subjectEmailField = "TestSubject_" + getIntRandomValue();
    private static String messageEmailField = "TestMessage_" + getIntRandomValue();

    @DataProvider(name = "data-provider")
    public static Object[][] dataProviderForEmail() {
        return new Object[][]{
                {toWhomAddressEmailField, subjectEmailField, messageEmailField}
        };
    }

    @DataProvider(name = "send-data-provider")
    public static Object[][] dataProviderToSendEmail() {
        return new Object[][]{
                {toWhomAddressEmailField + 99, subjectEmailField + 99, messageEmailField + 99}
        };
    }

    @DataProvider(name = "data-provider-compare-email")
    public static Object[][] dataProviderToCompareEmail() {
        return new Object[][]{
                {toWhomAddressEmailField + 33, subjectEmailField + 33, messageEmailField + 33}
        };
    }

}
