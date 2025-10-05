package tests1;

import model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeleteGroups extends TestBase {

    //  @AfterEach
//  public void tearDown() {
//    driver.findElement(By.linkText("Logout")).click();
//    driver.quit();
//  }
    @Test
    public void removeGroups() {
        if (app.getGroups().getCount()==0) {
            app.getGroups().creatingGroup(new Group("group_name", "group_header", "group_footer"));
        }
        int groupCount = app.getGroups().getCount();
        app.getGroups().removeGroup();
        int groupCountAfterRemove = app.getGroups().getCount();
        Assertions.assertEquals(groupCount - 1, groupCountAfterRemove);
    }
    @Test
    public void removeAllGroupsAtOnce() {
        if (app.getGroups().getCount()==0) {
            app.getGroups().creatingGroup(new Group("group_name ", "group_header", "group_footer"));
        }
        app.getGroups().removeAllGroups();
        Assertions.assertEquals(0, app.getGroups().getCount());
    }

}
