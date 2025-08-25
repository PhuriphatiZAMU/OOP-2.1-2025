package Polymorphism;

class Student {
    private String id;
    private String name;
    private double gpa;

    
    public Student(String id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student other = (Student) obj;
        return this.id.equals(other.id);
    }

    public class StudentTest {
        public static void main(String[] args) {
        Student studentA = new Student("S001", "Alice", 3.5);
        Student studentB = new Student("S001", "Alicia", 3.7);
        Student studentC = new Student("S002", "Bob", 3.5);

        System.out.println("Student A and B are the same student: " + studentA.equals(studentB));
        System.out.println("Student A and C are the same student: " + studentA.equals(studentC)); 
    }    
    }
    
}

