package com.epam.seleniumhw.mailru.service;

import java.util.ResourceBundle;

public class TestDataReader {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("ENVIRONMENT"));

    public static String getTestData(String key) {
        return resourceBundle.getString(key);
    }
}
