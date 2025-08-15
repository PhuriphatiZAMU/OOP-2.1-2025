import java.util.Comparator;

public class SortByAge implements Comparator<Car> {
    @Override
    public int compare(Car c1, Car c2) {
        return c1.getAge() - c2.getAge();
    }
}
