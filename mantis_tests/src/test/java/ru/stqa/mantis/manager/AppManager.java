package ru.stqa.mantis.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class AppManager {
    private WebDriver driver;
    private Properties properties;
    private String browser;
    private LoginHelper loginHelper;

    public void initialization(String browser, Properties properties) {
        this.browser = browser;
        this.properties = properties;
    }


    public WebDriver driver() {
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
        }
        return driver;
    }

    public LoginHelper session() {
        if (loginHelper == null) {
            loginHelper = new LoginHelper(this);
        }
        return loginHelper;
    }

}

