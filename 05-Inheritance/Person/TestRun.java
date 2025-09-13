package Inheritance.Person;
public class TestRun {
    
    public static void main (String [] args) {

    System.out.println ("========Person ============================");
    Person p = new Person("Phuriphat Hemakul","231/3 Songphinong Witthaya School Suphanburi");
    System.out.println(p);
        
    System.out.println ("========Staff ============================");    
    Staff st1 = new Staff("Chanakarn Kingkaew","85/1 PIM Nonthaburi","PIM",1_000_000);
    System.out.println(st1);

    System.out.println ("========Staff # 2 ============================");
    Staff st2 = new Staff("Tinnapop Dindam","85/1 PIM Nonthaburi","PIM",1_500_000);
    System.out.println(st2);
    
    System.out.println ("========Student # 1 ============================");
    Student cv = new Student("Katarina Blu","85/1 PIM Nonthaburi","CAI",2558,50000);
    System.out.println(cv);

    System.out.println ("========Student # 2 ============================");
    Student cv2 = new Student("Phuriphat Hemakul", "85/1 PIM Nonthabur", "CAI", 2567, 30_000);
    System.out.println(cv2);
    
    System.out.println ("========Student # 3 ============================");
    Student cv3 = new Student("Hizutome Rakuro", "Tokyo Japan", "GSP", 2567, 119_000);
    System.out.println(cv3);

    System.out.println ("========Student # 4 ============================");
    Student cv4 = new Student("Wiraphat Phimsalit", "Silpakorn Nakhon Pathom", "ECS", 2568, 19_000);
    System.out.println(cv4);
    }
    
}
