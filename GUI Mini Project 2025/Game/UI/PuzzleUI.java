package Game.UI;

import Game.Logic.PuzzleBoard;
import Game.Logic.PuzzleGame;
import Game.Logic.PuzzleTile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;

public class PuzzleUI extends JPanel {
    private final PuzzleGame game;
    private final JPanel boardPanel = new JPanel();
    private final JButton[][] tileButtons;
    private final JLabel movesLabel = new JLabel("Moves: 0");
    private final JLabel timeLabel = new JLabel("Time: 00:00");
    private final JButton btnNew = new JButton("New Game");
    private final JButton btnPause = new JButton("Pause");

    private final ImageManager imageManager = new ImageManager();

    public PuzzleUI(PuzzleGame game) {
        this.game = game;
        int n = game.getSize();
        this.tileButtons = new JButton[n][n];

        setLayout(new BorderLayout(12, 12));
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        setBackground(new Color(38, 95, 78));
        setPreferredSize(new Dimension(383, 596));

        // Top: Logo + status
        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);



        // Status (time/moves)
        JPanel statusPanel = new JPanel(new GridLayout(1,2,8,8));
        statusPanel.setOpaque(false);
        styleStatusLabel(timeLabel);
        styleStatusLabel(movesLabel);
        statusPanel.add(timeLabel);
        statusPanel.add(movesLabel);

        top.add(statusPanel, BorderLayout.EAST);
        add(top, BorderLayout.NORTH);

        // Board area
        boardPanel.setLayout(new GridLayout(n, n, 3, 3));
        boardPanel.setBackground(new Color(38, 95, 78));
        boardPanel.setPreferredSize(new Dimension(305, 305));
        add(boardPanel, BorderLayout.CENTER);

        // Controls (big pause / new) at bottom
        JPanel controls = new JPanel(new GridBagLayout());
        controls.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.insets = new Insets(8,8,8,8);

        styleMainButton(btnNew);
        styleMainButton(btnPause);

        btnNew.addActionListener(e -> newGame());
        btnPause.addActionListener(e -> togglePause());

        gbc.gridx = 0; gbc.gridy = 0;
        controls.add(btnNew, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        controls.add(btnPause, gbc);

        add(controls, BorderLayout.SOUTH);

        initBoardButtons();

        // load images & start
        imageManager.loadDefaultImages();
        newGame();

        // callbacks from logic
        game.setOnTick(seconds -> SwingUtilities.invokeLater(() -> timeLabel.setText("Time: " + formatTime(seconds))));
        game.setOnMove(moves -> SwingUtilities.invokeLater(() -> movesLabel.setText("Moves: " + moves)));
        game.setOnSolved(() -> SwingUtilities.invokeLater(this::onSolved));
    }

    private void initBoardButtons() {
        boardPanel.removeAll();
        ActionListener click = e -> {
            if (game.isPaused()) return;
            JButton src = (JButton) e.getSource();
            int r = (int) src.getClientProperty("r");
            int c = (int) src.getClientProperty("c");
            if (game.tryMove(r, c)) refreshTiles();
        };

        int n = game.getSize();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                JButton b = new JButton();
                b.putClientProperty("r", r);
                b.putClientProperty("c", c);
                b.setFocusPainted(false);
                b.setOpaque(true);
                b.setBorder(new LineBorder(new Color(200,200,200), 2, true));
                b.setBackground(new Color(39, 75, 62));
                b.setForeground(Color.WHITE);
                b.setFont(new Font("SansSerif", Font.BOLD, 12));
                b.addActionListener(click);
                tileButtons[r][c] = b;
                boardPanel.add(b);
            }
        }
    }

    // สุ่มรูปต่างจากปัจจุบันแล้วเริ่มเกมใหม่
    public void newGame() {
        BufferedImage img = imageManager.randomImageDifferentFromCurrent();
        game.newGame(img);
        refreshTiles();
        btnPause.setText("Pause");
        setBoardEnabled(true);
    }

    // เปลี่ยนสถานะ pause: หยุด timer และปิดการคลิกกระดาน
    public void togglePause() {
        boolean nowPaused = game.togglePause();
        btnPause.setText(nowPaused ? "Resume" : "Pause");
        setBoardEnabled(!nowPaused);
    }

    private void setBoardEnabled(boolean enabled) {
        for (JButton[] row : tileButtons) for (JButton b : row) b.setEnabled(enabled);
    }

    private void refreshTiles() {
        PuzzleBoard board = game.getBoard();
        int n = game.getSize();
        List<PuzzleTile> tiles = board.getTilesFlat();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                JButton b = tileButtons[r][c];
                PuzzleTile t = tiles.get(r * n + c);
                if (t.isEmpty()) {
                    b.setIcon(null);
                    b.setText("");
                    b.setBackground(new Color(20, 50, 45)); // dark empty
                } else {
                    b.setBackground(null);
                    b.setText("");
                    b.setIcon(new ImageIcon(t.getImage()));
                }
            }
        }
        revalidate();
        repaint();
    }

    // เมื่อแก้เสร็จ: แสดงสถิติ + ภาพเต็ม + ปุ่ม Continue / Close
    private void onSolved() {
        setBoardEnabled(false);
        long seconds = game.getElapsedSeconds();
        int moves = game.getMoves();
        BufferedImage full = imageManager.getCurrentImage();
        Image scaled = full.getScaledInstance(305, 305, Image.SCALE_SMOOTH);
        JLabel pic = new JLabel(new ImageIcon(scaled));

        String msg = String.format("<html><b>Level Complete</b><br/>Moves: %d<br/>Time: %s</html>", moves, formatTime(seconds));
        JPanel panel = new JPanel(new BorderLayout(8,8)); 
        panel.add(new JLabel(msg), BorderLayout.NORTH);
        panel.add(pic, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(320, 400));


        Object[] options = {"Continue", "Close"};
        int choice = JOptionPane.showOptionDialog(this, panel, "Level Complete",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        imageManager.markCurrentCompleted();

        if (imageManager.allCompleted()) {
            JOptionPane.showMessageDialog(this, "Congratulations! You completed all images!", "All Done", JOptionPane.INFORMATION_MESSAGE);
        } else if (choice == JOptionPane.YES_OPTION) {
            // Continue -> next uncompleted random image
            BufferedImage next = imageManager.randomUncompletedImage();
            game.newGame(next);
            refreshTiles();
            setBoardEnabled(true);
        } else {
            // Close -> keep disabled until user New Game
            setBoardEnabled(false);
        }
    }

    // helper
    private String formatTime(long seconds) {
        long m = seconds / 60;
        long s = seconds % 60;
        return String.format("%02d:%02d", m, s);
    }

    private void styleMainButton(JButton b) {
        b.setPreferredSize(new Dimension(305, 56));
        b.setFocusPainted(false);
        b.setBorder(new LineBorder(new Color(170, 200, 190), 3, true));
        b.setBackground(new Color(38, 95, 78));
        b.setForeground(new Color(200, 220, 210));
        b.setFont(new Font("SansSerif", Font.BOLD, 18));
    }

    private void styleStatusLabel(JLabel l) {
        l.setOpaque(true);
        l.setBackground(new Color(39, 75, 62));
        l.setForeground(new Color(220, 240, 230));
        l.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        l.setHorizontalAlignment(SwingConstants.CENTER);
        l.setFont(new Font("SansSerif", Font.BOLD, 12));
    }
}
