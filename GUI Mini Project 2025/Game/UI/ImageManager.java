package Game.UI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * ImageManager - โหลดรูปจาก resources (/images/...) หรือจากโฟลเดอร์ภายนอกชื่อ "FootballerImage"
 *
 * การค้นหาไฟล์:
 * 1) classpath resource "/images/<name>.<ext>"
 * 2) ./FootballerImage/<name>.<ext>
 * 3) ../FootballerImage/<name>.<ext>  (ขึ้นไปหาจนถึง 5 ชั้น)
 *
 * ชื่อไฟล์ในชุดเริ่มต้น: footballer1..footballer4 (รองรับ .jpg .jpeg .png)
 */
public class ImageManager {
    private final List<BufferedImage> images = new ArrayList<>();
    private final Set<Integer> completed = new HashSet<>();
    private int currentIndex = -1;
    private final Random rnd = new Random();

    // base names (ไม่ต้องมีนามสกุล)
    private final String[] baseNames = {
            "Lionel-Messi-2014-15",
            "Neymar-2014-15",
            "Luis-Suarez-2014-15",
            "Cristiano-Ronaldo-2017-18",
            "Kevin-De-Bruyne-2022-23",
            "Andrés-Iniesta-2010-11",
            "Xavier-Hernández-2010-11",
            "Manuel-Neuer-2012-13",
    };

    private final String[] exts = {".jpg", ".jpeg", ".png"};

    /**
     * โหลดชุดภาพเริ่มต้น
     */

    public void loadDefaultImages() {
        images.clear();
        completed.clear();
        currentIndex = -1;

        for (String base : baseNames) {
            BufferedImage img = tryLoadAny(base);
            if (img != null) images.add(img);
        }

        // ถ้าไม่เจอภาพเลย ให้ fallback ขนาด 305x305
        if (images.isEmpty()) images.add(generateFallback(305, 305));
    }

    /**
     * พยายามโหลดภาพปัจจุบัน (set currentIndex = 0 ถ้ายังไม่ถูกตั้ง)
     */
    public BufferedImage getCurrentImage() {
        if (currentIndex < 0 && !images.isEmpty()) currentIndex = 0;
        return images.get(currentIndex);
    }

    public int getIndex() { return currentIndex; }

    /**
     * เลือกรูปแบบสุ่มที่ต่างจากรูปปัจจุบัน (ใช้ตอน New Game)
     */

    public BufferedImage randomImageDifferentFromCurrent() {
        if (images.isEmpty()) throw new IllegalStateException("No images loaded");
        if (images.size() == 1) {
            currentIndex = 0;
            return images.get(0);
        }
        int idx;
        do {
            idx = rnd.nextInt(images.size());
        } while (idx == currentIndex);
        currentIndex = idx;
        return images.get(currentIndex);
    }

    /**
     * เลือกภาพที่ยังไม่ผ่าน (uncompleted) แบบสุ่ม
     */

    public BufferedImage randomUncompletedImage() {
        List<Integer> candidates = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            if (!completed.contains(i)) candidates.add(i);
        }
        if (candidates.isEmpty()) {
            currentIndex = 0;
            return images.get(currentIndex);
        }
        currentIndex = candidates.get(rnd.nextInt(candidates.size()));
        return images.get(currentIndex);
    }

    public void markCurrentCompleted() {
        if (currentIndex >= 0) completed.add(currentIndex);
    }

    public boolean allCompleted() {
        return !images.isEmpty() && completed.size() >= images.size();
    }

    // ---------------- helpers ----------------

    /**
     * พยายามโหลด resource หรือไฟล์จากโฟลเดอร์ FootballerImage
     */
    
    private BufferedImage tryLoadAny(String baseName) {
        // 1) try classpath resource /images/<base><ext>
        for (String ext : exts) {
            String resPath = "/images/" + baseName + ext;
            URL url = getClass().getResource(resPath);
            if (url != null) {
                try {
                    return ImageIO.read(url);
                } catch (IOException ignored) {}
            }
        }

        // 2) try ./FootballerImage/<base><ext>
        String userDir = System.getProperty("user.dir");
        File tryDir = new File(userDir, "FootballerImage");
        BufferedImage img = tryLoadFromDir(tryDir, baseName);
        if (img != null) return img;

        // 3) search upward for a FootballerImage folder (up to 5 levels)
        File folder = findFolderUpwards(new File(userDir), "FootballerImage", 5);
        if (folder != null) {
            img = tryLoadFromDir(folder, baseName);
            if (img != null) return img;
        }

        // 4) try one more common possibility: parent dir + "GUI Mini Project 2025/FootballerImage"
        File parent = new File(userDir).getParentFile();
        if (parent != null) {
            File alt = new File(parent, "FootballerImage");
            img = tryLoadFromDir(alt, baseName);
            if (img != null) return img;
            File alt2 = new File(parent, "GUI Mini Project 2025" + File.separator + "FootballerImage");
            img = tryLoadFromDir(alt2, baseName);
            if (img != null) return img;
        }

        // none found
        return null;
    }

    private BufferedImage tryLoadFromDir(File dir, String baseName) {
        if (dir == null || !dir.exists() || !dir.isDirectory()) return null;
        for (String ext : exts) {
            File f = new File(dir, baseName + ext);
            if (f.exists() && f.isFile()) {
                try {
                    return ImageIO.read(f);
                } catch (IOException ignored) {}
            }
        }
        return null;
    }

    /**
     * ค้นหาโฟลเดอร์ที่ชื่อ targetName ขึ้นไปจนถึง maxDepth ชั้น
     */
    private File findFolderUpwards(File start, String targetName, int maxDepth) {
        File cur = start;
        for (int depth = 0; depth <= maxDepth && cur != null; depth++) {
            File candidate = new File(cur, targetName);
            if (candidate.exists() && candidate.isDirectory()) return candidate;
            cur = cur.getParentFile();
        }
        return null;
    }

    private BufferedImage generateFallback(int w, int h) {
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        java.awt.Graphics2D g = img.createGraphics();
        g.setColor(new java.awt.Color(120, 160, 140));
        g.fillRect(0, 0, w, h);
        g.setColor(java.awt.Color.WHITE);
        g.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 28));
        String text = "No Image";
        int tw = g.getFontMetrics().stringWidth(text);
        g.drawString(text, Math.max(10, (w - tw) / 2), h / 2);
        g.dispose();
        return img;
    }
}
