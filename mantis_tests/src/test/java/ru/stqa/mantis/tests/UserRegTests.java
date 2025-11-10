package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;

public class UserRegTests extends TestBase{
    @Test
    public void testUserReg(String username){
        var email = String.format("%s@localhost", username);
        //создать пользователя на почтовом сервере,для регистрации (JamesHelper)
        // заполняем форму и отправляем письмо (браузер)
        //ждем почту (MailHelper)
        //извлечь ссылку ()
        //возвращаемся в браузер, переходим по ссылке и завершаем регистрацию(браузер)
        //Проверяем,может ли новый пользователь войти со своими кредами(http helper)

    }
}
