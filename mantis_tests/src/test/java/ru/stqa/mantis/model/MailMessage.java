package ru.stqa.mantis.model;

public record MailMessage(String from, String content) {
    public MailMessage() {
        this("","");
    }
    public MailMessage withFrom(String from) {
        return new MailMessage(from, content);
    }
    public MailMessage withContent(String content) {
        return new MailMessage(from, content);
    }
}
