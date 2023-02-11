package com.epam.seleniumhw.mailru.decorator;

import com.epam.seleniumhw.mailru.model.User;
import com.epam.seleniumhw.mailru.pageobject.LogInPage;

public class BasicLoginComponent implements LoginComponent {

    @Override
    public void login(LogInPage logInPage, String url, User user) {
        logInPage.
                openLogInFrame(url)
                .setUsernameDataWithEnterAction(user)
                .setPasswordDataWithEnterAction(user);
    }
}
