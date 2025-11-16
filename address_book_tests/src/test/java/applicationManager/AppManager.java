package applicationManager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;


public class AppManager {
    protected WebDriver driver;
    private LoginHelper session;
    private GroupHelper groups;
    private ContactHelper contacts;
    public Properties properties;
    private JdbcHelper jdbc;
    private HibernateHelper hibernate;

    public void initialization(String browser, Properties properties) throws MalformedURLException {
        this.properties = properties;
        if (driver == null) {
            var url = properties.getProperty("seleniumServer");
            if (browser.equals("firefox")) {
                if (url != null) {
                    driver = new RemoteWebDriver(new URL(url), new FirefoxOptions());
                } else {
                    driver = new FirefoxDriver();
                }
            } else if (browser.equals("chrome")) {
                if (url != null) {
                    driver = new RemoteWebDriver(new URL(url), new ChromeOptions());
                } else {
                    driver = new ChromeDriver();
                }
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

    public JdbcHelper getJdbc() {
        if (jdbc == null) {
            jdbc = new JdbcHelper(this);

        }
        return jdbc;
    }

    public HibernateHelper getHibernate() {
        if (hibernate == null) {
            hibernate = new HibernateHelper(this);

        }
        return hibernate;
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
