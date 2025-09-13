public class Car implements Vehicle {

    private int numberOfWheels;

    public Car(int numberOfWheels) {
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
        if (numberOfWheels != 4) {
            throw new IllegalArgumentException("จำนวนล้อของรถยนต์ต้องเป็น 4 เท่านั้น");
        } return numberOfWheels;
            
        }
    }