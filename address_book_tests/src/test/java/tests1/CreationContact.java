package tests1;

import model.Contact;
import org.junit.jupiter.api.Test;


public class CreationContact extends TestBase {
    @Test
    public void testCreationContactMainFields() {
    app.getContact().contactCreation(new Contact().mainFields("Mayya", "Matveeva","улица Пушкина,дом Колотушкина", "89524582455"));

    }
}
