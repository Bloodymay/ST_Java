package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpHelper extends HelperBase{
    public SignUpHelper(AppManager manager) {
        super(manager);
    }
    public void signUp(String username, String email) {
        if (!isElementPresent(By.linkText("Signup for a new account"))){

            manager.driver().get(manager.property("web.baseUrl"));
        }
        clickElement(By.linkText("Signup for a new account"));
        typeText(By.name("username"), username);
        typeText(By.name("email"), email);
        clickElement(By.cssSelector("input[type='submit']"));

    }
    public void regConfirmation(String url, String username, String password) {
        manager.driver().get(url);
        fillPassword(username, password);
        clickElement(By.xpath("/html/body/div/div/div/div/div/div[5]/div/div/div/div/form/fieldset/span/button"));
    }
    public void fillPassword(String username, String password) {
        typeText(By.name("realname"), username);
        typeText(By.name("password"), password);
        typeText(By.name("password_confirm"), password);
    }

}
