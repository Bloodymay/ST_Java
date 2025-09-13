package ru.may.geometry.figure;

public class Square {
    public static void printArea(double side) {
        System.out.println(String.format("Площадь квадрата со стороной %f = %f", side, getArea(side)));
    }

    public static double getArea(double side) {
        return side * side;
    }

    public static double getPerimeter(double side) {
        return side * 4;
    }
    public static void printPerimeter(double side) {
        System.out.println(String.format("Периметр квадрата со стороной %f = %f", side, getPerimeter(side)));
    }
}
