package ru.stqa.mantis.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.CircularOutputStream;
import org.openqa.selenium.os.ExternalProcess;

import java.time.Duration;

public class JamesCliHelper extends HelperBase{
    public JamesCliHelper(AppManager manager) {
        super(manager);
    }
    public void addUser(String email, String password) throws InterruptedException {
        ExternalProcess.builder()
                .command("java", "-cp", "\"james-server-jpa-app.lib/*\"", "org.apache.james.cli.ServerCmd", "AddUser", email, password)
                .directory(manager.property("james.directory"))
                .copyOutputTo(System.err)
                .start()
                .waitFor(Duration.ofHours(1));
    }
}
