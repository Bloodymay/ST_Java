package ru.may.geometry.figure;

public class Rectangle {
    public static void main(String[] args) {

    }

    public static void printArea(double sideA, double sideB) {
        var text = String.format("Площадь Прямоугольника со сторонами %f и %f = %f", sideA, sideB, getArea(sideA, sideB));
        System.out.println(text);
    }

    public static double getArea(double sideA, double sideB) {
        return (sideA * sideB);
    }
    public  static double getPerimeter(double sideA, double sideB) {
        return ((sideA + sideB) * 2);
    }
    public static void printPerimeter(double sideA, double sideB) {
        var text = String.format("Периметр прямоугольника со сторонами %f и %f равен: %f", sideA, sideB, getPerimeter(17,42));
        System.out.println(text);
    }

}
