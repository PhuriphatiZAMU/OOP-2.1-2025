package Polymorphism.Shape;

class Rectangle extends Shape {
    protected double width;
    protected double length;

    public Rectangle(){
        super();
    }
    public Rectangle(double width , double length){
        super();
        this.width = width;
        this.length = length;
    }
    public Rectangle(String color , boolean filled , double width , double length){
        super(color,filled);
        this.width = width;
        this.length = length; 
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        this.length = length;
    }
    public double getArea(){
        return width * length;
    }
    public double getPerimeter(){
        return 2 * (length + width);
    }
    public String toString(){
        return String.format("A Rectangle with width = %.2f and length = %.2f, which is a subclass of %s", width,length,super.toString());
    }
}
