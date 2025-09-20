package ru.may.geometry.figure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class SquareTests {
    @Test
    void canGetArea() {
        var s = new Square(10);
        double result = s.getArea();
        Assertions.assertEquals(100, result);
    }

    @Test
    void canCalculatePerimeter() {
        Assertions.assertEquals(60, new Square(15).getPerimeter());
    }
    @Test
    void cannotCalcSqureWithNegativeSide() {
        try{
        var s = new Square(-20);
            Assertions.fail();
        }catch (IllegalArgumentException e) {
        }

    }
    @Test
    void testEquality() {
        var s1 = new Square(12);
        var s2 = new Square(12);
        Assertions.assertEquals(s1, s2);
    }
    @Test
    void testNonEquality() {
        var s1 = new Square(12);
        var s2 = new Square(13);
        Assertions.assertNotEquals(s1, s2);
    }
    @Test
    void testTrue() {
        var s1 = new Square(12);
        var s2 = new Square(12);
        Assertions.assertTrue(s1.equals(s2));

    }
}
