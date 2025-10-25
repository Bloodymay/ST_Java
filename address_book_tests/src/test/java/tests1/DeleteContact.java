package tests1;

import common.Utilities;
import model.Contact;
import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class DeleteContact extends TestBase {
    @Test
    public void testDeleteContact()  {
        if (!app.getContact().isContactPresent()) {
            app.getHibernate().creatingContact(new Contact().mainFields("Mayya", "Matveeva", "улица Пушкина,дом Колотушкина", "89524582455").contactWithPhoto(Utilities.getRandomFile("src/test/resources/images")));
            app.getContact().goToTheHomePage();
        }
        Comparator<Contact> compareByID = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        var oldContacts = app.getHibernate().getContactsListHnt();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.getContact().deleteOneContact(oldContacts.get(index));
        var newContacts = app.getHibernate().getContactsListHnt();
        newContacts.sort(compareByID);
        var expectedContacts = new ArrayList<>(oldContacts);
        expectedContacts.remove(index);
        expectedContacts.sort(compareByID);
        Assertions.assertEquals(newContacts, expectedContacts);
    }
    @Test
    public void testDeleteAllContacts() {
        if (!app.getContact().isContactPresent()) {
            app.getHibernate().creatingContact(new Contact().mainFields("Mayya", "Matveeva", "улица Пушкина,дом Колотушкина", "89524582455").contactWithPhoto(Utilities.getRandomFile("src/test/resources/images")));
            app.getContact().goToTheHomePage();
        }

        app.getContact().deleteAllContacts();
        int newCount = app.getHibernate().contactCounter();
        Assertions.assertEquals(0, newCount);
    }
}
