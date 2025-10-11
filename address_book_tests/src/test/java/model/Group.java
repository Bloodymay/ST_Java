package model;

public record Group(String id, String name, String header, String footer) {
    public Group() {
        this("", "", "", "");
    }

    public Group withName(String name) {
        return new Group(id, name, header, footer);
    }

    public Group withHeader(String header) {
        return new Group(id, name, header, footer);
    }

    public Group withFooter(String footer) {
        return new Group(id, name, header, footer);
    }
    public Group withID(String id) {
        return new Group(id, name , header, footer);
    }
}