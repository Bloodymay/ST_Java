package tests1;

import model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
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

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void testCreationContactMainFields(Contact contact) {
        int initialQuantity = app.getContact().contactCounter();
        app.getContact().contactCreation(contact);
        int newQuantity = app.getContact().contactCounter();
        Assertions.assertEquals(initialQuantity + 1, newQuantity);
    }

//    @Test
//    public void testCreationContactWithFiles() {
//        app.getContact().contactCreation(new Contact().contactWithPhoto("Mayya", "Matveeva",
//                "улица Пушкина,дом Колотушкина", "89524582455",
//                "C:\\Users\\Negosu\\Desktop\\3b1a3ae3cb7d6096205432b1505a2147.jpg", "username.com"));
//    }

}
