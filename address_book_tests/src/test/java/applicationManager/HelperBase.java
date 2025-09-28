package applicationManager;

import org.openqa.selenium.By;

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

    protected void clickElement(By locator) {
        manager.driver.findElement(locator).click();
    }
}
