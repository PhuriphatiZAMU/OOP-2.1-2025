import java.util.Scanner;

public class HW1PABasicCalculation {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
    
        System.out.print("Enter n: ");
        double n = input.nextDouble();
    
        System.out.print("Enter m: ");
        double m = input.nextDouble();
    
        System.out.print("Enter (+, -, *, /, ^): ");
        char operator = input.next().charAt(0);
        input.close();
    
        double result = switch (operator){
        case '+'-> n + m;
        case '-'-> n - m;
        case '*'-> n * m;
        case '/'-> n / m;
        case '^'-> Math.pow(n,m);
        default -> throw new IllegalArgumentException("Invalid operator!");
       
        };
        System.out.printf("%2f" , result);
        }
}
    

