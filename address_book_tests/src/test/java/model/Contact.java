package model;

public record Contact(String id,
                      String firstName,
                      String middleName,
                      String lastName,
                      String nickName,
                      String photo,
                      String position,
                      String company,
                      String address,
                      String homePhone,
                      String mobilePhone,
                      String workPhone,
                      String fax,
                      String email,
                      String email2,
                      String email3,
                      String homepage,
                      Integer birthDay,
                      String birthMonth,
                      String birthYear,
                      Integer anniversaryDay,
                      String anniversaryMonth,
                      String anniversaryYear,
                      String group
) {

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
    public Contact mainFieldsWithID(String id,String firstName, String lastName,String address, String mobilePhone) {
        return new Contact(id, firstName, this.middleName, lastName, this.nickName, this.photo, this.position,
                this.company, address, this.homePhone, mobilePhone, this.workPhone, this.fax,
                email, this.email2, this.email3, homepage, this.birthDay, this.birthMonth, this.birthYear,
                this.anniversaryDay, this.anniversaryMonth, this.anniversaryYear, this.group);
    }
    public Contact withHomePhone(String homePhone) {
        return new Contact(this.id, this.firstName, this.middleName, this.lastName, this.nickName, this.photo, this.position,
                this.company, this.address, homePhone, this.mobilePhone, this.workPhone, this.fax,
                email, this.email2, this.email3, this.homepage, this.birthDay, this.birthMonth, this.birthYear,
                this.anniversaryDay, this.anniversaryMonth, this.anniversaryYear, this.group);
    }
    public Contact withWorkPhone(String workPhone) {
        return new Contact(this.id, this.firstName, this.middleName, this.lastName, this.nickName, this.photo, this.position,
                this.company, this.address, this.homePhone, this.mobilePhone, workPhone, this.fax,
                email, this.email2, this.email3, this.homepage, this.birthDay, this.birthMonth, this.birthYear,
                this.anniversaryDay, this.anniversaryMonth, this.anniversaryYear, this.group);
    }
    public Contact withSecondaryPhone(String secondary) {
        return new Contact(this.id, this.firstName, this.middleName, this.lastName, this.nickName, this.photo, this.position,
                this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.fax,
                email, this.email2, this.email3, this.homepage, this.birthDay, this.birthMonth, this.birthYear,
                this.anniversaryDay, this.anniversaryMonth, this.anniversaryYear, this.group);
    }
    public Contact withEmail(String email) {
        return new Contact(this.id, this.firstName, this.middleName, this.lastName, this.nickName, this.photo, this.position,
                this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.fax,
                email, this.email2, this.email3, this.homepage, this.birthDay, this.birthMonth, this.birthYear,
                this.anniversaryDay, this.anniversaryMonth, this.anniversaryYear, this.group);
    }
    public Contact withEmail2(String email2) {
        return new Contact(this.id, this.firstName, this.middleName, this.lastName, this.nickName, this.photo, this.position,
                this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.fax,
                this.email, email2, this.email3, this.homepage, this.birthDay, this.birthMonth, this.birthYear,
                this.anniversaryDay, this.anniversaryMonth, this.anniversaryYear, this.group);
    }
    public Contact withEmail3(String email3) {
        return new Contact(this.id, this.firstName, this.middleName, this.lastName, this.nickName, this.photo, this.position,
                this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.fax,
                this.email, this.email2, email3, this.homepage, this.birthDay, this.birthMonth, this.birthYear,
                this.anniversaryDay, this.anniversaryMonth, this.anniversaryYear, this.group);
    }
}
