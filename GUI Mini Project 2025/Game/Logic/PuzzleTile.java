package Game.Logic;

import java.awt.image.BufferedImage;

/**
 * ตัวแทนของแผ่นปริศนาแต่ละชิ้น
 * id: 1..(n*n-1)  — 0 = ช่องว่าง (empty)
 */
public class PuzzleTile {
    private final int id;
    private BufferedImage image;

    public PuzzleTile(int id, BufferedImage image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public boolean isEmpty() {
        return id == 0;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
