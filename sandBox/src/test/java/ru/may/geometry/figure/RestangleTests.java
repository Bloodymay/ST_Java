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
    @Test
    void cannotCalcRectWithNegativeSideA() {
        try {
            new Rectangle(-20,30);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
        }
    }

        @Test
        void cannotCalcRectWithNegativeSideB() {
            try {
                new Rectangle(30,-20);
                Assertions.fail();
            } catch (IllegalArgumentException e) {
            }
        }
    @Test
    void testEquality() {
        var r1 = new Rectangle(12, 6);
        var r2 = new Rectangle(12, 6);
        Assertions.assertEquals(r1, r2);
    }
    @Test
    void testEquality2() {
        var r1 = new Rectangle(12, 6);
        var r2 = new Rectangle(6, 12);
        Assertions.assertEquals(r1, r2);
    }

        }
