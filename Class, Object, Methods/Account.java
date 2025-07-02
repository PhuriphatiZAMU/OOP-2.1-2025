import javax.swing.JOptionPane;
class Account {
/// ทำเพิ่มเติมเพื่อเก็บข้อมูล
private double balance;
     public Account(double initialBalance) {
         if (initialBalance > 0.0) balance=initialBalance;
     }
     public void deposit(double amount){ /// การเพิ่มเงินเข้าบัญชี
         balance=balance+amount;
     }
    public void withdraw(double amount) { // การถอนเงินออกบัญชี
        if (amount <= balance) {
            balance -= amount;
        } else {
            JOptionPane.showMessageDialog(null, "วงเงินของท่านไม่เพียงพอ", "แจ้งเตือน!", JOptionPane.ERROR_MESSAGE);
        }
    }
     public double getBalance(){ /// ดูยอดเงินในบัญชี
         return balance;
     }
     @Override
     public String toString(){
        return "บัญชีนี้มีเงิน = " + balance;
     }
}

