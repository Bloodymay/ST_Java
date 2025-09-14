package ru.may.geometry;

import ru.may.geometry.figure.Rectangle;
import ru.may.geometry.figure.Square;
import ru.may.geometry.figure.Triangle;

public class Geometery {
    public static void main(String[] args) {
        Square.printArea(new Square(5.0));
        Rectangle.printArea(new Rectangle(3, 4));
        Rectangle.printPerimeter(new Rectangle(16,42));
        Square.printPerimeter(new Square(20));
        Triangle.printPerimeter(new Triangle(5,5,32));
        Triangle.printArea(new Triangle(15,35,45));
    }

}
