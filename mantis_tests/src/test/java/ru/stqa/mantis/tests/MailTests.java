package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailTests extends TestBase{
    @Test
    public void canReceiveMail(){
        var mails = app.mail().receive("user1@localhost","password", Duration.ofSeconds(10));
        Assertions.assertEquals(1,mails.size());
        System.out.println(mails);
    }
    @Test
    public void canDrainInboxTest(){
        app.mail().drain("user1@localhost","password");

    }
    @Test
    public void canExtractURL(){
        var mails = app.mail().receive("user1@localhost","password", Duration.ofSeconds(10));
        var text = mails.get(0).content();
        Pattern compile = Pattern.compile("http://\\S*");
        Matcher matcher = compile.matcher(text);
        if(matcher.find()){
            var url = text.substring(matcher.start(),matcher.end());
            System.out.println(url);
        }

    }
}
