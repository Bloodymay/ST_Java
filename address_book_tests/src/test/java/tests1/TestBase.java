package tests1;

import applicationManager.AppManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class TestBase {
    protected static AppManager app;

    @BeforeEach
    public void setUp() throws IOException {
        if (app == null) {
            var properties = new Properties();
            properties.load(new FileReader(System.getProperty("target", "local.properties")));
            app = new AppManager();
            app.initialization(System.getProperty("browser", "firefox"), properties);//Добавлен выбор браузера
        }





    }
    @AfterEach
    void checkDatabaseConsistency() throws SQLException {
        app.getJdbc().checkConsistensy();
    }
}
