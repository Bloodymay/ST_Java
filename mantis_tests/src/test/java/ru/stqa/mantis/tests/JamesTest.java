package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.Utilities;

public class JamesTest extends TestBase {
    @Test
    public void canCreateUser() throws InterruptedException {
        app.jamesCli().addUser(String.format("%s@localhost", Utilities.stringGenerator(10)), "password");
    }
}
