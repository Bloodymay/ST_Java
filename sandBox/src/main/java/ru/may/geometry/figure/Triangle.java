package ru.may.geometry.figure;

import java.util.Objects;

public record Triangle(double sideA, double sideB, double sideC) {
    public Triangle {
        if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
            throw new IllegalArgumentException("Triangle Sides must be a positive number");
        }
        if (sideA + sideB <= sideC || sideB + sideC <= sideA || sideC + sideA <= sideB) {
            throw new IllegalArgumentException("Triangle doesn't exist");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(sideA, triangle.sideA) == 0 && Double.compare(sideB, triangle.sideB) == 0 && Double.compare(sideC, triangle.sideC) == 0)
                || (Double.compare(sideA, triangle.sideB) == 0 && Double.compare(sideB, triangle.sideA) == 0 && Double.compare(sideC, triangle.sideC) == 0)
                || (Double.compare(sideA, triangle.sideA) == 0 && Double.compare(sideB, triangle.sideC) == 0 && Double.compare(sideC, triangle.sideB) == 0)
                || (Double.compare(sideA, triangle.sideC) == 0 && Double.compare(sideB, triangle.sideB) == 0 && Double.compare(sideC, triangle.sideA) == 0)
                || (Double.compare(sideA, triangle.sideB) == 0 && Double.compare(sideB, triangle.sideC) == 0 && Double.compare(sideC, triangle.sideA) == 0)
                || (Double.compare(sideA, triangle.sideC) == 0 && Double.compare(sideB, triangle.sideA) == 0 && Double.compare(sideC, triangle.sideB) == 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideA, sideB, sideC);
    }

    public double calculatePerimeter() {
        return (this.sideA + this.sideB + this.sideC);
    }

    public double calculateArea() {
        double halfPerimeter = this.calculatePerimeter() / 2;
        return Math.sqrt(halfPerimeter * ((halfPerimeter - this.sideA) * (halfPerimeter - this.sideB) * (halfPerimeter - this.sideC)));
    }

    public static void printPerimeter(Triangle triangle) {

        System.out.println(String.format("Triangle perimeter with sides: %f, %f and %f = %f", triangle.sideA, triangle.sideB, triangle.sideC, triangle.calculatePerimeter()));

    }

    public static void printArea(Triangle triangle) {

        System.out.println(String.format("Triangle area with sides: %f, %f and %f = %f", triangle.sideA, triangle.sideB, triangle.sideC, triangle.calculateArea()));

    }
}



