package tests1;

import model.Contact;
import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class CreationContact extends TestBase {

    public static List<Contact> contactProvider() {
        var result = new ArrayList<Contact>(List.of(new Contact())); //.mainFields("Mayya", "Matveeva", "улица Пушкина,дом Колотушкина", "89524582455"))
        for (var firstName : List.of("Mary", "Jane", "John", "Jack", "Bob")) {
            for (var lastName : List.of("Hopkins", "Doe", "Wick", "Sparrow", "Dylan")) {
                for (var address : List.of("Yessentuki", "Mineral Waters")) {
                    for (var phone : List.of("89254856854", "254522112456", "empty")) {
                        result.add(new Contact().mainFields(firstName, lastName, address, phone));
                    }
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            result.add(new Contact().mainFields(stringGenerator(4), stringGenerator(10), stringGenerator(20), stringGenerator(10)));
        }
        return result;
    }

    public static List<Contact> negativeContactProvider() {
        var result = new ArrayList<Contact>(List.of(new Contact().mainFields("Mayya'", "Matveeva", "Kislovodsk", "204551248612")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void testCreationContactMainFields(Contact contact) {
        var oldContactList = app.getContact().getContactList();
        app.getContact().contactCreation(contact);
        var newContactList = app.getContact().getContactList();
        Comparator<Contact> compareByID = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContactList.sort(compareByID);
        var expectedList = new ArrayList<>(oldContactList);
        expectedList.add(contact.withID(newContactList.get(newContactList.size() - 1).id()));
        expectedList.sort(compareByID);
        Assertions.assertEquals(newContactList, expectedList);
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void testCreationContactNegativeMainFields(Contact contact) {
        var oldContactList = app.getContact().getContactList();
        app.getContact().contactCreation(contact);
        var newContactList = app.getContact().getContactList();
        Assertions.assertEquals(newContactList, oldContactList);
    }

//    @Test
//    public void testCreationContactWithFiles() {
//        app.getContact().contactCreation(new Contact().contactWithPhoto("Mayya", "Matveeva",
//                "улица Пушкина,дом Колотушкина", "89524582455",
//                "C:\\Users\\Negosu\\Desktop\\3b1a3ae3cb7d6096205432b1505a2147.jpg", "username.com"));
//    }

}
