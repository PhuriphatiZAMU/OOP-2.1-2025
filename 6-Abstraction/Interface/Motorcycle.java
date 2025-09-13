
public class Motorcycle implements Vehicle {
    private int numberOfWheels;

    public Motorcycle(int numberOfWheels) {
            this.numberOfWheels = numberOfWheels;
    }
    
    @Override
    public void startEngine() {
    }

    @Override
    public void stopEngine() {
    }

    @Override
    public int getNumberOfWheels() {
        if (numberOfWheels != 2) {
            throw new IllegalArgumentException("จำนวนล้อของรถจักรยานยนต์ต้องเป็น 2 เท่านั้น");
        }return numberOfWheels;
    }
}

    

