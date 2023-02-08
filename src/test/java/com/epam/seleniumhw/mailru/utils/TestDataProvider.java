package com.epam.seleniumhw.mailru.utils;

import org.testng.annotations.DataProvider;

import static com.epam.seleniumhw.mailru.utils.NumberGenerator.getIntRandomValue;
import static com.epam.seleniumhw.mailru.utils.NumberGenerator.getRandomFirstDomainLvl;


public class TestDataProvider {

    private static String toWhomAddressEmailField = "TestUser_" + getIntRandomValue() + "@test." + getRandomFirstDomainLvl();
    private static String subjectEmailField = "TestSubject_" + getIntRandomValue();
    private static String messageEmailField = "TestMessage_" + getIntRandomValue();

    private static String toWhomAddressEmailField2 = "TestUser_" + (getIntRandomValue() + 33) + "@test." + getRandomFirstDomainLvl();
    private static String subjectEmailField2 = "TestSubject_" + (getIntRandomValue() + 33);
    private static String messageEmailField2 = "TestMessage_" + (getIntRandomValue() + 33);

    private static String toWhomAddressEmailField3 = "TestUser_" + (getIntRandomValue() + 99) + "@test." + getRandomFirstDomainLvl();
    private static String subjectEmailField3 = "TestSubject_" + (getIntRandomValue() + 99);
    private static String messageEmailField3 = "TestMessage_" + (getIntRandomValue() + 99);

    @DataProvider(name = "data-provider")
    public static Object[][] dataProviderForEmail() {
        return new Object[][]{
                {toWhomAddressEmailField, subjectEmailField, messageEmailField}
        };
    }

    @DataProvider(name = "send-data-provider")
    public static Object[][] dataProviderToSendEmail() {
        return new Object[][]{
                {toWhomAddressEmailField2, subjectEmailField2, messageEmailField2}
        };
    }

    @DataProvider(name = "data-provider-compare-email")
    public static Object[][] dataProviderToCompareEmail() {
        return new Object[][]{
                {toWhomAddressEmailField3, subjectEmailField3, messageEmailField3}
        };
    }

}
