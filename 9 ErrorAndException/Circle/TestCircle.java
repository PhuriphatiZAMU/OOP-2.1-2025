package ErrorAndException.Circle;
class Circle  implements GeometricObject{
    protected double radius = 1.0;

    public Circle(double radius){
        if(radius < 0) {
            throw new IllegalArgumentException("Radius must be non-negative.");
        }   
        
        this.radius = radius;  
    }
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
    public double getArea() {
        return Math.PI * radius * radius;
    }
}

class ResizableCircle extends Circle implements Resizable {

    public ResizableCircle(double radius) {
        super(radius);
    }
    @Override
    public void resize(int percent) {
        if (percent <= 0) {
            throw new IllegalArgumentException("Resize percentage must be positive.");
        }
        radius = radius * percent / 100.0;
    }
    
}
interface GeometricObject {
    double getPerimeter();
    double getArea();
}
interface Resizable {
    void resize(int percent);
}

public class TestCircle {
    public static void main(String[] args) {
       
      try {
            ResizableCircle rc1 = new ResizableCircle(-10);  // radius ผิด
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }

        try {
            ResizableCircle rc2 = new ResizableCircle(10);
            rc2.resize(-50);  // resize ผิด
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }


        System.out.println("\n--- 9.2 FileReader with try-catch ---");

        // ทดสอบ 9.2
        try {
            java.io.File file = new java.io.File("E://file.txt");
            java.io.FileReader fr = new java.io.FileReader(file);
            System.out.println("Will this print ?");
            fr.close();
        } catch (java.io.IOException e) {
            System.out.println("IOException caught: " + e.getMessage());
        }

        System.out.println("\n--- 9.3 ArrayIndexOutOfBoundsException ---");

        // ทดสอบ 9.3
        int[] num = {1, 2, 3, 4};
        try {
            System.out.println(num[5]);  // เกินขอบเขต
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException caught: " + e.getMessage());
        }

        System.out.println("Will this print ?");
}
}
