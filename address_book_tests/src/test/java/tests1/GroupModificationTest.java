package tests1;

import model.Group;
import org.junit.jupiter.api.Test;

public class GroupModificationTest extends TestBase {
    @Test
    void canGroupModifyGroup() {
        if (!app.getGroups().isGroupPresent()) {
            app.getGroups().creatingGroup(new Group("Name", "Header", "Footer"));
        }
        app.getGroups().modifyGroup(new Group().withName("Modify name"));


    }
}
