import java.util.Scanner;
public class IntDouble {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("int a :");
        System.out.println("double b :");
        
        int a = input.nextInt();
        double b = input.nextDouble();
        input.close();
        
        if(a > b){
            System.out.println(a + " " + b);
        }else{
            System.out.println(b + " " + a);
        }
    }
}

