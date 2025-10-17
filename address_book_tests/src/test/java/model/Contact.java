package model;

public record Contact(String id, String firstName, String middleName, String lastName, String nickName, String photo,
                      String position,
                      String company, String address, String homePhone, String mobilePhone, String workPhone,
                      String fax,
                      String email, String email2, String email3, String homepage, Integer birthDay, String birthMonth,
                      String birthYear,
                      Integer anniversaryDay, String anniversaryMonth, String anniversaryYear, String group) {

    public String getPhoto() {
        return photo;
    }
    public Contact() {
        this("", "", "", "", "", null, "", "", "", "", "",
                "", "", "", "", "", "", null, "", "",
                null, "", "", "");
    }

    public Contact mainFields(String firstName, String lastName,String address, String mobilePhone) {
        return new Contact(id, firstName, this.middleName, lastName, this.nickName, this.photo, this.position,
                this.company, address, this.homePhone, mobilePhone, this.workPhone, this.fax,
                email, this.email2, this.email3, homepage, this.birthDay, this.birthMonth, this.birthYear,
                this.anniversaryDay, this.anniversaryMonth, this.anniversaryYear, this.group);
    }

    public Contact contactWithPhoto(String photo) {
        return new Contact(id, firstName, middleName, lastName, this.nickName,photo, this.position,
                this.company, address, this.homePhone, mobilePhone, this.workPhone, this.fax,
                email, this.email2, this.email3, homepage, this.birthDay, this.birthMonth, this.birthYear,
                this.anniversaryDay, this.anniversaryMonth, this.anniversaryYear, this.group);
    }

    public Contact contactWithPhotoAndBirthDate(String firstName, String lastName,String address, String mobilePhone, String photo,String homepage, Integer birthDay,String birthMonth,String birthYear) {
        return new Contact(id, firstName, middleName, lastName, this.nickName, photo, this.position,
                this.company, address, this.homePhone, mobilePhone, this.workPhone, this.fax,
                email, this.email2, this.email3, homepage, birthDay, birthMonth, birthYear,
                this.anniversaryDay, this.anniversaryMonth, this.anniversaryYear, this.group);
    }

    public Contact withID(String id) {
        return new Contact(id, firstName, this.middleName, lastName, this.nickName, this.photo, this.position,
                this.company, address, this.homePhone, mobilePhone, this.workPhone, this.fax,
                email, this.email2, this.email3, homepage, this.birthDay, this.birthMonth, this.birthYear,
                this.anniversaryDay, this.anniversaryMonth, this.anniversaryYear, this.group);
    }
    public Contact withName(String name) {
        return new Contact(id, name, this.middleName, lastName, this.nickName, this.photo, this.position,
                this.company, address, this.homePhone, mobilePhone, this.workPhone, this.fax,
                email, this.email2, this.email3, homepage, this.birthDay, this.birthMonth, this.birthYear,
                this.anniversaryDay, this.anniversaryMonth, this.anniversaryYear, this.group);
    }
    public Contact withLastName(String lastName) {
        return new Contact(id, this.firstName, this.middleName, lastName, this.nickName, this.photo, this.position,
                this.company, address, this.homePhone, mobilePhone, this.workPhone, this.fax,
                email, this.email2, this.email3, homepage, this.birthDay, this.birthMonth, this.birthYear,
                this.anniversaryDay, this.anniversaryMonth, this.anniversaryYear, this.group);
    }
}
