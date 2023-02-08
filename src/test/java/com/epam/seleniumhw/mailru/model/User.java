package com.epam.seleniumhw.mailru.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class User {

    private String username;
    private String password;
}
