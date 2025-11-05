package tests1;

import model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {
    @Test
    public void testPhones() {
        var contacts = app.getHibernate().getContactsListHnt();
        var contact = contacts.get(0);
        var phones = app.getContact().getPhones(contact);
        var expected = Stream.of(contact.homePhone(), contact.mobilePhone(), contact.workPhone(), contact.secondary()).filter(s -> s !=null&& ! "".equals(s)).collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, phones);
    }
    @Test
    public void testAllPhones() {
        var contacts = app.getHibernate().getContactsListHnt();
        var expected = contacts.stream().collect(Collectors.toMap(Contact::id, contact ->
            Stream.of(contact.homePhone(), contact.mobilePhone(), contact.workPhone(), contact.secondary()).filter(s -> s !=null&& ! "".equals(s)).collect(Collectors.joining("\n"))
        ));
        var phones = app.getContact().getAllPhones();
        Assertions.assertEquals(expected, phones);

    }
}
