package ru.may.geometry.figure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class SquareTests {
    @Test
    void canGetArea() {
        var s = new Square(15);
        double result = s.getArea();
        Assertions.assertEquals(225, result);
        }
    @Test
    void canCalculatePerimeter() {
        Assertions.assertEquals(60, new Square(15).getPerimeter());
    }
}
