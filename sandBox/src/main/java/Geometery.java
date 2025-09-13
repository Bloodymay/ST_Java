import java.awt.*;

public class Geometery {
    public static void main(String[] args) {
        printSquareArea(7);
        printRectangleArea(3, 4);

    }

    private static void printRectangleArea(double sideA, double sideB) {
        System.out.println("Площадь квадрата со стороной " + sideA + " и " + sideB + " = " + RectangleArea(sideA, sideB));
    }

    private static Object RectangleArea(double sideA, double sideB) {
        return (sideA * sideB);
    }

    static void printSquareArea(double side) {
        System.out.println("Площадь квадрата со стороной " + side + " = " + squareArea(side));
    }

    private static double squareArea(double side) {
        return side * side;
    }
}
