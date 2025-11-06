package tests1;


import common.Utilities;
import model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {
    @Test
    public void testPhones() {
        if (!app.getContact().isContactPresent()) {
            app.getHibernate().creatingContact(new Contact()
                    .mainFields(Utilities.stringGenerator(10),
                            Utilities.stringGenerator(10),
                            Utilities.stringGenerator(10),
                            Utilities.phoneGenerator(10))
                    .contactWithPhoto(Utilities.getRandomFile("src/test/resources/images")));
            app.getContact().goToTheHomePage();
        }
        if (!app.getContact().isContactPresent()) {
            app.getHibernate().creatingContact(new Contact().mainFields(Utilities.stringGenerator(10), Utilities.stringGenerator(10), Utilities.stringGenerator(15), Utilities.phoneGenerator(10)).contactWithPhoto(Utilities.getRandomFile("src/test/resources/images")));
            app.getContact().goToTheHomePage();
        }
        var contacts = app.getHibernate().getContactsListHnt();
        var contact = contacts.get(0);
        var phones = app.getContact().getPhones(contact);
        var expected = Stream.of(contact.homePhone(), contact.mobilePhone(), contact.workPhone()).filter(s -> s != null && !"".equals(s)).collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, phones);
    }

    @Test
    public void testAllPhones() {
        if (!app.getContact().isContactPresent()) {
            app.getHibernate().creatingContact(new Contact()
                    .mainFields(Utilities.stringGenerator(10),
                            Utilities.stringGenerator(10),
                            Utilities.stringGenerator(10),
                            Utilities.phoneGenerator(10))
                    .contactWithPhoto(Utilities.getRandomFile("src/test/resources/images")));
            app.getContact().goToTheHomePage();
        }
        var contacts = app.getHibernate().getContactsListHnt();
        var expected = contacts.stream().collect(Collectors.toMap(Contact::id, contact ->
                Stream.of(contact.homePhone(), contact.mobilePhone(), contact.workPhone()).filter(s -> s != null && !"".equals(s)).collect(Collectors.joining("\n"))
        ));
        var phones = app.getContact().getAllPhones();
        Assertions.assertEquals(expected, phones);

    }

    @Test
    public void testAllAddresses() {
        if (!app.getContact().isContactPresent()) {
            app.getHibernate().creatingContact(new Contact()
                    .mainFields(Utilities.stringGenerator(10),
                            Utilities.stringGenerator(10),
                            Utilities.stringGenerator(10),
                            Utilities.phoneGenerator(10))
                    .contactWithPhoto(Utilities.getRandomFile("src/test/resources/images")));
            app.getContact().goToTheHomePage();
        }
        var contacts = app.getHibernate().getContactsListHnt();
        var expected = contacts.stream().collect(Collectors.toMap(Contact::id, Contact::address));
        var addr = app.getContact().getAllAddresses();
        Assertions.assertEquals(expected, addr);


    }

    @Test
    public void testAllEmails() {
        if (!app.getContact().isContactPresent()) {
            app.getHibernate().creatingContact(new Contact()
                    .mainFields(Utilities.stringGenerator(10),
                            Utilities.stringGenerator(10),
                            Utilities.stringGenerator(10),
                            Utilities.phoneGenerator(10))
                    .contactWithPhoto(Utilities.getRandomFile("src/test/resources/images"))
                    .withEmail(Utilities.stringGenerator(15))
                    .withEmail2(Utilities.stringGenerator(12)));
            app.getContact().goToTheHomePage();
        }
        var contacts = app.getHibernate().getContactsListHnt();
        var expected = contacts.stream().collect(Collectors.toMap(Contact::id, contact ->
                Stream.of(contact.email(), contact.email2(), contact.email3()).filter(s -> s != null && !"".equals(s)).collect(Collectors.joining("\n"))
        ));
        var emails = app.getContact().getAllEmails();
        Assertions.assertEquals(expected, emails);

    }

}
