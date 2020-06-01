package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar extends JPanel implements ActionListener {
    private JButton saveButton;
    private JButton loadButton;

    private ToolbarListener toolbarListener;

    public Toolbar() {
        setBorder(BorderFactory.createEtchedBorder());

        saveButton = new JButton("Save");
        loadButton = new JButton("Load");

        saveButton.addActionListener(this);
        loadButton.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(saveButton);
        add(loadButton);
    }

    public void setToolbarListener(ToolbarListener toolbarListener) {
        this.toolbarListener = toolbarListener;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JButton clicked = (JButton)event.getSource();

        if (clicked == saveButton && toolbarListener != null) {
            toolbarListener.saveEventOccurred();
        }

        if (clicked == loadButton && toolbarListener != null) {
            toolbarListener.loadEventOccurred();
        }
    }
}
