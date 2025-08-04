package Polymorphism.Shape;

class Circle extends Shape {

    protected double redius;

    public Circle(){
        super();
    }
    public Circle(double redius){
        super();
        this.redius = redius;
    }
    public Circle(String color, boolean filled, double redius){
        super(color,filled);
        this.redius = redius;
    }
    public double getRedius() {
        return redius;
    }
    public void setRedius(double redius) {
        this.redius = redius;
    }
    public double getArea(){
        return Math.PI * Math.pow(redius, 2);
    }
    public double getPerimeter(){
        return 2 * Math.PI * redius;
    }
    public String toString() {
        return String.format("A Circle with radius = %.2f, which is a subclass of %s", redius , super.toString());
    }
}