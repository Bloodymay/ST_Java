package ru.may.geometry;

import ru.may.geometry.figure.Rectangle;
import ru.may.geometry.figure.Square;
import ru.may.geometry.figure.Triangle;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometery {
    public static void main(String[] args) {
        Supplier<Square> randomSquare = () -> new Square(new Random().nextDouble(100.0));
        var squares = Stream.generate(randomSquare).limit(10);
//        for (Square square : squares) {
//            Square.printArea(square);
//        }

        squares.peek(Square::printArea).forEach(Square::printPerimeter);

//        Triangle.printArea(new Triangle(25,35,45));
//        Square.printArea(new Square(5.0));
//        Rectangle.printArea(new Rectangle(3, 4));
//        Rectangle.printPerimeter(new Rectangle(16,42));
//        Square.printPerimeter(new Square(20));
//        Triangle.printPerimeter(new Triangle(5,5,32));

    }

}
