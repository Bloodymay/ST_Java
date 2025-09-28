package tests1;

import model.Group;
import org.junit.jupiter.api.Test;

public class CreationGroupTests extends TestBase {


    @Test
    public void canCreateGroup() {

        app.getGroups().creatingGroup(new Group("name", "header", "footer"));

    }

    @Test
    public void canCreateGroupWithNameOnly() {
        var emptyGroup = new Group();
        var groupWithName = new Group().withName("new_name");
        app.getGroups().creatingGroup(groupWithName);
    }

    @Test
    public void canCreateGroupWithHeaderOnly() {
        app.getGroups().creatingGroup(new Group().withHeader("SomeHeader"));
    }

    @Test
    public void canCreateGroupWithFooterOnly() {
        app.getGroups().creatingGroup(new Group().withFooter("SomeFooter"));
    }

    @Test
    public void canCreateEmptyGroup() {
        app.getGroups().creatingGroup(new Group());
    }

}
