package Polymorphism.Shape;

class Square extends Shape {

    protected double side;

    public Square() {
        super();
    }

    public Square(double side) {
        super();
        this.side = side;
    }

    public Square(String color, boolean filled, double side) {
        super(color, filled);
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    public double getArea() {
        return side * side;
    }

    public double getPerimeter() {
        return 4 * side;
    }

    public String toString() {
        return String.format(
            "A Square with side = %.2f, which is a subclass of %s", 
            side, 
            super.toString()
        );
    }
}

