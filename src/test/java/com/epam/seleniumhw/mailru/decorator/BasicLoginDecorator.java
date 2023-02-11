package com.epam.seleniumhw.mailru.decorator;

import com.epam.seleniumhw.mailru.model.User;
import com.epam.seleniumhw.mailru.pageobject.LogInPage;

public class BasicLoginDecorator extends LoginDecorator {

    public BasicLoginDecorator(LoginComponent loginComponent) {
        super(loginComponent);
    }

    @Override
    public void login(LogInPage logInPage, String url, User user) {
/*        logInPage
                .openLogInFrame(url)
                .setUsernameDataWithClickButtonAction(user)
                .setPasswordDataWithClickButtonAction(user);*/
        super.login(logInPage, url, user);
        logInPage.validateLogIn();
    }
}
