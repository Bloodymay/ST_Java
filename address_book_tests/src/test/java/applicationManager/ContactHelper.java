package applicationManager;

import model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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

    public void initCreationContact() {
        clickElement(By.linkText("add new"));
    }

    public void fillContactForm(Contact contact) {
        typeTextInContact(By.name("firstname"), contact.firstName());
        typeTextInContact(By.name("lastname"), contact.lastName());
        typeTextInContact(By.name("address"), contact.address());
        typeTextInContact(By.name("email"), contact.email());
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

    public void closeAlert() {
        manager.driver.switchTo().alert().accept();
    }

}
