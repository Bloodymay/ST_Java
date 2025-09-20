package ru.may.geometry.figure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {


    @Test
    public void canCalculateArea() {
        var triangle = new Triangle(15, 35, 45);
        double result = triangle.calculateArea();
        Assertions.assertEquals(220, Math.round(result), 2);
    }

    @Test
    public void canCalculatePerimeter() {
        var triangle = new Triangle(15, 35, 45);

        double result = triangle.calculatePerimeter();
        Assertions.assertEquals(95, result);

    }

    @Test
    public void canExist() {
        try {
            new Triangle(5, 35, 45);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
        }
    }

    @Test
    public void checkFirstSide() {
        try {
            new Triangle(-10, 35, 45);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {

        }
    }

    @Test
    public void checkSecondSide() {
        try {
            new Triangle(25, -35, 45);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {

        }

    }

    @Test
    public void checkThirdSide() {
        try {
            new Triangle(25, 35, -45);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
        }


    }

}

