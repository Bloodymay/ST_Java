package tests1;

import applicationManager.AppManager;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;

public class TestBase {
    protected static AppManager app;

    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new AppManager();
        }
        app.initialization(System.getProperty("browser", "firefox"));//Добавлен выбор браузера


    }
    public static String stringGenerator(int length) {
        var random = new Random();
        var result = "";
        for (int i = 0; i < length; i++) {
            result=result + (char) ('a' + random.nextInt(26));
        }
//        if (length<20){
//            result = result+'\'';
//        }
        return result;

    }

}
