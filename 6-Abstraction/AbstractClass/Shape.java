package Abstraction.AbstractClass;
abstract class Shape {

    abstract double getArea(); 
    abstract double getPerimeter();
    void printInfo() {
        System.out.printf("Area: %.2f\nPerimeter: %.2f%n", getArea(), getPerimeter());
    }

}

