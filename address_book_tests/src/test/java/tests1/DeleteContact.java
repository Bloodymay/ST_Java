package tests1;

import model.Contact;
import org.junit.jupiter.api.Test;

public class DeleteContact extends TestBase {
    @Test
    public void testDeleteContact() {
        if (!app.getContact().isContactPresent()) {
            app.getContact().contactCreation(new Contact().mainFields("Mayya", "Matveeva", "улица Пушкина,дом Колотушкина", "89524582455"));
        }
        app.getContact().selectContact();
        app.getContact().deleteContact();
        //app.getContact().closeAlert();
    }
}
