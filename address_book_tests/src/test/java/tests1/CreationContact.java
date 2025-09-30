package tests1;

import model.Contact;
import org.junit.jupiter.api.Test;

import java.io.File;


public class CreationContact extends TestBase {
    @Test
    public void testCreationContactMainFields() {
        app.getContact().contactCreation(new Contact().mainFields("Mayya", "Matveeva", "улица Пушкина,дом Колотушкина", "89524582455"));
    }

    @Test
    public void testCreationContactWithFiles() {
        app.getContact().contactCreation(new Contact().contactWithPhoto("Mayya", "Matveeva",
                "улица Пушкина,дом Колотушкина", "89524582455",
                "C:\\Users\\Negosu\\Desktop\\3b1a3ae3cb7d6096205432b1505a2147.jpg", "username.com"));
    }

}
