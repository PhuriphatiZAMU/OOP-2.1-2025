package FootballerImage;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.net.URL;

public class FootballerPuzzle extends JFrame {

    private JButton[][] buttons;
    private int emptyRow = 3, emptyCol = 3;
    private int moves = 0;
    private javax.swing.Timer timer;
    private int seconds = 0;

    private JPanel panelBoard;
    private JButton btnPause, btnNewGame;
    private JLabel lblMoves, lblTime, lblLevel;

    private Font font;
    private boolean isPaused = false; // ✅ ตัวแปร pause

    {
        try {
            URL fontUrl = new URL(
                "https://fonts.googleapis.com/css2?family=Prompt:wght@400;500;600&display=swap");
            font = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream())
                       .deriveFont(Font.PLAIN, 16f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (Exception ex) {
            font = new Font("Prompt", Font.PLAIN, 10);
        }
    }

    private final String[] images = {
        "/FootballerImage/Lionel-Messi-2014-15.jpg",
        "/FootballerImage/Neymar-2014-15.jpg",
        "/FootballerImage/Cristiano-Ronaldo-2017-18.jpg",
        "/FootballerImage/Luis-Suarez-2014-15.jpg",
        "/FootballerImage/Kevin-De-Bruyne-2022-23.jpg"
    };

    private int currentLevel = 1;
    private int currentImageIndex = 0;

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

        panelBoard = new JPanel();
        panelBoard.setBounds(30, 100, 340, 340);
        panelBoard.setLayout(new GridLayout(4, 4, 1, 1)); // ✅ ชิดกันเหลือห่าง 1
        panelBoard.setBackground(new Color(100, 100, 100)); // ✅ BG เขียวพาสเทลเข้ม
        add(panelBoard);

        btnNewGame = new JButton("NEW GAME");
        btnNewGame.setFocusPainted(false);
        btnNewGame.setFont(font.deriveFont(Font.BOLD, 14f));
        btnNewGame.setBounds(30, 30, 120, 30);
        btnNewGame.addActionListener(e -> newGame());
        add(btnNewGame);

        lblLevel = new JLabel("Level: 1");
        lblLevel.setBounds(160, 30, 80, 30);
        lblLevel.setFont(font.deriveFont(Font.BOLD, 10f));
        add(lblLevel);

        lblTime = new JLabel("Time: 0s");
        lblTime.setBounds(240, 30, 80, 30);
        lblTime.setFont(font.deriveFont(Font.BOLD, 10f));
        add(lblTime);

        lblMoves = new JLabel("Moves: 0");
        lblMoves.setBounds(320, 30, 80, 30);
        lblMoves.setFont(font.deriveFont(Font.BOLD, 10f));
        add(lblMoves);

        btnPause = new JButton("Pause");
        btnPause.setBounds(30, 460, 340, 40);
        btnPause.setFont(font.deriveFont(Font.BOLD, 14f));
        btnPause.setFocusPainted(false);
        btnPause.addActionListener(e -> {
            if (timer != null) {
                if (!isPaused) {
                    timer.stop();
                    btnPause.setText("Resume");
                    isPaused = true;  // ✅ หยุดเกม
                } else {
                    timer.start();
                    btnPause.setText("Pause");
                    isPaused = false; // ✅ เล่นต่อ
                }
            }
        });
        add(btnPause);
    }

    private void newGame() {
        currentLevel = 1;
        currentImageIndex = (int) (Math.random() * images.length);
        initGame();
    }

    private void initGame() {
        panelBoard.removeAll();
        buttons = new JButton[4][4];
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 16; i++) numbers.add(i);
        Collections.shuffle(numbers);

        String imagePath = images[currentImageIndex];

        try {
            BufferedImage img = ImageIO.read(getClass().getResource(imagePath));
            int tileSize = img.getWidth() / 4;

            int index = 0;
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    int value = numbers.get(index++);
                    JButton btn;
                    if (value == 15) {
                        btn = new JButton();
                        emptyRow = row;
                        emptyCol = col;
                    } else {
                        int r = value / 4;
                        int c = value % 4;
                        BufferedImage subImg = img.getSubimage(c * tileSize, r * tileSize, tileSize, tileSize);
                        subImg = drawNumberOnTile(subImg, value + 1); // ✅ เพิ่มเลข
                        btn = new JButton(new ImageIcon(subImg));
                    }

                    btn.setBorder(null);
                    btn.setFocusPainted(false);
                    final int rClick = row, cClick = col;
                    btn.addActionListener(e -> moveTile(rClick, cClick));
                    buttons[row][col] = btn;
                    panelBoard.add(btn);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        moves = 0;
        lblMoves.setText("Moves: 0");
        seconds = 0;
        lblTime.setText("Time: 0s");
        lblLevel.setText("Level: " + currentLevel);

        if (timer != null) timer.stop();
        timer = new javax.swing.Timer(1000, e -> {
            seconds++;
            lblTime.setText("Time: " + seconds + "s");
        });
        timer.start();

        panelBoard.revalidate();
        panelBoard.repaint();
    }

    private void moveTile(int row, int col) {
        if (isPaused) return; // ✅ ห้ามขยับถ้า pause

        if ((Math.abs(row - emptyRow) == 1 && col == emptyCol) ||
            (Math.abs(col - emptyCol) == 1 && row == emptyRow)) {

            animateTile(row, col, emptyRow, emptyCol); // ✅ Smooth slide animation

            emptyRow = row;
            emptyCol = col;

            moves++;
            lblMoves.setText("Moves: " + moves);

            if (checkWin()) {
                timer.stop();
                JOptionPane.showMessageDialog(this,
                    "Level " + currentLevel + " Completed!\nMoves: " + moves + "\nTime: " + seconds + "s");

                currentLevel++;
                currentImageIndex = (currentImageIndex + 1) % images.length;
                initGame();
            }
        }
    }

    /** ✅ Animation slide */
    private void animateTile(int fromRow, int fromCol, int toRow, int toCol) {
        JButton btn = buttons[fromRow][fromCol];
        Icon icon = btn.getIcon();
        btn.setIcon(null);

        JWindow animWindow = new JWindow();
        animWindow.setBackground(new Color(0,0,0,0));
        JLabel animLabel = new JLabel(icon);
        animWindow.add(animLabel);
        Point from = btn.getLocationOnScreen();
        Point to = buttons[toRow][toCol].getLocationOnScreen();
        animWindow.setBounds(from.x, from.y, btn.getWidth(), btn.getHeight());
        animWindow.setVisible(true);

        Timer animTimer = new Timer(10, null);
        final int steps = 20;
        final int[] step = {0};
        int dx = (to.x - from.x) / steps;
        int dy = (to.y - from.y) / steps;

        animTimer.addActionListener(e -> {
            if (step[0] < steps) {
                animWindow.setLocation(from.x + dx * step[0], from.y + dy * step[0]);
                step[0]++;
            } else {
                animTimer.stop();
                animWindow.dispose();
                buttons[toRow][toCol].setIcon(icon);
            }
        });
        animTimer.start();
    }

    private boolean checkWin() {
        int correct = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (row == 3 && col == 3) {
                    if (buttons[row][col].getIcon() != null) return false;
                } else {
                    Icon icon = buttons[row][col].getIcon();
                    if (icon == null) return false;

                    ImageIcon expected = getTileIcon(correct, currentImageIndex);
                    if (!iconsEqual((ImageIcon) icon, expected)) return false;
                    correct++;
                }
            }
        }
        return true;
    }

    private ImageIcon getTileIcon(int value, int imageIndex) {
        try {
            BufferedImage img = ImageIO.read(getClass().getResource(images[imageIndex]));
            int tileSize = img.getWidth() / 4;
            int r = value / 4;
            int c = value % 4;
            BufferedImage subImg = img.getSubimage(c * tileSize, r * tileSize, tileSize, tileSize);
            subImg = drawNumberOnTile(subImg, value + 1);
            return new ImageIcon(subImg);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean iconsEqual(ImageIcon icon1, ImageIcon icon2) {
        if (icon1 == null || icon2 == null) return false;
        return icon1.getImage().equals(icon2.getImage());
    }

    /** ✅ ฟังก์ชันวาดเลขบน tile */
    private BufferedImage drawNumberOnTile(BufferedImage tile, int number) {
        BufferedImage newTile = new BufferedImage(tile.getWidth(), tile.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newTile.createGraphics();
        g.drawImage(tile, 0, 0, null);

        g.setFont(font.deriveFont(Font.BOLD, 18f));
        String text = String.valueOf(number);

        FontMetrics fm = g.getFontMetrics();
        int x = tile.getWidth() - fm.stringWidth(text) - 5;
        int y = tile.getHeight() - 5;

        g.setColor(Color.BLACK);
        g.drawString(text, x + 1, y + 1);

        g.setColor(Color.WHITE);
        g.drawString(text, x, y);

        g.dispose();
        return newTile;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FootballerPuzzle().setVisible(true));
    }
}