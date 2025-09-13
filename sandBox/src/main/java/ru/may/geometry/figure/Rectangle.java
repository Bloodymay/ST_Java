package ru.may.geometry.figure;

public class Rectangle {
    public static void main(String[] args) {

    }

    public static void printRectangleArea(double sideA, double sideB) {
        var text = String.format("Площадь Прямоугольника со сторонами %f и %f = %f", sideA, sideB, RectangleArea(sideA, sideB));
        System.out.println(text);
    }

    private static Object RectangleArea(double sideA, double sideB) {
        return (sideA * sideB);
    }
}
