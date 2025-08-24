package Game.Logic;

import java.util.ArrayList;
import java.util.List;

/**
 * เก็บสถานะของกระดาน NxN (row-major)
 */
public class PuzzleBoard {
    private final int n;
    private final PuzzleTile[][] grid;
    private int emptyR, emptyC;

    public PuzzleBoard(int n) {
        this.n = n;
        this.grid = new PuzzleTile[n][n];
    }

    public int getSize() {
        return n;
    }

    public void setTile(int r, int c, PuzzleTile tile) {
        grid[r][c] = tile;
        if (tile.isEmpty()) {
            emptyR = r;
            emptyC = c;
        }
    }

    /**
     * ตรวจว่าตำแหน่ง (r,c) สามารถย้ายเข้าไปที่ช่องว่างได้ไหม (ติดกันแนวตั้ง/แนวนอน)
     */
    public boolean canMove(int r, int c) {
        int dr = Math.abs(r - emptyR);
        int dc = Math.abs(c - emptyC);
        return (dr + dc) == 1;
    }

    /**
     * ทำการสลับตำแหน่ง tile ที่ (r,c) กับช่องว่างแล้วอัพเดตตำแหน่งช่องว่าง
     * คืนค่า true เมื่อย้ายสำเร็จ
     */
    public boolean move(int r, int c) {
        if (!canMove(r, c)) return false;
        PuzzleTile t = grid[r][c];
        grid[emptyR][emptyC] = t;
        grid[r][c] = new PuzzleTile(0, null); // new empty tile
        emptyR = r;
        emptyC = c;
        return true;
    }

    /**
     * คืน list ของ tile ในลักษณะ row-major (ใช้สำหรับอัพเดต UI)
     */
    public List<PuzzleTile> getTilesFlat() {
        List<PuzzleTile> list = new ArrayList<>(n * n);
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                list.add(grid[r][c]);
            }
        }
        return list;
    }

    /**
     * ตรวจสอบสถานะว่าจัดเรียงถูกต้องหรือยัง (เรียง 1..n*n-1 แล้วช่องสุดท้ายเป็นว่าง)
     */
    public boolean isSolved() {
        int want = 1;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                // last cell must be empty
                if (r == n - 1 && c == n - 1) {
                    if (!grid[r][c].isEmpty()) return false;
                } else {
                    PuzzleTile t = grid[r][c];
                    if (t == null || t.isEmpty()) return false;
                    if (t.getId() != want) return false;
                    want++;
                }
            }
        }
        // all checks passed
        return true;
    }
}
