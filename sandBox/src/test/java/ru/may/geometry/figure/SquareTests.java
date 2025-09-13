package ru.may.geometry.figure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class SquareTests {
    @Test
    void canGetArea() {
        double result = Square.getArea(25);
        Assertions.assertEquals(625, result);
    }
    @Test
    void canCalculatePerimeter() {
        Assertions.assertEquals(100, Square.getPerimeter(25));
    }
}
