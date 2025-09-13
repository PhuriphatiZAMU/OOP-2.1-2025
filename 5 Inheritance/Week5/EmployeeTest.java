package Inheritance.Week5;

// Date class (for birth date)
class Date {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String toString() {
        return day + "/" + month + "/" + year;
    }
}
class Employee {

        private Date date;
        private String name;

    public Employee(String name, Date birthDate) {
        this.name = name;
        date = birthDate;
    }
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Birthday: " + date); // date.toString() 
    }
    
}
class Teacher extends Employee {
    private String subject;
    public Teacher(String name, Date date) {
        super(name, date); 
    }
    public Teacher(String name, Date date, String subject) {
        super(name, date); 
        this.subject = subject;
    }
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Subject: " + subject);
    }
}


public class EmployeeTest {
        public static void main(String[] args) {

        Date birthDate = new Date(15, 5, 2006);
        Employee Arm = new Employee("Arm", birthDate);
        Arm.displayInfo();

        Date birthDate2 = new Date(6, 6, 1966);
        Teacher Oak = new Teacher("Oak", birthDate2, "OOP");
        Oak.displayInfo();

}
}
