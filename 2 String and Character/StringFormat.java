import java.util.Scanner;

public class StringFormat {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input your name: ");
        String names = input.nextLine();
        input.close();

        names = names.trim();
        

        String [] fullnames = names.split("\\s");

        String result = "";

        for (int i = 0; i < fullnames.length; i++){
            
            if (fullnames[i].length() > 0) {
                String firsttext = fullnames[i].substring(0 , 1).toUpperCase();
                String rest = "";

                if (fullnames[i].length() > 1) {
                    rest = fullnames[i].substring(1).toLowerCase();
                }
                result = result + firsttext + rest + " ";
            }
                 
            }
            System.out.printf("ผลลัพธ์ที่ได้: %s", result.trim());
        }
        
}