package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.Utilities;
import ru.stqa.mantis.model.Credentials;

import java.io.IOException;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class UserRegTests extends TestBase {
    public static Stream<Credentials> userCredentials() {
        Supplier<Credentials> randomUser = () -> new Credentials()
                .withUsername(Utilities.stringGenerator(10))
                .withPassword(Utilities.stringGenerator(20));
        return Stream.generate(randomUser).limit(1);
    }

    @ParameterizedTest
    @MethodSource("userCredentials")
    public void testUserReg(Credentials credentials) throws InterruptedException, IOException {
        var username = credentials.username();
        var pwd = credentials.password();
        var email = String.format("%s@localhost", username);
        app.jamesCli().addUser(String.format("%s@localhost", username), pwd);  //создать пользователя на почтовом сервере,для регистрации (JamesHelper)
        app.signUp().signUp(username, email);// заполняем форму и отправляем письмо (браузер)
        var regURL = app.mail().extractUrl(email, pwd);//ждем почту (MailHelper) и извлекаем ссылку
        app.signUp().regConfirmation(regURL, username, pwd);//возвращаемся в браузер, переходим по ссылке и завершаем регистрацию(браузер)
        app.http().login(username, pwd);//Проверяем,может ли новый пользователь войти со своими кредами(http helper)
        var isLog = app.http().isLoggedIn();
        Assertions.assertTrue(isLog);
    }
    @ParameterizedTest
    @MethodSource("userCredentials")
    public void testApiUserReg(Credentials credentials) throws InterruptedException, IOException {
        var username = credentials.username();
        var pwd = credentials.password();
        var email = String.format("%s@localhost", username);
        app.jamesApi().addUser(credentials);  //создать пользователя на почтовом сервере,для регистрации (JamesHelper)
        app.signUp().signUp(username, email);// заполняем форму и отправляем письмо (браузер)
        var regURL = app.mail().extractUrl(email, pwd);//ждем почту (MailHelper) и извлекаем ссылку
        app.signUp().regConfirmation(regURL, username, pwd);//возвращаемся в браузер, переходим по ссылке и завершаем регистрацию(браузер)
        app.http().login(username, pwd);//Проверяем,может ли новый пользователь войти со своими кредами(http helper)
        var isLog = app.http().isLoggedIn();
        Assertions.assertTrue(isLog);
    }

//    @Test
//    public void testUserReg() throws InterruptedException, IOException {  //без провайдера
//        var username = Utilities.stringGenerator(10);;
//        var email = String.format("%s@localhost", username);
//        String pwd = app.property("mailbox.pwd");
//        app.jamesCli().addUser(String.format("%s@localhost", username), pwd);  //создать пользователя на почтовом сервере,для регистрации (JamesHelper)
//        app.signUp().signUp(username,email);// заполняем форму и отправляем письмо (браузер)
//        var regURL = app.mail().extractUrl(email, pwd);//ждем почту (MailHelper) и извлекаем ссылку
//        app.signUp().regConfirmation(regURL,username,pwd);//возвращаемся в браузер, переходим по ссылке и завершаем регистрацию(браузер)
//        app.http().login(username,pwd);//Проверяем,может ли новый пользователь войти со своими кредами(http helper)
//        var isLog= app.http().isLoggedIn();
//        Assertions.assertTrue(isLog);
//    }
}
