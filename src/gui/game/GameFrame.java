package gui.game;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        System.out.println("Start new game");
        setSize(600, 500);
        setMinimumSize(new Dimension(500, 400));
        setVisible(true);

        add(new GameComponent());
    }
}
