package tests1;

import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GroupModificationTest extends TestBase {
    @Test
    void canGroupModifyGroup() { //обеспечение предусловия через DB посредством Hibernate и сравнение списков через hbn
        if (app.getHibernate().getGroupCount() == 0) {
            app.getHibernate().creatingGroup(new Group("", "group_name ", "group_header", "group_footer"));
        }
        var newGroupName =  new Group().withName("Modify name");
        var oldGroups = app.getHibernate().getGroupsListHnt();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.getGroups().modifyGroup(oldGroups.get(index), newGroupName);
        var newGroups = app.getHibernate().getGroupsListHnt();
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
