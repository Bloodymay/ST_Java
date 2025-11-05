package applicationManager;

import model.Contact;
import model.Group;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class ContactHelper extends HelperBase {
    public ContactHelper(AppManager manager) {
        super(manager);
    }

    public void contactCreation(Contact contact) {
        initCreationContact();
        fillContactForm(contact);
        submitCreateContact();
        returnToHomePage();
    }

    public void contactCreationWithGroup(Contact contact, Group group) {
        initCreationContact();
        fillContactForm(contact);
        selectGroup(group);
        submitCreateContact();
        returnToHomePage();
    }

    private void selectGroup(Group group) {
        //goToTheHomePage();
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());

    }

    public void selectGroupOnHomepage(Group group) {
        //goToTheHomePage();
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());

    }

    public void bottomSelectGroupOnHomepage(Group group) {
        //goToTheHomePage();
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());

    }


    public void initCreationContact() {
        clickElement(By.linkText("add new"));
    }

    public void fillContactForm(Contact contact) {
        typeTextInContact(By.name("firstname"), contact.firstName());
        //typeTextInContact(By.name("middlename"), contact.middleName());
        typeTextInContact(By.name("lastname"), contact.lastName());
        //typeTextInContact(By.name("nickname"), contact.nickName());
        attach(By.name("photo"), contact.photo());
//        typeTextInContact(By.name("title"), contact.position());
//        typeTextInContact(By.name("company"), contact.company());
        typeTextInContact(By.name("address"), contact.address());
//        typeTextInContact(By.name("home"), contact.homePhone());
        typeTextInContact(By.name("mobile"), contact.mobilePhone());
//        typeTextInContact(By.name("work"), contact.workPhone());
//        typeTextInContact(By.name("fax"), contact.fax());
//        typeTextInContact(By.name("email"), contact.email());
//        typeTextInContact(By.name("email2"), contact.email2());
//        typeTextInContact(By.name("email3"), contact.email3());
//        typeTextInContact(By.name("homepage"), contact.homepage());
        //typeTextInContact(By.name("bday"), String.valueOf(contact.birthDay()));
        //selectDropdownOption(String.valueOf(By.tagName("option")));
        // typeTextInContact(By.name("bmonth"), contact.birthMonth());
        //typeTextInContact(By.name("byear"), contact.birthYear());
        //typeTextInContact(By.name("aday"), String.valueOf(contact.anniversaryDay()));
        //typeTextInContact(By.name("amonth"), contact.anniversaryMonth());
        //typeTextInContact(By.name("aYear"), contact.anniversaryYear());
        //typeTextInContact(By.name("group"), contact.group());

    }

    public void selectDropdownOption(String dropdownName) {
        clickElement(By.name(dropdownName));
        WebElement dropdown = manager.driver.findElement(By.name(dropdownName));
        Select select = new Select(dropdown);
        select.selectByValue(String.valueOf(randomDay()));

    }

    public Integer randomDay() {
        Random rand = new Random();
        return rand.nextInt(31) + 1;
    }
//    public String randomMonth() {
//        List months = manager.driver.findElements(By.name("month"));
//        return rand.nextInt(12) + 1;
//    }


    public void attach(By locator, String file) {
        manager.driver.findElement(locator).sendKeys(Paths.get(file).toAbsolutePath().toString());
    }

    public void waiting(int seconds) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectAllContacts() {
        if (!manager.isElementPresent(By.linkText("home"))) {
            manager.driver.get(manager.properties.getProperty("web.baseUrl"));
        }
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public void initModifyContact(Contact contact) {
        goToTheHomePage();
        clickElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", contact.id())));

    }

    public void contactModify(Contact contact, Contact modifiedContact) {
        initModifyContact(contact);
        fillContactForm(modifiedContact);
        submitModifyContact();
    }

    public void deleteOneContact(Contact contact) {
        selectContact(contact);
        deleteContact();
        returnToHomePage();
    }

    public void deleteAllContacts() {
        selectAllContacts();
        deleteContact();
        returnToHomePage();
    }

    public int contactCounter() {
        return manager.driver.findElements(By.name("selected[]")).size();

    }


    public void submitModifyContact() {
        clickElement(By.name("update"));
    }

    public void goToTheHomePage() {
        clickElement(By.linkText("home"));
    }

    public boolean isContactPresent() {
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void submitCreateContact() {
        clickElement(By.name("submit"));
    }

    public void returnToHomePage() {
        clickElement(By.linkText("home page"));
    }

    public void selectContact(Contact contact) {
        clickElement(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    public void deleteContact() {
        clickElement(By.name("delete"));
    }

    public void initDeleteContactFromGroup() {
        clickElement(By.name("remove"));
    }

    public void deleteContactFromGroup(Group group, Contact contact) {
        bottomSelectGroupOnHomepage(group);
        selectContact(contact);
        initDeleteContactFromGroup();
    }

    public void addContactToGroup(Contact contact, Group group) {
        goToTheHomePage();
        selectContact(contact);
        selectGroupOnHomepage(group);
        clickElement(By.name("add"));
    }


    //    public List<Contact> getContactList() {
//        var contacts = new ArrayList<Contact>();
//        var spans = manager.driver.findElements(By.cssSelector("tr"));
//
//            for (var _ : spans) {
//                var attr =  manager.driver.findElements(By.cssSelector("td"));
//                for (var attribute : attr) {
//                    var name = attr.get(2).getText();
//                    var lastName = attr.get(1).getText();
//                    var checkbox = attribute.findElement(By.name("selected[]"));
//                    var id = checkbox.getAttribute("value");
//                    contacts.add(new Contact().withID(id).
//                                                withName(name).
//                                                withLastName(lastName));
//                }
//            }
//
//        return contacts;
//    }
    public List<Contact> getContactList() {
        var contacts = new ArrayList<Contact>();
        var rows = manager.driver.findElements(By.cssSelector("tr"));

        for (var row : rows) {


            var cells = row.findElements(By.cssSelector("td"));

            if (cells.size() < 3) continue;

            var name = cells.get(2).getText();
            var lastName = cells.get(1).getText();
            var address = cells.get(3).getText();
            var mobilePhone = cells.get(5).getText();


            var checkbox = row.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");

            contacts.add(new Contact().withID(id)
                    .mainFields(name, lastName, address, mobilePhone));

        }

        return contacts;
    }

    public void selectGroupAllOnHomepage() {
        goToTheHomePage();
        new Select(manager.driver.findElement(By.name("group"))).selectByContainsVisibleText("[all]");

    }

    public String getPhones(Contact contact) {
        return manager.driver.findElement(By.xpath(String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();

    }

    public Map<String, String> getAllPhones() {
        var result = new HashMap<String, String>();
       var rows = manager.driver.findElements(By.name("entry"));
       for (var row : rows) {
           var id = row.findElement(By.tagName("input")).getAttribute("id");
           var phones = row.findElements(By.tagName("td")).get(5).getText();
           result.put(id, phones);
       }
       return result;
    }
}
