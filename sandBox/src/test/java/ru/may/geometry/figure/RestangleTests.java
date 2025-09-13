package ru.may.geometry.figure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RestangleTests {
    @Test
    void canCalculateArea(){
        var s = new Rectangle(22, 55);
        double result = s.getArea();
        Assertions.assertEquals(1210, result);
    }
    @Test
    void getPerimeter(){
        var s = new Rectangle(15, 45);
        double result = s.getPerimeter();
        Assertions.assertEquals(120, result);
    }
}
