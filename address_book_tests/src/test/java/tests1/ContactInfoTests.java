package tests1;

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
}
