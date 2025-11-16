package tests1;

import common.Utilities;
import io.qameta.allure.Allure;
import model.Contact;
import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class DeleteContact extends TestBase {
    @Test
    public void testDeleteContact() {
        Allure.step("Checking precondition", step-> {
            if (!app.getContact().isContactPresent()) {
                app.getHibernate().creatingContact(new Contact().mainFields("Mayya", "Matveeva", "улица Пушкина,дом Колотушкина", "89524582455").contactWithPhoto(Utilities.getRandomFile("src/test/resources/images")));
                app.getContact().goToTheHomePage();
            }
        }) ;

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
        Allure.step("Validating results", step-> {
            Assertions.assertEquals(newContacts, expectedContacts);
        });

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

    @Test
    public void testDeleteContactFromGroup() throws SQLException {
        if (app.getHibernate().getGroupCount() == 0) {
            app.getHibernate().creatingGroup(new Group("", "group_name ", "group_header", "group_footer"));
        }
        if (!app.getContact().isContactPresent()) {
            app.getHibernate().creatingContact(new Contact().mainFields("Mayya", "Matveeva", "улица Пушкина,дом Колотушкина", "89524582455").contactWithPhoto(Utilities.getRandomFile("src/test/resources/images")));
            app.getContact().goToTheHomePage();
        }
        var group = app.getHibernate().getGroupsListHnt().get(0);
        var oldRelated = app.getHibernate().getContactsInGroup(group);
        var relations = app.getJdbc().checkContactsInGroup(group);
        var index1 = 0;
        if (oldRelated.size() == 0 || relations == false) {
            app.getHibernate().getContactsListHnt();
            var rnd = new Random();
            index1 = rnd.nextInt(app.getHibernate().getContactsListHnt().size());
            var contact = app.getHibernate().getContactsListHnt().get(index1);
            app.getContact().addContactToGroup(contact, group);
        }
        var rnd = new Random();
        var index2=rnd.nextInt(app.getHibernate().getContactsInGroup(group).size());
        oldRelated = app.getHibernate().getContactsInGroup(group);
        var contact = oldRelated.get(index2);
        app.getContact().goToTheHomePage();
        app.getContact().deleteContactFromGroup(group, contact);
        var newRelated = app.getHibernate().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() - 1, newRelated.size());


    }
}
