package applicationManager;

import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class HelperBase {
    protected final AppManager manager;

    public HelperBase(AppManager manager) {
        this.manager = manager;
    }

    protected void typeText(By locator, String group) {
        clickElement(locator);
        manager.driver.findElement(locator).clear();
        manager.driver.findElement(locator).sendKeys(group);
    }
    protected void typeTextInContact(By locator, String contact) {
        clickElement(locator);
        manager.driver.findElement(locator).clear();
        manager.driver.findElement(locator).sendKeys(contact);

    }

    protected void clickElement(By locator) {
        manager.driver.findElement(locator).click();
    }
    protected void waiting(int seconds) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
