import java.util.Scanner;
public class StudentID {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Your ID: ");
        String id = input.nextLine();
        input.close();
        
        if(id.length() == 10){
            System.out.println("รหัสนักศึกษาถูกต้อง");
        }else{
            System.out.println("โปรดลองใส่อีกครั้ง");
        }
    }
}
