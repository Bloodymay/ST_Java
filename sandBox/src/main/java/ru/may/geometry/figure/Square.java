package ru.may.geometry.figure;

public class Square {
    public static void printSquareArea(double side) {
        System.out.println(String.format("Площадь квадрата со стороной %f = %f", side, squareArea(side)));
    }

    private static double squareArea(double side) {
        return side * side;
    }
}
