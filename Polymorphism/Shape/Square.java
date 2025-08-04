package Polymorphism.Shape;

class Square extends Rectangle {
    protected double side;
    
    public Square(){
        super();
    }
    public Square(double side) {
        super(side, side); 
    }

    public Square(double side , String color, boolean filled){
        super();
        this.side = side;
    }
    public double getSide() {
        return side;
    }
    public void setSide(double side) {
        this.side = side;
    }
    public void setWidth(double width) {
        super.setWidth(width);
    }
    public void setLength(double length) {
        super.setLength(length);
    }
    public String toString(){
        return String.format("A Square with side = %.2f , which is a subclass of %s", side,super.toString());
    }
}
