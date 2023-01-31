package com.epam.seleniumhw.mailru.utils;

import com.epam.seleniumhw.mailru.pageobject.MailRUMainPage;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestHelper {

    public static List<String> getEmailListDataTestHelper(MailRUMainPage mailRUMainPage, MailPartitionNameList mailPartitionNameList) {

        List<String> listOfUsers = mailRUMainPage.getEmailList(mailPartitionNameList)
                .stream().map(WebElement::getText)
                // .filter(row -> row.equals(toWhomAddressEmailField))
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .collect(Collectors.toList());

        System.out.println("List Filtering data = " + listOfUsers);

        return listOfUsers;

    }
}
