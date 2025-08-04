package Polymorphism.Shape;

public class TestShape {

    public static void main(String[] args) {
       
        Shape c = new Circle("Blue", true , 5.0);
        System.out.println(c);
        Shape c2 = new Circle("Red", false , 4.0);
        System.out.println(c2);
        

    }

}

