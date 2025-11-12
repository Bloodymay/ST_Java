package ru.stqa.mantis.manager;

import jakarta.mail.Flags;
import jakarta.mail.Folder;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import ru.stqa.mantis.model.MailMessage;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailHelper extends HelperBase {
    public MailHelper(AppManager manager) {
        super(manager);
    }

    public List<MailMessage> receive(String username, String password, Duration duration) {
        var startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < startTime + duration.toMillis()) {
            try {
                var inbox = getInbox(username, password);
                inbox.open(Folder.READ_ONLY);
                var messages = inbox.getMessages();
                var result = Arrays.stream(messages).map(m -> {
                    try {
                        return new MailMessage().withFrom(m.getFrom()[0].toString()).withContent((String) m.getContent());
                    } catch (MessagingException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
                inbox.close();
                inbox.getStore().close();
                if (result.size() > 0) {
                return result;
                }
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(1000);}
            catch (RuntimeException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("Could not find any mail messages");
    }
    public void drain(String username, String password) {

        try {
            var inbox = getInbox(username,password);
            inbox.open(Folder.READ_WRITE);
            Arrays.stream(inbox.getMessages()).forEach(m -> {
                try {
                    m.setFlag(Flags.Flag.DELETED, true);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });
            inbox.close();
            inbox.getStore().close();
        }
        catch (MessagingException e){
            throw new RuntimeException(e);
        }



    }
    private static Folder getInbox(String username, String password) {
        try {


            var session = Session.getInstance(new Properties());
            var store = session.getStore("pop3");
            store.connect("localhost", username, password);
            var inbox = store.getFolder("INBOX");
            return inbox;
        }
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public String extractUrl(String username, String password) {
        var mails = receive(username,password, Duration.ofSeconds(10));
        var text = mails.get(0).content();
        Pattern compile = Pattern.compile("http://\\S*");
        Matcher matcher = compile.matcher(text);
        if(matcher.find()){
            var url = text.substring(matcher.start(),matcher.end());
            return url;
        }
        return null;
    }
}
