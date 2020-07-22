package gui.game;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    public Game() {
        System.out.println("Start new game");
        setSize(600, 500);
        setMinimumSize(new Dimension(500, 400));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
