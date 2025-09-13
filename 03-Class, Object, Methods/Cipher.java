import java.util.Scanner;

public class Cipher {
    public static void main(String[] args) {
        System.out.println("รับข้อความ: ");
        Scanner input = new Scanner(System.in);
        String text = input.nextLine();   
        int shift = input.nextInt();      
        input.close();

        
        shift = shift % 26;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);  

            if (Character.isLetter(c)) {
                c = Character.toLowerCase(c); 

                
                char shifted = (char)(c + shift);

                
                if (shifted > 'z') {
                    shifted -= 26;
                }
                if (shifted < 'a') {
                    shifted += 26;
                }

                System.out.print(shifted); 
            } else {
                System.out.print(c); 
            }
        }
    }
}


