package Abstraction.AbstractClass;

public class TestShape {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(4, 7);

        System.out.println("Circle:");
        circle.printInfo();

        System.out.println("\nRectangle:");
        rectangle.printInfo();
    }
}

