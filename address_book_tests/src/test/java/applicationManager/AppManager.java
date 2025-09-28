package applicationManager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class AppManager {
    protected WebDriver driver;
    private LoginHelper session;
    private GroupHelper groups;

    public void initialization(String browser) {
        if (driver == null) {
            if (browser.equals("firefox")) {
                driver = new FirefoxDriver();
            } else if (browser.equals("chrome")) {
                driver = new ChromeDriver();
            } else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(2576, 1416));
            getSession().login("admin", "secret");
        }
    }

    public LoginHelper getSession() {
        if (session == null) {
            session = new LoginHelper(this);
        }
        return session;

    }

    public GroupHelper getGroups() {
        if (groups == null) {
            groups = new GroupHelper(this);

        }
        return groups;
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }

    }

}
