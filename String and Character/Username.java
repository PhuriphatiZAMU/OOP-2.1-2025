import java.util.Scanner;
public class Username {
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        
        System.out.println("โปรดกรอก Username เพื่อทำการสมัครบริการ");
        String username1 = input.nextLine(); // ทำการใส่ username ครั้งแรก
        System.out.println("โปรดกรอก Username อีกครั้ง เพื่อทำการสมัครบริการ");
        String username2 = input.nextLine();// ทำการใส่ username อีกครั้งเพื่อยืนยัน
        input.close();

        if (username1 == username2) { /// ถ้า username1 กับ username2 มีค่าเท่ากัน
        
            System.out.println("Success");
            
        }
        else System.out.println ("Failed Please re-enter again!");   

    }
}
