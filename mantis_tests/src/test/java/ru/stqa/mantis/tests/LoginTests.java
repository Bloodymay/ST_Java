package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class LoginTests extends TestBase{
    @Test
    public void testLogin(){
        app.session().login("administrator", "root");
        Assertions.assertTrue(app.session().isLoggedIn());
    }
    @Test
    public void testLoginHTTP() throws IOException {
        app.http().login(app.property("web.username"), app.property("web.pwd"));
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
