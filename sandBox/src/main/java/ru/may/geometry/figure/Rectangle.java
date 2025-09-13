package ru.may.geometry.figure;

public class Rectangle {
    public static void main(String[] args) {

    }

    public static void printRectangleArea(double sideA, double sideB) {
        System.out.println("Площадь квадрата со стороной " + sideA + " и " + sideB + " = " + RectangleArea(sideA, sideB));
    }

    private static Object RectangleArea(double sideA, double sideB) {
        return (sideA * sideB);
    }
}
