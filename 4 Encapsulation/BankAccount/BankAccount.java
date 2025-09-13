package Encapsulation.BankAccount;
// คลาส BankAccount แทนข้อมูลบัญชีธนาคาร
class BankAccount{
    // เลขที่บัญชี
    private String accountNumber;
    // ชื่อเจ้าของบัญชี
    private String accountHolderName;
    // ยอดเงินคงเหลือ
    private double balance;

    // คอนสตรัคเตอร์สำหรับสร้างบัญชีใหม่
    public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
        // ตรวจสอบว่าเลขที่บัญชีมีความยาว 10 ตัวอักษร
        if (accountNumber.length() != 10) {
            throw new IllegalArgumentException("Account number must be exactly 10 characters long");
        } this.accountNumber = accountNumber;

        // ตรวจสอบชื่อเจ้าของบัญชีไม่ให้เป็น null ว่างเปล่า หรือ trim ชื่อให้ไม่มีช่องว่างหน้า-หลัง
        if (accountHolderName == null || accountHolderName.trim().isEmpty()) {
            throw new IllegalArgumentException("Account holder name cannot be null or empty");
        }this.accountHolderName = accountHolderName;

        // ตรวจสอบยอดเงินฝากขั้นต่ำในการเปิดบัญชี
        if (initialBalance < 500.0) {
            throw new IllegalArgumentException("To open an account, a minimum deposit of 500 baht is required.");
        } this.balance = initialBalance;
        } 
     // เมธอดฝากเงิน
        public void deposit(double amount) {
        // ตรวจสอบว่าจำนวนเงินที่ฝากต้องมากกว่า 0
        if (amount <= 0) {
            // หากจำนวนเงินฝากไม่ถูกต้อง ให้โยนข้อผิดพลาด
            throw new IllegalArgumentException("The deposit amount must be greater than 0.");
        }
        // หากจำนวนเงินฝากถูกต้อง ให้ทำการฝากเงิน
        balance += amount;
        // แสดงผลการฝากเงิน
        System.out.println("Deposited: " + amount);
    }
    // เมธอดถอนเงิน
    public void withdraw(double amount) {
        // ตรวจสอบว่าจำนวนเงินที่ถอนมากกว่ายอดคงเหลือหรือไม่ และจำนวนเงินที่ถอนต้องมากมกว่า 0
        if (amount > balance || amount < 0) {
            // หากยอดเงินไม่เพียงพอหรือจำนวนเงินที่ถอนไม่ถูกต้อง ให้โยนข้อผิดพลาด
            throw new IllegalArgumentException("Insufficient funds or invalid withdraw amount");
        }
        // หากยอดเงินเพียงพอ ให้ทำการถอนเงิน
        balance -= amount;
        // แสดงผลการถอนเงิน
            System.out.println("Withdrew: " + amount);

    }
    // เมธอดแสดงยอดเงินคงเหลือ
    public void  checkBalance() {
        // แสดงยอดเงินคงเหลือปัจจุบัน
        System.out.println("Current balance: " + balance);
        // คืนยอดเงินคงเหลือ
    }
    // เมธอดดึงเลขที่บัญชี
    public String getAccountNumber() {
        return accountNumber;
    }
    // เมธอดดึงชื่อเจ้าของบัญชี
    public String getAccountHolderName() {
        return accountHolderName;
    }
    // เมธอดดึงยอดเงินคงเหลือ
    public double getBalance() {
        return balance;
    }
    // เมธอดตั้งค่าชื่อเจ้าของบัญชี
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }
    // เมธอดแสดงข้อมูลบัญชีในรูปแบบ String
    @Override
    public String toString() {
        return String.format("Account Number : %s\nAccount Holder : %s\nBalance : %.2f", accountNumber, accountHolderName, balance);
    }
}
