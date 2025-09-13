import java.util.Scanner;
public class Split {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        System.out.println("Plaese Enter Your Number Phone: ");
        String Phone = input.nextLine();
        input.close();

        String cleanPhone = Phone.replace("-", "");
        System.out.println(cleanPhone);

    }
}
