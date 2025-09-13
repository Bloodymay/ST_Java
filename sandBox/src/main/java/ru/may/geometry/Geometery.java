package ru.may.geometry;

import ru.may.geometry.figure.Rectangle;
import ru.may.geometry.figure.Square;

public class Geometery {
    public static void main(String[] args) {
        Square.printArea(new Square(5.0));
        Rectangle.printArea(new Rectangle(3, 4));
        Rectangle.printPerimeter(new Rectangle(16,42));
        Square.printPerimeter(new Square(20));

    }

}
