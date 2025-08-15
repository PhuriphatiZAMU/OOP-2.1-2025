import java.util.Comparator;

public class SortByMan implements Comparator<Car> {
    @Override
    public int compare(Car c1, Car c2) {
        return c1.getManufacturer().compareTo(c2.getManufacturer());
    }
}
