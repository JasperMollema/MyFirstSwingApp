package gui.game;

import javax.swing.*;
import java.awt.*;

public class GameComponent extends JComponent {
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());
    }
}
