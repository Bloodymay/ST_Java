package tests1;

import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class DeleteGroups extends TestBase {

    //  @AfterEach
//  public void tearDown() {
//    driver.findElement(By.linkText("Logout")).click();
//    driver.quit();
//  }
    @Test
    public void removeGroup() { //обеспечение предусловия через web-интерфейс
        if (app.getGroups().getCount() == 0) {
            app.getGroups().creatingGroup(new Group("", "group_name", "group_header", "group_footer"));
        }
        //int groupCount = app.getGroups().getCount();
        var oldGroups = app.getGroups().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.getGroups().removeGroup(oldGroups.get(index));
        var newGroups = app.getGroups().getList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        //int groupCountAfterRemove = app.getGroups().getCount();
        Assertions.assertEquals(newGroups, expectedList);
    }

    @Test
    public void removeAllGroupsAtOnce() { //обеспечение предусловия через DB посредством Hibernate
        if (app.getHibernate().getGroupCount() == 0) {
            app.getHibernate().creatingGroup(new Group("", "group_name ", "group_header", "group_footer"));
        }
        app.getGroups().removeAllGroups();
        Assertions.assertEquals(0, app.getHibernate().getGroupCount());
    }
    @Test
    public void removeGroupHbn() { //обеспечение предусловия через DB посредством Hibernate
        if (app.getHibernate().getGroupCount() == 0) {
            app.getHibernate().creatingGroup(new Group("", "group_name ", "group_header", "group_footer"));
        }
        var oldGroups = app.getHibernate().getGroupsListHnt();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.getGroups().removeGroup(oldGroups.get(index));
        var newGroups = app.getHibernate().getGroupsListHnt();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Comparator<Group> compareByID = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareByID);
        expectedList.sort(compareByID);
        Assertions.assertEquals(newGroups, expectedList);
    }

}
