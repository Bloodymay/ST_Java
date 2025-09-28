package model;

public record Group(String name, String header, String footer) {
    public Group() {
        this("", "", "");
    }

    public Group withName(String name) {
        return new Group(name, header, footer);
    }

    public Group withHeader(String header) {
        return new Group(name, header, footer);
    }

    public Group withFooter(String footer) {
        return new Group(name, header, footer);
    }
}