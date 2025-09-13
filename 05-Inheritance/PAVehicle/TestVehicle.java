package Inheritance.PAVehicle;

class Vehicle {
    protected String brand;
    protected int year;

    public void move() {
        System.out.println("Vehicle is moving.");
    }
}

 class Car extends Vehicle {
    protected int numDoors;

    public void honk() {
        System.out.println("Car horn goes beep!");
    }
}

class ElectricCar extends Car {
    private double batteryCapacity = 75.0; // Example value in kWh

    public void charge() {
        System.out.println("Electric car is charging.");
        System.out.println("Battery capacity: " + batteryCapacity + " kWh");
    }
}

class SportsCar extends Car {
    private int maxSpeed = 300; // Example value in km/h

    public void turboBoost() {
        System.out.println("Sports car turbo boost!");
        System.out.println("Max speed: " + maxSpeed + " km/h");
    }
}

class Motorcycle extends Vehicle {
    private boolean hasSidecar;

    public Motorcycle() {
        this.hasSidecar = false; // default value
    }

    public boolean hasSidecar() {
        return hasSidecar;
    }

    public void revEngine() {
        System.out.println("Motorcycle engine revs!");
        System.out.println("Has sidecar: " + hasSidecar);
    }
}
public class TestVehicle {
    public static void main(String[] args) {
        ElectricCar ec = new ElectricCar();
        ec.move();
        ec.honk();
        ec.charge();

        SportsCar sc = new SportsCar();
        sc.move();
        sc.honk();
        sc.turboBoost();

        Motorcycle mc = new Motorcycle();
        mc.move();
        mc.revEngine();
    }
}
