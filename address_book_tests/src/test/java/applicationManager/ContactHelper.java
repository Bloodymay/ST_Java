package applicationManager;

import model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class ContactHelper extends HelperBase {
    public ContactHelper(AppManager manager) {
        super(manager);
    }

    public void contactCreation(Contact contact) {
        initCreationContact();
        //waiting(5);
//        if (contact.getPhoto() != null) {
//            fillContactFormWithFile(contact);
//            waiting(5);
//            submitCreateContact();
//            waiting(5);
//            returnToHomePage();
//
//        } else
//
        fillContactForm(contact);
        submitCreateContact();
        returnToHomePage();
    }

    public void initCreationContact() {
        clickElement(By.linkText("add new"));
    }

    public void fillContactForm(Contact contact) {
        typeTextInContact(By.name("firstname"), contact.firstName());
        //typeTextInContact(By.name("middlename"), contact.middleName());
        typeTextInContact(By.name("lastname"), contact.lastName());
        //typeTextInContact(By.name("nickname"), contact.nickName());
        //typeTextInContact(By.name("photo"), contact.photo());
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

    public void fillContactFormWithFile(Contact contact) {
        typeTextInContact(By.name("firstname"), contact.firstName());
        typeTextInContact(By.name("lastname"), contact.lastName());
        typeTextInContact(By.name("address"), contact.address());
        typeTextInContact(By.name("email"), contact.email());
        addFiles(By.name("photo"), contact.photo());
        typeTextInContact(By.name("homepage"), contact.homepage());
    }

    public void addFiles(By locator, String contact) {
        manager.driver.findElement(locator).sendKeys(contact);
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
            manager.driver.get("http://localhost/addressbook");
        }
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public void initModifyContact() {
        goToTheHomePage();
        clickElement(By.xpath("/html/body/div[1]/div[4]/form[2]/table/tbody/tr[2]/td[8]/a/img")); ////*[@id="maintable"]/tbody/tr[2]/td[8]/a/img
    }

    public void contactModify(Contact contact) {
        initModifyContact();
        fillContactForm(contact);
        submitModifyContact();
    }
    public void deleteOneContact() {
        selectContact();
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

    public void selectContact() {
        clickElement(By.name("selected[]"));
    }

    public void deleteContact() {
        clickElement(By.name("delete"));
    }


}
