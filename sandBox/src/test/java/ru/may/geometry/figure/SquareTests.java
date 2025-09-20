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
        }catch (IllegalArgumentException e){
        }


    }
}
