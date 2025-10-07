package applicationManager;

import model.Group;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GroupHelper extends HelperBase {

    public GroupHelper(AppManager manager) {
        super(manager);
    }

    ;

    public void openGroupPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            clickElement(By.linkText("groups"));
        }
    }

    public void creatingGroup(Group group) {
        openGroupPage();
        initCreationGroup();
        fillGroupForm(group);
        submitGroupCreation();
        returnsToGroupPage();
    }

    private void submitGroupCreation() {
        clickElement(By.name("submit"));
    }

    public void removeGroup() {
        openGroupPage();
        waiting(1);
        clickElement(By.linkText("groups"));
        selectGroup();
        removeSelectedGroups();
        waiting(1);
        returnsToGroupPage();
    }

    public void modifyGroup(Group modifiedGroup) {
        openGroupPage();
        waiting(1);
        selectGroup();
        initGroupModification();
        waiting(1);
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        waiting(1);
        returnsToGroupPage();


    }

    private void initCreationGroup() {
    clickElement(By.name("new"));
    }


    private void removeSelectedGroups() {
        clickElement(By.name("delete"));
    }


    private void returnsToGroupPage() {
        if(!manager.isElementPresent(By.name("delete"))){
            manager.driver.get("http://localhost/addressbook/group.php");
       }
        else{
        clickElement(By.linkText("group page"));}
    }

    private void submitGroupModification() {
        clickElement(By.name("update"));

    }

    private void fillGroupForm(Group group) {
        typeText(By.name("group_name"), group.name());
        typeText(By.name("group_header"), group.header());
        typeText(By.name("group_footer"), group.footer());

    }

    private void initGroupModification() {
        clickElement(By.name("edit"));

    }

    private void selectGroup() {
        clickElement(By.name("selected[]"));

    }

    public int getCount() {
        openGroupPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllGroups() {
        openGroupPage();
        selectAllGroups();
        removeSelectedGroups();

    }

    private void selectAllGroups() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }
    public void openPage() throws InterruptedException {
        if(!manager.isElementPresent(By.name("delete"))){
            manager.driver.get("http://localhost/addressbook/group.php");
            Thread.sleep(15000);}

    }
}
