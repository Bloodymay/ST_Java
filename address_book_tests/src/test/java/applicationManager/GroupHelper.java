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
        clickElement(By.linkText("groups"));
        selectGroup();
        removeSelectedGroup();
        returnsToGroupPage();
    }

    public void modifyGroup(Group modifiedGroup) {
        openGroupPage();
        selectGroup();
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnsToGroupPage();


    }

    private void initCreationGroup() {
    clickElement(By.name("new"));
    }

    public boolean isGroupPresent() {
        openGroupPage();
        return manager.isElementPresent(By.name("selected[]"));
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

}
