package tests1;

import model.Group;
import org.junit.jupiter.api.Test;

public class DeleteGroups extends TestBase {

    //  @AfterEach
//  public void tearDown() {
//    driver.findElement(By.linkText("Logout")).click();
//    driver.quit();
//  }
    @Test
    public void removeGroups() {
        if (!app.getGroups().isGroupPresent()) {
            app.getGroups().creatingGroup(new Group("group_name", "group_header", "group_footer"));
        }
        app.getGroups().removeGroup();
    }

}
