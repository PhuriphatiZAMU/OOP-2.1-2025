package FootballerImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * FootballerPuzzle - 15 Puzzle แบบภาพนักเตะ
 * - Smooth slide animation ภายในกระดาน (ไม่ใช้ JWindow -> ไม่มีแสงกะพริบ)
 * - ป้องกันคลิกซ้ำระหว่างแอนิเมชัน (รูปไม่หาย)
 * - ตรวจจบเกมด้วยค่า tile index (ไม่เทียบรูป)
 * - มีเลขกำกับมุมขวาล่างของรูปเพื่อเล่นง่าย
 */
public class FootballerPuzzle extends JFrame {

    // --- เกม ---
    private JButton[][] buttons;
    private int emptyRow = 3, emptyCol = 3;
    private int moves = 0;
    private javax.swing.Timer timer;   // ตัวจับเวลาเล่นเกม (วินาที)
    private int seconds = 0;
    private boolean isPaused = false;
    private boolean animating = false; // ป้องกันคลิกระหว่างแอนิเมชัน

    // --- UI หลัก ---
    private JLayeredPane boardLayer;   // ชั้นซ้อน: ใส่กระดาน + overlay แอนิเมชัน
    private JPanel gridPanel;          // กระดานปุ่ม (GridLayout)
    private AnimationOverlay overlay;  // วาดภาพที่กำลังเลื่อน
    private JButton btnPause, btnNewGame;
    private JLabel lblMoves, lblTime, lblLevel;

    private Font font;

    // พาธรูปภาพ (คงตามของคุณ)
    private final String[] images = {
        "/FootballerImage/Lionel-Messi-2014-15.jpg",
        "/FootballerImage/Neymar-2014-15.jpg",
        "/FootballerImage/Cristiano-Ronaldo-2017-18.jpg",
        "/FootballerImage/Luis-Suarez-2014-15.jpg",
        "/FootballerImage/Kevin-De-Bruyne-2022-23.jpg"
    };

    private int currentLevel = 1;
    private int currentImageIndex = 0;

    // โหลดฟอนต์ Prompt แบบ fallback ได้
    {
        try {
            URL fontUrl = new URL("https://fonts.googleapis.com/css2?family=Prompt:wght@400;500;600&display=swap");
            font = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream()).deriveFont(Font.PLAIN, 16f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (Exception ex) {
            font = new Font("SansSerif", Font.PLAIN, 16);
        }
    }

    public FootballerPuzzle() {
        initComponents();
        newGame();
    }

    private void initComponents() {
        setTitle("Footballer Puzzle");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420, 640);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        // ---- Board Layer (ซ้อน grid + overlay) ----
        boardLayer = new JLayeredPane();
        boardLayer.setBounds(30, 100, 340, 340);
        add(boardLayer);

        // Grid ของปุ่ม: ช่องว่าง 1 px (ให้ภาพแทบชิดกัน)
        gridPanel = new JPanel(new GridLayout(4, 4, 1, 1));
        gridPanel.setBackground(new Color(100, 150, 100)); // BG เขียวพาสเทลเข้ม
        gridPanel.setBounds(0, 0, 340, 340);
        boardLayer.add(gridPanel, JLayeredPane.DEFAULT_LAYER);

        // Overlay สำหรับวาดภาพที่กำลังเคลื่อน
        overlay = new AnimationOverlay();
        overlay.setBounds(0, 0, 340, 340);
        boardLayer.add(overlay, JLayeredPane.DRAG_LAYER);

        // ---- แถบควบคุมด้านบน ----
        btnNewGame = new JButton("NEW GAME");
        btnNewGame.setFocusPainted(false);
        btnNewGame.setFont(font.deriveFont(Font.BOLD, 14f));
        btnNewGame.setBounds(30, 30, 120, 30);
        btnNewGame.addActionListener(e -> newGame());
        add(btnNewGame);

        lblLevel = new JLabel("Level: 1");
        lblLevel.setBounds(160, 30, 80, 30);
        lblLevel.setFont(font.deriveFont(Font.BOLD, 12f));
        add(lblLevel);

        lblTime = new JLabel("Time: 0s");
        lblTime.setBounds(240, 30, 80, 30);
        lblTime.setFont(font.deriveFont(Font.BOLD, 12f));
        add(lblTime);

        lblMoves = new JLabel("Moves: 0");
        lblMoves.setBounds(320, 30, 80, 30);
        lblMoves.setFont(font.deriveFont(Font.BOLD, 12f));
        add(lblMoves);

        // ---- ปุ่ม Pause ด้านล่าง ----
        btnPause = new JButton("Pause");
        btnPause.setBounds(30, 460, 340, 40);
        btnPause.setFont(font.deriveFont(Font.BOLD, 14f));
        btnPause.setFocusPainted(false);
        btnPause.addActionListener(e -> {
            if (timer == null) return;
            if (!isPaused) {
                timer.stop();
                btnPause.setText("Resume");
                isPaused = true;
            } else {
                timer.start();
                btnPause.setText("Pause");
                isPaused = false;
            }
        });
        add(btnPause);
    }

    // เริ่มเกมใหม่ (Level = 1, รูปสุ่มใหม่)
    private void newGame() {
        if (animating) overlay.cancel(); // กันค้างกลางทาง
        currentLevel = 1;
        currentImageIndex = (int) (Math.random() * images.length);
        initGame();
    }

    // ตั้งกระดานและจับเวลา
    private void initGame() {
        gridPanel.removeAll();
        overlay.clear();

        buttons = new JButton[4][4];
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 16; i++) numbers.add(i);
        Collections.shuffle(numbers);

        String imagePath = images[currentImageIndex];

        try {
            BufferedImage img = ImageIO.read(getClass().getResource(imagePath));
            int tileSize = img.getWidth() / 4; // สมมติภาพสี่เหลี่ยมจัตุรัส

            int index = 0;
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    int value = numbers.get(index++);
                    JButton btn = new JButton();
                    btn.setBorder(null);
                    btn.setFocusPainted(false);
                    btn.setContentAreaFilled(false); // ไม่ทับรูป

                    if (value == 15) {
                        // ช่องว่าง
                        btn.putClientProperty("value", -1);
                        emptyRow = row;
                        emptyCol = col;
                    } else {
                        int r = value / 4;
                        int c = value % 4;
                        BufferedImage subImg = img.getSubimage(c * tileSize, r * tileSize, tileSize, tileSize);
                        subImg = drawNumberOnTile(subImg, value + 1); // เพิ่มเลขกำกับ
                        btn.setIcon(new ImageIcon(subImg));
                        btn.putClientProperty("value", value);
                    }

                    final int rClick = row, cClick = col;
                    btn.addActionListener(e -> moveTile(rClick, cClick));
                    buttons[row][col] = btn;
                    gridPanel.add(btn);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // รีเซ็ตสถานะ
        moves = 0;
        lblMoves.setText("Moves: 0");
        seconds = 0;
        lblTime.setText("Time: 0s");
        lblLevel.setText("Level: " + currentLevel);
        isPaused = false;
        btnPause.setText("Pause");

        // จับเวลา
        if (timer != null) timer.stop();
        timer = new javax.swing.Timer(1000, e -> {
            seconds++;
            lblTime.setText("Time: " + seconds + "s");
        });
        timer.start();

        gridPanel.revalidate();
        gridPanel.repaint();
    }

    // ย้าย tile (อนุญาตเฉพาะ 4 ทิศ ไม่เอาแนวทแยง)
    private void moveTile(int row, int col) {
        if (isPaused || animating) return;

        boolean adjacent =
                (Math.abs(row - emptyRow) == 1 && col == emptyCol) ||
                (Math.abs(col - emptyCol) == 1 && row == emptyRow);

        if (!adjacent) return;

        JButton src = buttons[row][col];
        JButton dst = buttons[emptyRow][emptyCol];

        Icon icon = src.getIcon();
        if (icon == null) return; // เผื่อเผลอกดช่องว่าง

        // เก็บค่า tile index ไว้ย้ายตามภาพ
        Integer srcVal = (Integer) src.getClientProperty("value");

        // เริ่มแอนิเมชัน: เคลียร์ภาพจาก src ชั่วคราว แล้ววาดบน overlay แทน
        animating = true;
        src.setIcon(null);

        Point from = SwingUtilities.convertPoint(src.getParent(), src.getLocation(), overlay);
        Point to   = SwingUtilities.convertPoint(dst.getParent(), dst.getLocation(), overlay);

        overlay.start(icon, from, to, () -> {
            // เมื่อแอนิเมชันจบ: ใส่รูปให้ช่องว่าง (dst) และอัปเดต value
            dst.setIcon(icon);
            dst.putClientProperty("value", srcVal);

            // อัปเดตช่องว่างใหม่: src กลายเป็นว่าง
            src.putClientProperty("value", -1);

            // ปรับตำแหน่งช่องว่าง
            emptyRow = row;
            emptyCol = col;

            // นับเดิน
            moves++;
            lblMoves.setText("Moves: " + moves);

            animating = false;

            if (checkWin()) {
                timer.stop();
                JOptionPane.showMessageDialog(this,
                        "Level " + currentLevel + " Completed!\nMoves: " + moves + "\nTime: " + seconds + "s");

                currentLevel++;
                currentImageIndex = (currentImageIndex + 1) % images.length;
                initGame();
            }
        });
    }

    // ตรวจชนะโดยดู value แต่ละช่อง (ไม่เทียบรูป)
    private boolean checkWin() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                int expected = row * 4 + col;
                Integer val = (Integer) buttons[row][col].getClientProperty("value");
                if (row == 3 && col == 3) {
                    if (val == null || val != -1) return false; // ช่องสุดท้ายต้องว่าง
                } else {
                    if (val == null || val != expected) return false;
                }
            }
        }
        return true;
    }

    /** วาดเลขมุมขวาล่างบน tile */
    private BufferedImage drawNumberOnTile(BufferedImage tile, int number) {
        BufferedImage newTile = new BufferedImage(tile.getWidth(), tile.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newTile.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.drawImage(tile, 0, 0, null);

        g.setFont(font.deriveFont(Font.BOLD, Math.max(14f, tile.getWidth() * 0.12f)));
        String text = String.valueOf(number);

        FontMetrics fm = g.getFontMetrics();
        int x = tile.getWidth() - fm.stringWidth(text) - 6;
        int y = tile.getHeight() - 6;

        // เงาดำ
        g.setColor(new Color(0, 0, 0, 180));
        g.drawString(text, x + 1, y + 1);
        // ตัวอักษรขาว
        g.setColor(Color.WHITE);
        g.drawString(text, x, y);

        g.dispose();
        return newTile;
    }

    /** ชั้น overlay วาดภาพที่กำลังเลื่อนแบบนุ่ม ๆ */
    private class AnimationOverlay extends JComponent {
        private Icon icon;
        private Point from, to;
        private long start;
        private int durationMs = 120; // ความเร็วเลื่อน (ยิ่งน้อยยิ่งไว)
        private javax.swing.Timer animTimer;
        void start(Icon icon, Point from, Point to, Runnable onDone) {
            clear();
            this.icon = icon;
            this.from = from;
            this.to = to;
            this.start = System.currentTimeMillis();

            animTimer = new javax.swing.Timer(10, e -> {
                long now = System.currentTimeMillis();
                float t = Math.min(1f, (now - start) / (float) durationMs);
                // ease-out cubic
                t = 1 - (float) Math.pow(1 - t, 3);
                repaint();
                if (t >= 1f) {
                    animTimer.stop();
                    Icon doneIcon = this.icon; // กัน NPE หาก clear ระหว่างทาง
                    clear();
                    if (onDone != null) onDone.run();
                }
            });
            animTimer.start();
        }

        void cancel() {
            if (animTimer != null) animTimer.stop();
            clear();
        }

        void clear() {
            icon = null;
            from = null;
            to = null;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (icon == null || from == null || to == null) return;

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            long now = System.currentTimeMillis();
            float t = Math.min(1f, (now - start) / (float) durationMs);
            t = 1 - (float) Math.pow(1 - t, 3); // ease-out cubic

            int x = Math.round(lerp(from.x, to.x, t));
            int y = Math.round(lerp(from.y, to.y, t));

            icon.paintIcon(this, g2, x, y);
            g2.dispose();
        }

        private float lerp(float a, float b, float t) {
            return a + (b - a) * t;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FootballerPuzzle().setVisible(true));
    }
}
