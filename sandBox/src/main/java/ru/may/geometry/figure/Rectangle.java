package ru.may.geometry.figure;

public class Rectangle {

    private double sideA;
    private double sideB;
    public Rectangle(double sideA, double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
        if ((this.sideA <= 0)||(this.sideB <= 0)) {
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

}
