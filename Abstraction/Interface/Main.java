

public class Main {
    public static void main(String[] args) {
        Vehicle car = new Car(4);
        Vehicle motorcycle = new Motorcycle(2);
        car.startEngine();
        System.out.println("Car has " + car.getNumberOfWheels() + " wheels");
        car.stopEngine();
        motorcycle.startEngine();
        System.out.println("Motorcycle has " + motorcycle.getNumberOfWheels() + " wheels");
        motorcycle.stopEngine();
    }
}
