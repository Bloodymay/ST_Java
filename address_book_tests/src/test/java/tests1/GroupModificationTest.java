package tests1;

import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GroupModificationTest extends TestBase {
    @Test
    void canGroupModifyGroup() {
        if (app.getGroups().getCount()==0) {
            app.getGroups().creatingGroup(new Group("", "Name", "Header", "Footer"));
        }
        var newGroupName =  new Group().withName("Modify name");
        var oldGroups = app.getGroups().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.getGroups().modifyGroup(oldGroups.get(index), newGroupName);
        var newGroups = app.getGroups().getList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, newGroupName.withID(oldGroups.get(index).id()));
        Comparator<Group> compareByID = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareByID);
        expectedList.sort(compareByID);
        Assertions.assertEquals(expectedList, newGroups);
    }
}
