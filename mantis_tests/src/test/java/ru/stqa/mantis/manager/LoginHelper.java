package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {
    public LoginHelper(AppManager manager) {
        super(manager);
    }

    public void login(String username, String pwd) {
        if (!isElementPresent(By.cssSelector("input[type='submit']"))) {
            manager.driver().get(manager.property("web.baseUrl"));
        }
        typeText(By.name("username"), username);
        clickElement(By.cssSelector("input[type='submit']"));
        typeText(By.name("password"), pwd);
        clickElement(By.cssSelector("input[type='submit']"));
    }

    public boolean isLoggedIn() {
        return isElementPresent(By.cssSelector("span.user-info"));
    }
}
