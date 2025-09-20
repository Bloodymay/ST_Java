package ru.may.geometry.figure;

public record Square (double side) {//сокращенная запись описания класса
    public Square {
        if (side <= 0) {
            throw new IllegalArgumentException("Side must be a positive number");
        }
    }

    public static void printArea(Square s) {
        System.out.println(String.format("Площадь квадрата со стороной %f = %f", s.side, s.getArea()));
    }


    public static void printPerimeter(Square s) {
        System.out.println(String.format("Периметр квадрата со стороной %f = %f", s.side, s.getPerimeter() ));
    }
    public double getArea() {
        return this.side * this.side;
    }

    public double getPerimeter() {
        return this.side * 4;
    }
}
