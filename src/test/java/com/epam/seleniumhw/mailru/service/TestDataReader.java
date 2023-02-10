package com.epam.seleniumhw.mailru.service;

import java.util.ResourceBundle;

public class TestDataReader {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("qa");

    public static String getTestData(String key) {
        if (System.getProperty("ENVIRONMENT") != null && (!System.getProperty("ENVIRONMENT").isEmpty())) {
            resourceBundle = ResourceBundle.getBundle(System.getProperty("ENVIRONMENT"));
            return resourceBundle.getString(key);
        }
        return resourceBundle.getString(key);
    }
}
