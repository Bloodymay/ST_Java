package common;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utilities {
//    public static String stringGenerator(int length) {
//        var random = new Random();
//        var result = "";
//        for (int i = 0; i < length; i++) {
//            result=result + (char) ('a' + random.nextInt(25));
//        }
//
//        return result;
//
//    }
    public static String stringGenerator(int length) {
        var random = new Random();
        Supplier<Integer> randomNumbers = () -> random.nextInt(26);
        var result = Stream.generate(randomNumbers)
                .limit(length)
                .map(i -> 'a' + i)
                .map(Character::toString)
                .collect(Collectors.joining());
        return result;
    }

    public static String phoneGenerator(int length) {
        var random = new Random();
        Supplier<Integer> randomNumbers = () -> random.nextInt(9);
        var result = Stream.generate(randomNumbers).limit(length).map(Object::toString).collect(Collectors.joining());
        return result;
    }

    public static String getRandomFile(String dirPath){
        var fileNames = new File(dirPath).list();
        var random = new Random();
        assert fileNames != null;
        var index = random.nextInt(fileNames.length);
        return Paths.get(dirPath, fileNames[index]).toString();
    }
}
