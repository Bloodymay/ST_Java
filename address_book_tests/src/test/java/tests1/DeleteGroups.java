package tests1;

import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeleteGroups extends TestBase {

    //  @AfterEach
//  public void tearDown() {
//    driver.findElement(By.linkText("Logout")).click();
//    driver.quit();
//  }
    @Test
    public void removeGroup() {
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
    public void removeAllGroupsAtOnce() {
        if (app.getGroups().getCount() == 0) {
            app.getGroups().creatingGroup(new Group("", "group_name ", "group_header", "group_footer"));
        }
        app.getGroups().removeAllGroups();
        Assertions.assertEquals(0, app.getGroups().getCount());
    }

}
