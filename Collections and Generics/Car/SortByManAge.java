import java.util.Comparator;

public class SortByManAge implements Comparator<Car> {
    @Override
    public int compare(Car c1, Car c2) {
        int manCompare = c1.getManufacturer().compareTo(c2.getManufacturer());
        if (manCompare != 0) {
            return manCompare;
        } else {
            return c1.getAge() - c2.getAge();
        }
    }
}

