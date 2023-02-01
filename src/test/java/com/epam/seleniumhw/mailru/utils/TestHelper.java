package com.epam.seleniumhw.mailru.utils;

import com.epam.seleniumhw.mailru.pageobject.MainPage;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestHelper {

    public static List<String> getStringEmailListFromWebElementList(MainPage mainPage, MailTypeEnaum mailTypeEnaum) {

        List<String> listOfUsers = mainPage.getEmailWebelementList(mailTypeEnaum)
                .stream().map(WebElement::getText)
                // .filter(row -> row.equals(toWhomAddressEmailField))
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .collect(Collectors.toList());

        System.out.println("List Filtering data = " + listOfUsers);

        return listOfUsers;

    }
}
