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
    public Date deprecated = new Date();

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
