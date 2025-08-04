package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GreetingApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            createAndShowGUI();
            }
         });

    }
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Greeting App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 150);

        JPanel panel = new JPanel();
        frame.add(panel);

        JLabel namelabel = new JLabel("Input your name:");
        panel.add(namelabel);

        JTextField nameField = new JTextField(15);
        panel.add(nameField);

        JButton greetButton = new JButton();
        greetButton.setText("Greet");
        panel.add(greetButton);

        greetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ดึงข้อความจาก JTextField
                String name = nameField.getText();

                // ตรวจสอบว่าผู้ใช้กรอกชื่อหรือไม่
                if (name != null && !name.trim().isEmpty()) {
                    // พิมพ์คำทักทายออกทาง Console
                    System.out.println("Hello, " + name + "! Welcome");
                    // หรือจะแสดงเป็น Dialog Box ก็ได้
                    JOptionPane.showMessageDialog(frame, "Hello, " + name + "!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter your name.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }
}