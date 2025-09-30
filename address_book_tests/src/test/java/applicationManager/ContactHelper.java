package applicationManager;

import model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends HelperBase {
    public ContactHelper(AppManager manager) {
        super(manager);
    }
    public void contactCreation(Contact contact) {
        initCreationContact();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }
public void initCreationContact(){
    clickElement(By.linkText("add new"));
}
public void fillContactForm(Contact contact) {
        typeTextInContact(By.name("firstname"), contact.firstName());
        typeTextInContact(By.name("lastname"), contact.lastName());
        typeTextInContact(By.name("address"), contact.address());
        typeTextInContact(By.name("email"), contact.email());
}
public void submitContactCreation() {
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
