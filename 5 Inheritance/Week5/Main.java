package Inheritance.Week5;
class Engine {

    private String type;

    public Engine(String type) { // เมื่อสร้าง Constructor มาแล้วจะไม่สามารถเปลี่ยนแปลงได้
        this.type = type;
    }

    public void start() {
        System.out.println("Engine of type " + type + " is starting.");
    }

    
}
// Car has a Engine
class Car {
   private Engine engine; // instance ของ Engine ที่สร้างขึ้นมา (has a Engine)
    private String model; // รุ่นของรถ

    public Car(String model, String type) {
        this.model = model;
        this.engine = new Engine(type); // Composition
    }
    public void startCar() {
        System.out.println("Starting car model " + model);
        engine.start(); // Engine เริ่มทำงาน
    }
    
}

public class Main {
    public static void main(String[] args) {

        Car Camry = new Car("Toyota Camry", "Hybrid");
        Camry.startCar(); 

        Car Ferrari = new Car("Ferrari 488", "Gasoline");
        Ferrari.startCar();

        Car Lamborghini = new Car("Lamborghini Huracan", "Gasoline");
        Lamborghini.startCar();
    }
}
