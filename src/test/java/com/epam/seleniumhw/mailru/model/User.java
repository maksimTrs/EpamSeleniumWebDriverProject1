package com.epam.seleniumhw.mailru.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@Builder
public class User {

    private String username;
    private String password;
}
