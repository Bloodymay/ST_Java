package ru.may.geometry.figure;

public class Square {
    public static void printSquareArea(double side) {
        System.out.println("Площадь квадрата со стороной " + side + " = " + squareArea(side));
    }

    private static double squareArea(double side) {
        return side * side;
    }
}
