package Inheritance.Person;

public class Student extends Person {
    private String program;
    private int year;
    private double fee;
    public Student(String name, String address, String program, int year, double fee) {
        super(name,address);
        this.program = program;
        this.fee=fee;
        this.year=year;
         System.out.println("Invoke Student(String name, String address, String program, int year, double fee)");
    }
    public String getProgram() {
        return program;
    }
    public void setProgram(String program) {
        this.program = program;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public double getFee() {
        return fee;
    }
    public void setFee(double fee) {
        this.fee = fee;
    }
    public String toString () {
    return String.format("%s[%s],program=%s,year=%d,fee=%.2f]",getClass().getSimpleName(), super.toString(), program,year,fee);
    }
}
