package ru.may.geometry.figure;

public record Triangle(double sideA, double sideB, double sideC) {
    public Triangle {
        if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
            throw new IllegalArgumentException("Triangle Sides must be a positive number");
        }
        if (sideA + sideB <= sideC || sideB + sideC <= sideA || sideC + sideA <= sideB){
            throw new IllegalArgumentException("Triangle doesn't exist");
        }
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



