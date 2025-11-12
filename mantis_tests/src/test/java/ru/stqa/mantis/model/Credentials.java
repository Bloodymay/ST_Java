package ru.stqa.mantis.model;

public record Credentials(String username, String password) {
    public Credentials() {
        this("","");
    }
    public Credentials withUsername(String username) {
        return new Credentials(username, password);
    }
    public Credentials withPassword(String password) {
        return new Credentials(username, password);
    }
}
