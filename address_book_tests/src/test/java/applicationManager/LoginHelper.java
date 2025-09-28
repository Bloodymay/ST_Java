package applicationManager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {


    public LoginHelper(AppManager manager) {
        super(manager);
    }

    ;

    void login(String username, String pwd) {
        typeText(By.name("user"), username);
        typeText(By.name("pass"), pwd);
        clickElement(By.xpath("//input[@value=\'Login\']"));
    }
}
