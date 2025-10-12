package tests1;

import model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeleteContact extends TestBase {
    @Test
    public void testDeleteContact() {
        if (!app.getContact().isContactPresent()) {
            app.getContact().contactCreation(new Contact().mainFields("Mayya", "Matveeva", "улица Пушкина,дом Колотушкина", "89524582455"));
        }
        var oldContacts = app.getContact().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.getContact().deleteOneContact(oldContacts.get(index));
        var newContacts = app.getContact().getContactList();
        var expectedContacts = new ArrayList<>(oldContacts);
        expectedContacts.remove(index);
        Assertions.assertEquals(newContacts, expectedContacts);
    }
    @Test
    public void testDeleteAllContacts() {
        if (!app.getContact().isContactPresent()) {
            app.getContact().contactCreation(new Contact().mainFields("Mayya", "Matveeva", "улица Пушкина,дом Колотушкина", "89524582455"));
        }
        app.getContact().deleteAllContacts();
        int newCount = app.getContact().contactCounter();
        Assertions.assertEquals(0, newCount);
    }
}
