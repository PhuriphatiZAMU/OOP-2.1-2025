package Game.UI;


import Game.Logic.PuzzleGame;


import javax.swing.*;
import java.awt.*;


public class FootballerPuzzle extends JFrame {
    private final PuzzleUI puzzleUI;


    public FootballerPuzzle() {
        super("Footballer Puzzle");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(383, 596));
        setPreferredSize(new Dimension(383, 596));
        setLocationRelativeTo(null);


        // Apply basic UIManager styles for a consistent theme
        UIManager.put("Label.font", new Font("SansSerif", Font.BOLD, 16));
        UIManager.put("Button.font", new Font("SansSerif", Font.BOLD, 20));


        // create game logic and UI
        PuzzleGame game = new PuzzleGame(4); // 4x4 puzzle
        puzzleUI = new PuzzleUI(game);
        setLayout(new BorderLayout());
        add(puzzleUI, BorderLayout.CENTER);


        setJMenuBar(buildMenuBar());
        pack();
    }


    private JMenuBar buildMenuBar() {
        JMenuBar mb = new JMenuBar();
        JMenu gameMenu = new JMenu();


        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(e -> puzzleUI.newGame());


        JMenuItem pause = new JMenuItem("Pause/Resume");
        pause.addActionListener(e -> puzzleUI.togglePause());


        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> dispose());


        gameMenu.add(newGame);
        gameMenu.add(pause);
        gameMenu.addSeparator();
        gameMenu.add(exit);
        mb.add(gameMenu);
        return mb;
    }
}