package ru.may.geometry.figure;

public record Triangle(double sideA, double sideB, double sideC) {
    public boolean checkTriangle() {
        if ((this.sideA > this.sideB + this.sideC) && (this.sideB > this.sideA + this.sideC) && (this.sideC > this.sideB + this.sideA)) {
            return true;
        } else if ((this.sideA > this.sideB - this.sideC) && (this.sideA > this.sideC - this.sideB) && (this.sideB > this.sideC - this.sideA) && (this.sideB > this.sideA - this.sideC) && (this.sideC > this.sideB - this.sideA) && (this.sideC > this.sideA - this.sideB)) {
            return true;
        } else return false;

    }

    public double calculatePerimeter() {
        return (this.sideA + this.sideB + this.sideC);
    }

    public double calculateArea() {
        double halfPerimeter = this.calculatePerimeter() / 2;
        return Math.sqrt(halfPerimeter * ((halfPerimeter - this.sideA) * (halfPerimeter - this.sideB) * (halfPerimeter - this.sideC)));
    }

    public static void printPerimeter(Triangle triangle) {
        if (triangle.checkTriangle() == true) {
            System.out.println(String.format("Triangle perimeter with sides: %f, %f and %f = %f", triangle.sideA, triangle.sideB, triangle.sideC, triangle.calculatePerimeter()));
        } else {
            System.out.println(String.format("Triangle with sides: %f, %f and %f does not exist", triangle.sideA, triangle.sideB, triangle.sideC));
        }
    }

    public static void printArea(Triangle triangle) {
        if (triangle.checkTriangle() == true) {
            System.out.println(String.format("Triangle area with sides: %f, %f and %f = %f", triangle.sideA, triangle.sideB, triangle.sideC, triangle.calculateArea()));
        } else {
            System.out.println(String.format("Triangle with sides: %f, %f and %f does not exist", triangle.sideA, triangle.sideB, triangle.sideC));
        }
    }


}
