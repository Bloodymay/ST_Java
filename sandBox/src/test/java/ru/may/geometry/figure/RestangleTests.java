package ru.may.geometry.figure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RestangleTests {
    @Test
    void canCalculateArea(){
       double result = Rectangle.getArea(16,21);
       Assertions.assertEquals(336, result);
    }
    @Test
    void getPerimeter(){
        double result = Rectangle.getPerimeter(15, 45);
        Assertions.assertEquals(120, result);
    }
}
