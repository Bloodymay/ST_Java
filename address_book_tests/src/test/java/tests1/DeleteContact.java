package tests1;

import model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeleteContact extends TestBase {
    @Test
    public void testDeleteContact() {
        if (!app.getContact().isContactPresent()) {
            app.getContact().contactCreation(new Contact().mainFields("Mayya", "Matveeva", "улица Пушкина,дом Колотушкина", "89524582455"));
        }
        int initialCount = app.getContact().contactCounter();
        app.getContact().deleteOneContact();
        int newCount = app.getContact().contactCounter();
        Assertions.assertEquals(initialCount - 1, newCount);
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
