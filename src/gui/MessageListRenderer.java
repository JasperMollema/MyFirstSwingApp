package gui;

import model.Message;
import utils.Utils;

import javax.swing.*;
import java.awt.*;

import static utils.Utils.createIcon;

/**
 *  Note -- this demostrates using a arbitrary component as a list box renderer.
 *  Probably overkill in this case to use JPanel when JLabel could be used directly.
 */

public class MessageListRenderer implements ListCellRenderer {
    private JPanel panel;
    private JLabel label;
    private Font labelFont;

    private Color selectedColor;
    private Color normalColor;

    public MessageListRenderer() {
        panel = new JPanel();
        label = new JLabel();
        labelFont = Utils.createTrueTypeFont("/fonts/JMHTypewriter.ttf").deriveFont(Font.PLAIN, 18);

        label.setFont(labelFont);

        selectedColor = new Color(210, 210, 255);
        normalColor = Color.WHITE;

        label.setIcon(createIcon("/images/Information24.gif"));

        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(label);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        Message message = (Message) value;

        label.setText(message.getTitle());
        panel.setBackground(cellHasFocus ? selectedColor : normalColor);

        return panel;
    }
}
