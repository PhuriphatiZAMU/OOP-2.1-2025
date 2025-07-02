// คลาส BankAccount แทนบัญชีธนาคารอย่างง่ายพร้อมฟังก์ชันพื้นฐาน
public class BankAccount {
    // ฟิลด์สำหรับเลขที่บัญชี, ชื่อเจ้าของบัญชี และยอดเงินคงเหลือ
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    // คอนสตรัคเตอร์สำหรับสร้างบัญชีใหม่
    public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    // เมธอดสำหรับดึงเลขที่บัญชี
    public String getAccountNumber() {
        return accountNumber;
    }

    // เมธอดสำหรับดึงชื่อเจ้าของบัญชี
    public String getAccountHolderName() {
        return accountHolderName;
    }

    // เมธอดสำหรับดึงยอดเงินคงเหลือ
    public double getBalance() {
        return balance;
    }

    // เมธอดฝากเงินเข้าบัญชี
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    // เมธอดถอนเงินออกจากบัญชี (ถ้ามียอดเงินเพียงพอ)
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }
     // เมธอดแสดงยอดเงินคงเหลือปัจจุบัน
    public void checkBalance() {
        System.out.println("Current balance: " + balance);
    }

    // setter สำหรับชื่อเจ้าของบัญชี พร้อมตรวจสอบความถูกต้อง
    public void setAccountHolderName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.accountHolderName = name;
        } else {
            throw new IllegalArgumentException("Invalid account holder name.");
        }
    }

    // คืนค่าข้อมูลบัญชีในรูปแบบสตริง
    @Override
    public String toString() {
        return String.format("Account Number: %s, Account Holder: %s, Balance: %.2f", 
                             accountNumber, accountHolderName, balance);
    }

    // คลาสย่อยสำหรับทดสอบการทำงานของ BankAccount
    public class BankAccountTest {
        // เมธอด main สำหรับรันทดสอบ
        public static void main(String[] args) {
            try {
                BankAccount account = new BankAccount("0411200000", "Karina Bluu", 1000.0);
                account.checkBalance();
                account.deposit(500.0);
                account.checkBalance();
                account.withdraw(200.0);
                account.checkBalance();
                account.withdraw(2000.0);  // ควรแสดงข้อความ error
                account.checkBalance();

                // ทดสอบ setter และ getter
                account.setAccountHolderName("Yu Jimin");
                System.out.println("Account Holder Name: " + account.getAccountHolderName());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            try {
                BankAccount invalidAccount = new BankAccount("12345", "Invalid Account", 400.0);
                System.out.println(invalidAccount);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

   
}
