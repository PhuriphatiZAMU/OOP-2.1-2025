package Game.Logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

/**
 * จัดการเกม: สร้าง/สุ่ม/ตรวจ solvable, นับ moves, timer, pause, callbacks
 */
public class PuzzleGame {
    private final int n;
    private PuzzleBoard board;
    private int moves;
    private boolean paused;

    private Timer timer;
    private long seconds;

    private Runnable onSolved = () -> {};
    private LongConsumer onTick = s -> {};
    private IntConsumer onMove = m -> {};

    private final Random rnd = new Random();

    public PuzzleGame(int n) {
        if (n < 2) throw new IllegalArgumentException("n must be >= 2");
        this.n = n;
    }

    public int getSize() { return n; }
    public PuzzleBoard getBoard() { return board; }
    public int getMoves() { return moves; }
    public long getElapsedSeconds() { return seconds; }
    public boolean isPaused() { return paused; }

    public void setOnSolved(Runnable r) { this.onSolved = r; }
    public void setOnTick(LongConsumer c) { this.onTick = c; }
    public void setOnMove(IntConsumer c) { this.onMove = c; }

    /**
     * toggle pause state (หยุด/เริ่ม) — คืนค่า paused ปัจจุบันหลังสลับ
     */
    public boolean togglePause() {
        paused = !paused;
        return paused;
    }

    /**
     * เริ่มเกมใหม่จาก fullImage:
     */
    public void newGame(BufferedImage fullImage) {
        // create board
        board = new PuzzleBoard(n);

        // slice into pieces with numbers overlay (1..n*n-1)
        List<PuzzleTile> pieces = sliceImageWithNumbers(fullImage, n);

        // prepare permutation 1..n*n-1
        List<Integer> perm = new ArrayList<>();
        for (int i = 1; i <= n * n - 1; i++) perm.add(i);

        // shuffle until solvable
        do {
            Collections.shuffle(perm, rnd);
        } while (!isSolvable(perm, n));

        // place tiles by perm, last cell = empty
        int idx = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (r == n - 1 && c == n - 1) {
                    board.setTile(r, c, new PuzzleTile(0, null));
                } else {
                    int id = perm.get(idx++);
                    PuzzleTile p = pieces.get(id - 1);
                    board.setTile(r, c, p);
                }
            }
        }

        // safety: if solved, swap two tiles
        if (board.isSolved()) {
            if (n * n - 1 >= 2) {
                PuzzleTile t00 = board.getTilesFlat().get(0);
                PuzzleTile t01 = board.getTilesFlat().get(1);
                board.setTile(0, 0, t01);
                board.setTile(0, 1, t00);
            }
        }

        // reset counters & timer
        moves = 0;
        seconds = 0;
        paused = false;
        onMove.accept(moves);
        onTick.accept(seconds);
        restartTimer();
    }

    /**
     * พยายามย้าย tile ที่ (r,c)
     */
    public boolean tryMove(int r, int c) {
        if (paused || board == null) return false;
        boolean moved = board.move(r, c);
        if (moved) {
            moves++;
            onMove.accept(moves);
            if (board.isSolved()) {
                stopTimer();
                onSolved.run();
            }
        }
        return moved;
    }

    // ---------------------- image slicing / tile numbering ----------------------

    /**
     * slice image to n x n pieces and draw number overlay for each piece (1..n*n-1)
     */
    private List<PuzzleTile> sliceImageWithNumbers(BufferedImage img, int n) {
        int square = Math.min(img.getWidth(), img.getHeight());
        int sx = (img.getWidth() - square) / 2;
        int sy = (img.getHeight() - square) / 2;
        BufferedImage cropped = img.getSubimage(sx, sy, square, square);

        // ensure tile size matches UI expected area (we'll return tiles sized to 305/n if possible)
        int tile = square / n;
        List<PuzzleTile> list = new ArrayList<>(n * n - 1);
        int id = 1;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (r == n - 1 && c == n - 1) break; // reserve empty
                BufferedImage piece = new BufferedImage(tile, tile, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = piece.createGraphics();
                // draw subimage region into piece
                g.drawImage(cropped,
                        0, 0, tile, tile,
                        c * tile, r * tile, (c + 1) * tile, (r + 1) * tile,
                        null);

                // overlay semi-transparent pill behind number + white number
                int fontSize = Math.max(12, tile / 4);
                g.setFont(new Font("SansSerif", Font.BOLD, fontSize));
                String text = String.valueOf(id);
                FontMetrics fm = g.getFontMetrics();
                int tw = fm.stringWidth(text);
                int th = fm.getAscent();
                int padX = Math.max(6, fontSize / 2);
                int padY = Math.max(4, fontSize / 3);
                int rectW = tw + padX * 2;
                int rectH = th + padY;
                int rx = tile - rectW - 8;
                int ry = tile - rectH - 8;
                g.setColor(new Color(0,0,0,140));
                g.fillRoundRect(rx, ry, rectW, rectH, 10, 10);
                g.setColor(Color.WHITE);
                int tx = rx + padX;
                int ty = ry + (rectH + th) / 2 - 2;
                g.drawString(text, tx, ty);

                g.dispose();

                list.add(new PuzzleTile(id, piece));
                id++;
            }
        }
        return list;
    }

    // ---------------------- solvability check ----------------------

    private boolean isSolvable(List<Integer> perm, int n) {
        int inversions = 0;
        for (int i = 0; i < perm.size(); i++) {
            for (int j = i + 1; j < perm.size(); j++) {
                if (perm.get(i) > perm.get(j)) inversions++;
            }
        }
        if (n % 2 == 1) {
            return inversions % 2 == 0;
        } else {
            int blankRowFromBottom = 1; // we place blank at last row
            if (blankRowFromBottom % 2 == 0) {
                return inversions % 2 == 1;
            } else {
                return inversions % 2 == 0;
            }
        }
    }

    // ---------------------- timer handling ----------------------

    private void restartTimer() {
        stopTimer();
        timer = new Timer("PuzzleTimer", true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!paused) {
                    seconds++;
                    onTick.accept(seconds);
                }
            }
        }, 1000, 1000);
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
