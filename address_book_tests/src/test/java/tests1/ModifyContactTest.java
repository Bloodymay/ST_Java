package tests1;

import model.Contact;
import org.junit.jupiter.api.Test;

public class ModifyContactTest extends TestBase {
    @Test
    public void testModifyContact() {
        if (!app.getContact().isContactPresent()) {
            app.getContact().contactCreation(new Contact().mainFields("123", "one, two,three", "lksajdjhshsghsdghxzgdh", "244141124541"));
        }
        app.getContact().contactModify(new Contact().mainFields("Ivan", "Ivanov", "hello world", "9999999999"));
    }
}
