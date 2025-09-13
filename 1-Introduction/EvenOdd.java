import java.util.Scanner;
public class EvenOdd {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        System.out.println("Please input integer: ");
        int n = input.nextInt();
        input.close();
        
        if(n % 2 == 0 ){
            System.out.println("Even");
        }else{
            System.out.println("Odd");
        }
    }
}

