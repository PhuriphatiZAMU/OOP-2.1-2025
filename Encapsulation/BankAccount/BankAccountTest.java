package Encapsulation.BankAccount;
// คลาสสำหรับทดสอบการทำงานของคลาส BankAccount
public class BankAccountTest {

   // เมธอดหลักสำหรับทดสอบการทำงานของคลาส BankAccount
   public static void main(String[] args) {
       try {
           BankAccount account = new BankAccount("0411200000", "Karina Bluu", 1000.0);
           account.checkBalance();
           account.deposit(500.0);
           account.checkBalance();
           account.withdraw(200.0);
           account.checkBalance();
           account.withdraw(2000.0);  // Should print an error message
           account.checkBalance();


           // Testing setter and getter
           account.setAccountHolderName("Yu Jimin");
           System.out.println("Account Holder Name: " + account.getAccountHolderName());
       } catch (IllegalArgumentException e) {
           System.out.println(e.getMessage());
       }


        try {
              // ทดสอบการสร้างบัญชีใหม่ด้วยเลขที่บัญชีไม่ถูกต้อง
           BankAccount invalidAccount = new BankAccount("12345", "Invalid Account", 400.0);
       } catch (IllegalArgumentException e) {
           System.out.println(e.getMessage());
       }

        try {
                // ทดสอบการสร้างบัญชีใหม่ด้วยยอดเงินฝากต่ำกว่าขั้นต่ำ
                BankAccount anotherAccount = new BankAccount("1234567890", "John Doe", 300.0);
                anotherAccount.deposit(200.0);    
         } catch (IllegalArgumentException e) {
              System.out.println(e.getMessage());
        }

        try {
           // ทดสอบสร้างบัญชีใหม่และแสดงข้อมูลบัญชี
           BankAccount Arm = new BankAccount("6752300313", "Phuriphat Hemakul", -9999.50);
           System.out.println(Arm.toString());
       } catch (IllegalArgumentException e) {
           System.out.println(e.getMessage());
       }
   }
}

