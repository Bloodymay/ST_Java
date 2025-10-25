package tests1;

import common.Utilities;
import model.Contact;
import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ModifyContactTest extends TestBase {
    @Test
    public void testModifyContact() {
        if (!app.getContact().isContactPresent()) {
            app.getHibernate().creatingContact(new Contact().mainFields("123", "one, two,three", "lksajdjhshsghsdghxzgdh", "244141124541").contactWithPhoto((Utilities.getRandomFile("src/test/resources/images"))));
        }
        //app.getContact().contactModify(new Contact().mainFields("Ivan", "Ivanov", "hello world", "9999999999"));

        var initialContactsList = app.getHibernate().getContactsListHnt();//app.getContact().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(initialContactsList.size());
        var newContactFields = new Contact().withID(initialContactsList.get(index).id()).mainFields("Ivan", "Ivanov", "hello world", "9999999999").contactWithPhoto((Utilities.getRandomFile("src/test/resources/images")));
        app.getContact().contactModify(initialContactsList.get(index), newContactFields);
        app.getContact().returnToHomePage();
        var newContactList = app.getHibernate().getContactsListHnt();//app.getContact().getContactList();
        var expectedList = new ArrayList<>(initialContactsList);
        expectedList.set(index, newContactFields.withID(initialContactsList.get(index).id()).contactWithPhoto(initialContactsList.get(index).photo()));
        Comparator<Contact> compareByID = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContactList.sort(compareByID);
        expectedList.sort(compareByID);
        Assertions.assertEquals(expectedList, newContactList);
    }

}
