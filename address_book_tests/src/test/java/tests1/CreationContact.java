package tests1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import common.Utilities;
import model.Contact;
import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


public class CreationContact extends TestBase {

    public static List<Contact> contactProvider() throws IOException {
        var result = new ArrayList<Contact>(); //.mainFields("Mayya", "Matveeva", "улица Пушкина,дом Колотушкина", "89524582455"))
//        for (var firstName : List.of("Mary", "Jane", "John", "Jack", "Bob")) {
//            for (var lastName : List.of("Hopkins", "Doe", "Wick", "Sparrow", "Dylan")) {
//                for (var address : List.of("Yessentuki", "Mineral Waters")) {
//                    for (var phone : List.of("89254856854", "254522112456", "empty")) {
//                        result.add(new Contact().mainFields(firstName, lastName, address, phone));
//                    }
//                }
//            }
//        }
//        for (int i = 0; i < 3; i++) {
//            result.add(new Contact().mainFields(Utilities.stringGenerator(4), Utilities.stringGenerator(10), Utilities.stringGenerator(20), Utilities.stringGenerator(10)));
//        }
        var xml = Files.readString(Paths.get("contacts.xml"));

        var mapper = new XmlMapper();
        var value = mapper.readValue(xml, new TypeReference<List<Contact>>() {
        });
        result.addAll(value);
        return result;
    }

    public static List<Contact> negativeContactProvider() {
        var result = new ArrayList<Contact>(List.of(new Contact().mainFields("Mayya'", "Matveeva", "Kislovodsk", "204551248612").contactWithPhoto(Utilities.getRandomFile("src/test/resources/images"))));
        return result;
    }

    @Test
    public void creationContact() {
        var contact = new Contact().mainFields(Utilities.stringGenerator(4), Utilities.stringGenerator(10), Utilities.stringGenerator(20), Utilities.stringGenerator(10)).contactWithPhoto(Utilities.getRandomFile("src/test/resources/images"));
        app.getContact().contactCreation(contact);
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void testCreationContactMainFields(Contact contact) {
        var oldContactList = app.getHibernate().getContactsListHnt();//var oldContactList = app.getContact().getContactList();
        app.getContact().contactCreation(contact);
        var newContactList = app.getHibernate().getContactsListHnt();//app.getContact().getContactList();
        Comparator<Contact> compareByID = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContactList.sort(compareByID);
        var expectedList = new ArrayList<>(oldContactList);
        expectedList.add(contact.withID(newContactList.get(newContactList.size() - 1).id()).contactWithPhoto(newContactList.get(newContactList.size() - 1).photo()));
        expectedList.sort(compareByID);
        Assertions.assertEquals(newContactList, expectedList);
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void testCreationContactNegativeMainFields(Contact contact) {
        var oldContactList = app.getHibernate().getContactsListHnt();//app.getContact().getContactList();
        app.getContact().contactCreation(contact); //использован метод из contactHelper т.к. напрямую в базу записывается название группы через апостроф и тест становится бесполезным
        var newContactList = app.getHibernate().getContactsListHnt();//app.getContact().getContactList();
        Assertions.assertEquals(newContactList, oldContactList);
    }

    @Test
    public void canCreateContactInGroup() {
        var contact = new Contact()
                .mainFields(Utilities.stringGenerator(4), Utilities.stringGenerator(10)
                        , Utilities.stringGenerator(20), Utilities.stringGenerator(10))
                .contactWithPhoto(Utilities.getRandomFile("src/test/resources/images"));
        if (app.getHibernate().getGroupCount() == 0) {
            app.getHibernate().creatingGroup(new Group("", "group_name ", "group_header", "group_footer"));
        }
        var group = app.getHibernate().getGroupsListHnt().get(0);
        var oldRelated = app.getHibernate().getContactsInGroup(group);
        app.getContact().contactCreationWithGroup(contact, group);
        var newRelated = app.getHibernate().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());

    }

    @Test
    public void canAddContactToGroup() throws SQLException {
        if (app.getHibernate().getGroupCount() == 0) {
            app.getHibernate().creatingGroup(new Group("", "group_name ", "group_header", "group_footer"));
        }
        if (!app.getContact().isContactPresent()) {
            app.getHibernate().creatingContact(new Contact().mainFields(Utilities.stringGenerator(10), Utilities.stringGenerator(15), Utilities.stringGenerator(25), Utilities.phoneGenerator(10)).contactWithPhoto(Utilities.getRandomFile("src/test/resources/images")));
            app.getContact().goToTheHomePage();
        }
        var group = app.getHibernate().getGroupsListHnt().get(0);
        var contactList = app.getHibernate().getContactsListHnt();
        var oldRelated = app.getHibernate().getContactsInGroup(group);
        var rnd = new Random();
        var index = rnd.nextInt(app.getHibernate().getContactsListHnt().size());
        var contact = contactList.get(index);

        if (app.getJdbc().checkCertainContactInGroup(group, contact) == true) {
            index = app.getHibernate().creatingContactAndReturnID(new Contact().mainFields(Utilities.stringGenerator(10),
                            Utilities.stringGenerator(15),
                            Utilities.stringGenerator(25),
                            Utilities.phoneGenerator(10))
                    .contactWithPhoto(Utilities.getRandomFile("src/test/resources/images")));
            contact = app.getHibernate().getContactById(index);
        }
        app.getContact().addContactToGroup(contact, group);
        var newRelated = app.getHibernate().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }

//    @Test
//    public void testCreationContactWithFiles() {
//        app.getContact().contactCreation(new Contact().contactWithPhoto("Mayya", "Matveeva",
//                "улица Пушкина,дом Колотушкина", "89524582455",
//                "C:\\Users\\Negosu\\Desktop\\3b1a3ae3cb7d6096205432b1505a2147.jpg", "username.com"));
//    }

}
