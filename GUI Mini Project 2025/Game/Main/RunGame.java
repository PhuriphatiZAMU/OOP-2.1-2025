package Game.Main;

import javax.swing.SwingUtilities;

import Game.UI.FootballerPuzzle;

public class RunGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        new FootballerPuzzle().setVisible(true);
        });
    }
}
