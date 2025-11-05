package applicationManager;

import model.Group;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;


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

    public void removeGroup(Group group) {
        openGroupPage();
        waiting(1);
        clickElement(By.linkText("groups"));
        selectGroup(group);
        removeSelectedGroups();
        waiting(1);
        returnsToGroupPage();
    }

    public void modifyGroup(Group group, Group modifiedGroup) {
        openGroupPage();
        waiting(1);
        selectGroup(group);
        initGroupModification();
        waiting(1);
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        waiting(3);
        returnsToGroupPage();
        waiting(5);


    }

    private void initCreationGroup() {
        clickElement(By.name("new"));
    }

    private void removeSelectedGroups() {
        clickElement(By.name("delete"));
    }


    private void returnsToGroupPage() {
        if (!manager.isElementPresent(By.name("delete"))) {
            manager.driver.get(manager.properties.getProperty("web.groupsUrl"));
        } else {
            clickElement(By.linkText("group page"));
        }
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

    private void selectGroup(Group group) {
        clickElement(By.cssSelector(String.format("input[value='%s']", group.id())));

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
        //        for (var checkbox : checkboxes) {
        //            checkbox.click(); вариант с циклом
        //        }
        manager.driver.findElements(By.name("selected[]")).forEach(WebElement::click);
    }

    public void openPage() throws InterruptedException {
        if (!manager.isElementPresent(By.name("delete"))) {
            manager.driver.get(manager.properties.getProperty("web.baseUrl")+"/group.php");//group.php
            Thread.sleep(15000);
        }

    }

    public List<Group> getList() {
        openGroupPage();
        //var groups = new ArrayList<Group>();
        var spans = manager.driver.findElements(By.cssSelector("span.group"));//тег span используется для группровки небольших фрагментов текста или других встроенных элементов
        return spans.stream().map(span ->             { //через трансформатор
            var name = span.getText();
            var checkbox = span.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            return new Group().withID((id)).withName(name); }).collect(Collectors.toList());
//        for (var span : spans) { Через цикл
//            var name = span.getText();
//            var checkbox = span.findElement(By.name("selected[]"));
//            var id = checkbox.getAttribute("value");
//            groups.add(new Group().withID((id)).withName(name));
//
//        }
//        return groups;
    }
}
