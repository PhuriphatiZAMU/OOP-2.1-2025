package Encapsulation.Student;

class Student {
    // Attributes (private)
    private String studentId;
    private String name;
    private double gpa;

    // Parameterized Constructor
    public Student(String studentId, String name, double gpa) {
        this.studentId = studentId;
        this.name = name;
        setGpa(gpa); // ใช้ setter เพื่อตรวจสอบค่ามากกว่า 0
    }

    // Getter Methods
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    // Setter Methods
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGpa(double gpa) {
        if (gpa >= 0) {
            this.gpa = gpa;
        } else {
            System.out.println("GPA must be >= 0");
        }
    }
}

public class StudentTest {
    public static void main(String[] args) {
        // สร้างอินสแตนซ์ผ่าน Parameterized Constructor
        Student student1 = new Student("S12345", "Alice", 3.75);

        // แสดงค่าของแต่ละ attribute ผ่าน Getter
        System.out.println("studentId = " + student1.getStudentId());
        System.out.println("name = " + student1.getName());
        System.out.println("gpa = " + student1.getGpa());
    }
}
