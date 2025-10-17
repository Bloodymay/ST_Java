package applicationManager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;


public class AppManager {
    protected WebDriver driver;
    private LoginHelper session;
    private GroupHelper groups;
    private ContactHelper contacts;
    public Properties properties;

    public void initialization(String browser, Properties properties) {
        this.properties = properties;
        if (driver == null) {
            if (browser.equals("firefox")) {
                driver = new FirefoxDriver();
            } else if (browser.equals("chrome")) {
                driver = new ChromeDriver();
            } else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);//Добавлен выбор браузера
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseUrl"));
            driver.manage().window().setSize(new Dimension(2576, 1416));
            getSession().login(properties.getProperty("web.username"), properties.getProperty("web.pwd"));
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
    public ContactHelper getContact() {
        if (contacts == null) {
            contacts = new ContactHelper(this);
        }
        return contacts;
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
