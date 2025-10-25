package applicationManager.hbn;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name="addressbook")
public class ContactRecord {
    @Id
    //@Column(name="group_id")
    public int id;
    //@Column(name="firstname")
    public String firstname;
    //@Column(name="lastname")
    public String lastname;
    //@Column(name="group_footer")
    public String address;
    public String mobile;
    //public Date deprecated = new Date();
    public String middlename="";
    public String nickname="";
    public String photo;
    public String title = "";
    public String company = "";
    public String home = "";
    public String work = "";
    public String fax = "";
    public String email = "";
    public String email2 = "";
    public String email3 = "";
    public String homepage = "";
    public Integer bday = 1;
    public String bmonth = "";
    public String byear = "";
    public Integer aday = 1;
    public String amonth = "";
    public String ayear = "";
    public Date created = new Date();
    public Date modified = new Date();

//id, firstName, this.middleName, lastName, this.nickName, this.photo, this.position,
//                this.company, address, this.homePhone, mobilePhone, this.workPhone, this.fax,
//                email, this.email2, this.email3, homepage, this.birthDay, this.birthMonth, this.birthYear,
//                this.anniversaryDay, this.anniversaryMonth, this.anniversaryYear, this.group

    public ContactRecord() {
    }
    public ContactRecord(int id, String firstname, String lastname, String address, String mobile) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.mobile = mobile;
    }

}
