import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Puzzle15 extends JFrame {
    // --- Game config ---
    private final int SIZE = 4;                 // 4x4 grid
    private final String TITLE = "15 Puzzle — Java Swing";

    // --- State ---
    private int[] tiles;                        // 0 = blank, 1..15 = numbers
    private int blankIndex;                     // index (0..15) of blank tile
    private int moves = 0;                      // move counter
    private int seconds = 0;                    // elapsed seconds

    // --- UI ---
    private JPanel boardPanel;
    private JButton[] buttons;
    private JLabel timeLabel;
    private JLabel movesLabel;
    private Timer timer;                        // Swing timer for stopwatch

    // --- Colors / Styling ---
    private final Color BG = new Color(246, 248, 250);
    private final Color TILE = new Color(238, 242, 255);
    private final Color TILE_TEXT = new Color(31, 41, 55);
    private final Color TILE_HOVER = new Color(224, 231, 255);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Puzzle15().setVisible(true));
    }

    public Puzzle15() {
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        buildUI();
        pack();
        setLocationRelativeTo(null);
        newGame();
    }

    // ---------------- UI Construction ----------------
    private void buildUI() {
        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(BG);

        // Top bar: title + stats
        JPanel top = new JPanel(new BorderLayout());
        top.setBorder(BorderFactory.createEmptyBorder(12, 12, 0, 12));
        top.setBackground(BG);

        JLabel title = new JLabel("15 Puzzle");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 22f));
        top.add(title, BorderLayout.WEST);

        JPanel stats = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        stats.setOpaque(false);
        timeLabel = new JLabel("Time: 00:00");
        movesLabel = new JLabel("Moves: 0");
        timeLabel.setFont(timeLabel.getFont().deriveFont(Font.PLAIN, 14f));
        movesLabel.setFont(movesLabel.getFont().deriveFont(Font.PLAIN, 14f));
        stats.add(timeLabel);
        stats.add(movesLabel);
        top.add(stats, BorderLayout.EAST);

        getContentPane().add(top, BorderLayout.NORTH);

        // Board panel 4x4
        boardPanel = new JPanel(new GridLayout(SIZE, SIZE, 8, 8));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        boardPanel.setBackground(BG);
        buttons = new JButton[SIZE * SIZE];

        for (int i = 0; i < buttons.length; i++) {
            JButton b = new JButton();
            b.setFocusPainted(false);
            b.setBackground(TILE);
            b.setForeground(TILE_TEXT);
            b.setFont(b.getFont().deriveFont(Font.BOLD, 20f));
            b.setPreferredSize(new Dimension(80, 80));
            final int index = i;
            b.addActionListener(e -> handleTileClick(index));
            b.addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) { b.setBackground(TILE_HOVER); }
                @Override public void mouseExited(MouseEvent e)  { b.setBackground(TILE); }
            });
            buttons[i] = b;
            boardPanel.add(b);
        }
        getContentPane().add(boardPanel, BorderLayout.CENTER);

        // Bottom controls
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        bottom.setBackground(BG);
        bottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));

        JButton newGameBtn = new JButton("New Game");
        JButton resetBtn   = new JButton("Reset Timer/Counter");
        JButton exitBtn    = new JButton("Exit");

        newGameBtn.addActionListener(e -> newGame());
        resetBtn.addActionListener(e -> resetStats());
        exitBtn.addActionListener(e -> System.exit(0));

        bottom.add(newGameBtn);
        bottom.add(resetBtn);
        bottom.add(exitBtn);

        getContentPane().add(bottom, BorderLayout.SOUTH);

        // Timer: 1 second tick
        timer = new Timer(1000, e -> {
            seconds++;
            refreshStats();
        });
    }

    // ---------------- Game Control ----------------
    private void newGame() {
        // initialize array 0..15 (0=blank) and shuffle to a solvable state
        tiles = new int[SIZE * SIZE];
        for (int i = 0; i < tiles.length; i++) tiles[i] = i; // 0..15

        shuffleSolvable();
        moves = 0;
        seconds = 0;
        refreshBoard();
        refreshStats();
        timer.restart();
    }

    private void resetStats() {
        moves = 0;
        seconds = 0;
        refreshStats();
    }

    private void handleTileClick(int index) {
        if (tiles == null || index < 0 || index >= tiles.length) return;
        if (isNeighborOfBlank(index)) {
            swap(index, blankIndex);
            moves++;
            refreshBoard();
            refreshStats();
            if (isSolved()) {
                timer.stop();
                showWinDialog();
            }
        }
    }

    private void showWinDialog() {
        String timeStr = formatTime(seconds);
        String msg = "You Win!\nMoves: " + moves + "\nTime: " + timeStr + "\n\nPlay again?";
        int choice = JOptionPane.showConfirmDialog(this, msg, "Victory", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            newGame();
        }
    }

    // ---------------- Board/State helpers ----------------
    private void refreshBoard() {
        for (int i = 0; i < tiles.length; i++) {
            JButton b = buttons[i];
            int val = tiles[i];
            if (val == 0) {
                b.setText("");
                b.setEnabled(true);
            } else {
                b.setText(String.valueOf(val));
                b.setEnabled(true);
            }
        }
        // locate blank
        for (int i = 0; i < tiles.length; i++) if (tiles[i] == 0) { blankIndex = i; break; }
    }

    private void refreshStats() {
        timeLabel.setText("Time: " + formatTime(seconds));
        movesLabel.setText("Moves: " + moves);
    }

    private String formatTime(int s) {
        int m = s / 60;
        int sec = s % 60;
        return String.format("%02d:%02d", m, sec);
    }

    private boolean isNeighborOfBlank(int index) {
        int r = index / SIZE, c = index % SIZE;
        int br = blankIndex / SIZE, bc = blankIndex % SIZE;
        return (Math.abs(r - br) + Math.abs(c - bc)) == 1; // Manhattan distance 1
    }

    private void swap(int i, int j) {
        int t = tiles[i];
        tiles[i] = tiles[j];
        tiles[j] = t;
        if (tiles[j] == 0) blankIndex = j;
        if (tiles[i] == 0) blankIndex = i;
    }

    private boolean isSolved() {
        for (int i = 0; i < tiles.length - 1; i++) {
            if (tiles[i] != i + 1) return false;
        }
        return tiles[tiles.length - 1] == 0;
    }

    // ---------------- Shuffling with solvability ----------------
    private void shuffleSolvable() {
        // Create list 0..15 and shuffle until solvable and not already solved
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < SIZE * SIZE; i++) list.add(i);

        Random rnd = new Random();
        do {
            Collections.shuffle(list, rnd);
        } while (!isSolvable(list) || isSolvedPermutation(list));

        for (int i = 0; i < list.size(); i++) tiles[i] = list.get(i);
        for (int i = 0; i < tiles.length; i++) if (tiles[i] == 0) { blankIndex = i; break; }
    }

    private boolean isSolvedPermutation(List<Integer> perm) {
        for (int i = 0; i < perm.size() - 1; i++) if (perm.get(i) != i + 1) return false;
        return perm.get(perm.size() - 1) == 0;
    }

    /**
     * Solvability for 4x4 15-puzzle:
     * - Let inv = number of inversions in the flattened list (excluding 0).
     * - Let blankRowFromBottom = 1..4 (1 = bottom row).
     * - For even grid width (4), puzzle is solvable iff:
     *     - blankRowFromBottom is even and inv is odd, OR
     *     - blankRowFromBottom is odd  and inv is even.
     */
    private boolean isSolvable(List<Integer> perm) {
        int inv = 0;
        for (int i = 0; i < perm.size(); i++) {
            int a = perm.get(i);
            if (a == 0) continue;
            for (int j = i + 1; j < perm.size(); j++) {
                int b = perm.get(j);
                if (b == 0) continue;
                if (a > b) inv++;
            }
        }
        int blankIdx = perm.indexOf(0);
        int blankRow = blankIdx / SIZE;                // 0..3 from top
        int blankRowFromBottom = SIZE - blankRow;      // 1..4 from bottom

        boolean widthEven = (SIZE % 2 == 0);
        if (widthEven) {
            // Classic 4x4 rule
            boolean cond = (blankRowFromBottom % 2 == 0 && inv % 2 == 1) ||
                           (blankRowFromBottom % 2 == 1 && inv % 2 == 0);
            return cond;
        } else {
            return inv % 2 == 0;
        }
    }
}
