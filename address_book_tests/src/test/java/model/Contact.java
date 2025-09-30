package model;

import java.io.File;

public record Contact(String firstName, String middleName, String lastName, String nickName, File photo,
                      String position,
                      String company, String address, String homePhone, String mobilePhone, String workPhone,
                      String fax,
                      String email, String email2, String email3, String homepage, Integer birthDay, String birthMonth,
                      String birthYear,
                      Integer anniversaryDay, String anniversaryMonth, String anniversaryYear, String group) {
    public Contact() {
        this("", "", "", "", null, "", "", "", "", "",
                "", "", "", "", "", "", null, "", "",
                null, "", "", "");
    }

    public Contact mainFields(String firstName, String lastName,String address, String mobilePhone) {
        return new Contact(firstName, this.middleName, lastName, this.nickName, this.photo, this.position,
                this.company, address, this.homePhone, mobilePhone, this.workPhone, this.fax,
                email, this.email2, this.email3, homepage, this.birthDay, this.birthMonth, this.birthYear,
                this.anniversaryDay, this.anniversaryMonth, this.anniversaryYear, this.group);
    }

    public Contact contactWithPhoto() {
        return new Contact(firstName, middleName, this.lastName, this.nickName, photo, this.position,
                this.company, address, this.homePhone, mobilePhone, this.workPhone, this.fax,
                email, this.email2, this.email3, homepage, this.birthDay, this.birthMonth, this.birthYear,
                this.anniversaryDay, this.anniversaryMonth, this.anniversaryYear, this.group);
    }

    public Contact contactWithPhotoAndBirthDate() {
        return new Contact(firstName, middleName, this.lastName, this.nickName, photo, this.position,
                this.company, address, this.homePhone, mobilePhone, this.workPhone, this.fax,
                email, this.email2, this.email3, homepage, birthDay, birthMonth, birthYear,
                this.anniversaryDay, this.anniversaryMonth, this.anniversaryYear, this.group);
    }
}
