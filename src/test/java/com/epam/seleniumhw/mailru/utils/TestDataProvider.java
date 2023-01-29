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

}
