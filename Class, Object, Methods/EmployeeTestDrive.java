import java.util.Scanner;
class Employee {
    public int id;
    public String name;
    private double salary;
    
    public Employee(){

    }
    public Employee(int id){
        this.id = id;
    }
    public Employee(int id, String name){

    }
    public void setSalary(double salary){
        if (salary >= 15_000 && salary <= 1_000_000) {
            this.salary = salary;
        }
        
    }

    /*public double getSalary(){
        return salary;
    } */

       public double getSalary(String password){

        if (password.equals("1212312121"))
            return salary;
        else return -1;
    }
    



    public class EmployeeTestDrive {
    
        public static void main(String[] args) {
            
            Employee PH = new Employee(00313);
           // PH.id = 00313;
            PH.name = "Phuriphat Hemakul";
            PH.setSalary(100_000);
            System.out.printf("เงินเดือน: %.2f" , PH.getSalary("1212312121"));
        
            Employee RR = new Employee();
            
            Scanner input = new Scanner(System.in);
            
            System.out.print("Please input your id: ");
            int epid = input.nextInt();
            System.out.print("Please input your name: ");
            input.nextLine();
            String name = input.nextLine();
            input.close();
            
            Employee CJ = new Employee(0311 , "Chanitha");
            
            System.out.printf("name = %s ", name);
            System.out.printf("id = %d" , epid);
        }

        
    }
}
