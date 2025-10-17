package common;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

public class Utilities {
    public static String stringGenerator(int length) {
        var random = new Random();
        var result = "";
        for (int i = 0; i < length; i++) {
            result=result + (char) ('a' + random.nextInt(26));
        }

        return result;

    }

    public static String phoneGenerator(int length) {
        var random = new Random();
        var result = "";
        for (int i = 0; i < length; i++) {
            result=result + random.nextInt(9);

        }
        return result;
    }

    public static String getRandomFile(String dirPath){
        var fileNames = new File(dirPath).list();
        var random = new Random();
        var index = random.nextInt(fileNames.length);
        return Paths.get(dirPath, fileNames[index]).toString();
    }
}
