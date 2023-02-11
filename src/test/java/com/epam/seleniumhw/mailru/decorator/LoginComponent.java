package com.epam.seleniumhw.mailru.decorator;

import com.epam.seleniumhw.mailru.model.User;
import com.epam.seleniumhw.mailru.pageobject.LogInPage;


/**
 * @see <a href="https://habr.com/ru/post/550204/">UI Automation Patterns and Methodologies: Real-life Examples</a>
 */

public interface LoginComponent {
    void login(LogInPage logInPage, String url, User user);
}
