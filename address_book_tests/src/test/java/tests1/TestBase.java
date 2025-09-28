package tests1;

import applicationManager.AppManager;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {
    protected static AppManager app;

    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new AppManager();
        }
        app.initialization(System.getProperty("browser", "firefox"));


    }

}
