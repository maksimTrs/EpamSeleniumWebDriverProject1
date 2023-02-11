package com.epam.seleniumhw.mailru.decorator;

import com.epam.seleniumhw.mailru.model.User;
import com.epam.seleniumhw.mailru.pageobject.LogInPage;

public abstract class LoginDecorator implements LoginComponent {

    private final LoginComponent loginComponent;

    public LoginDecorator(LoginComponent loginComponent) {
        this.loginComponent = loginComponent;
    }

    @Override
    public void login(LogInPage logInPage, String url, User user) {
        loginComponent.login(logInPage, url, user);
    }
}
