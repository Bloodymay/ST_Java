package ru.may.geometry.figure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {

    @Test
    public void triangleExistence() {
        var triangle = new Triangle(15, 35, 25);
        Assertions.assertTrue(triangle.checkTriangle());
    }

    @Test
    public void canCalculateArea() {
        var triangle = new Triangle(15, 35, 45);
        if (triangle.checkTriangle() == true) {
            double result = triangle.calculateArea();
            Assertions.assertEquals(220, Math.round(result), 2);
        } else {
            Assertions.fail("Triangle does not exist");
        }
    }
    @Test
    public void canCalculatePerimeter() {        var triangle = new Triangle(15, 35, 45);
        if (triangle.checkTriangle() == true) {
            double result = triangle.calculatePerimeter();
            Assertions.assertEquals(95, result);
        } else {
            Assertions.fail("Triangle does not exist");
        }}
}
