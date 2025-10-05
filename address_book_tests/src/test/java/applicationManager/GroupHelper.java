package applicationManager;

import model.Group;
import org.openqa.selenium.By;

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
        waiting(3);
        initCreationGroup();
        //waiting(3);
        fillGroupForm(group);
        //waiting(3);
        submitGroupCreation();
        waiting(3);
        returnsToGroupPage();
        waiting(3);


    }

    private void submitGroupCreation() {
        clickElement(By.name("submit"));
    }

    public void removeGroup() {
        openGroupPage();
        waiting(3);
        clickElement(By.linkText("groups"));
        selectGroup();
        removeSelectedGroup();
        waiting(3);
        returnsToGroupPage();
    }

    public void modifyGroup(Group modifiedGroup) {
        openGroupPage();
        waiting(3);
        selectGroup();
        initGroupModification();
        waiting(3);
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        waiting(3);
        returnsToGroupPage();


    }

    private void initCreationGroup() {
    clickElement(By.name("new"));
    }


    private void removeSelectedGroup() {
        clickElement(By.name("delete"));
    }


    private void returnsToGroupPage() {
        clickElement(By.linkText("group page"));
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
}
