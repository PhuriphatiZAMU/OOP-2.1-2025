import java.util.*;

public class TestCar {
    public static void main(String[] args) {
        // ข้อ 1: TreeSet เรียงตาม manufacturer (ไม่ใช้ Comparator)
        Set<Car> carSet = new TreeSet<>();
        carSet.add(new Car("Benz", 10));
        carSet.add(new Car("Toyota", 5));
        carSet.add(new Car("Honda", 7));
        carSet.add(new Car("BMW", 8));
        carSet.add(new Car("Audi", 6));
        carSet.add(new Car("Ford", 3));
        carSet.add(new Car("Isuzu", 4));
        carSet.add(new Car("Mazda", 2));
        carSet.add(new Car("Nissan", 12));
        carSet.add(new Car("Chevrolet", 9));

        System.out.println("=== TreeSet เรียงตาม manufacturer (compareTo) ===");
        for (Car car : carSet) {
            System.out.println(car);
        }

        // ข้อ 3: ArrayList + Comparator
        List<Car> carList = new ArrayList<>(carSet);

        System.out.println("\n=== SortByMan ===");
        Collections.sort(carList, new SortByMan());
        for (Car c : carList) {
            System.out.println(c);
        }

        System.out.println("\n=== SortByAge ===");
        Collections.sort(carList, new SortByAge());
        for (Car c : carList) {
            System.out.println(c);
        }

        System.out.println("\n=== SortByManAge ===");
        Collections.sort(carList, new SortByManAge());
        for (Car c : carList) {
            System.out.println(c);
        }
    }
}

