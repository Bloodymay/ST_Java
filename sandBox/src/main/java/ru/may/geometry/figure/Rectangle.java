package ru.may.geometry.figure;

/*public class Rectangle {

    private double sideA;
    private double sideB;
    public Rectangle(double sideA, double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
        if ((this.sideA <= 0)||(this.sideB <= 0)) {
            throw new IllegalArgumentException("sideA and sideB must be greater than 0");
        }
    }*/

import java.util.Objects;

public record Rectangle(double sideA, double sideB) {
    public Rectangle{
        if ((sideA <= 0)||(sideB <= 0)) {
            throw new IllegalArgumentException("sideA and sideB must be greater than 0");
        }

    }


    public double getArea() {
        return this.sideA * this.sideB;
    }
    public double getPerimeter() {
        return (this.sideA + this.sideB) * 2;
    }

    public static void main(String[] args) {

    }

    public static void printArea(Rectangle a ) {
        var text = String.format("Площадь Прямоугольника со сторонами %f и %f = %f",a.sideA, a.sideB, a.getArea());
        System.out.println(text);
    }

    public static void printPerimeter(Rectangle a ) {
        var text = String.format("Периметр прямоугольника со сторонами %f и %f равен: %f", a.sideA, a.sideB, a.getPerimeter());
        System.out.println(text);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return (Double.compare(sideA, rectangle.sideA) == 0 && Double.compare(sideB, rectangle.sideB) == 0)
                || (Double.compare(sideA, rectangle.sideB) == 0 && Double.compare(sideB, rectangle.sideA) == 0) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideA, sideB);
    }
}
