package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Toolbar extends JPanel implements ActionListener {
    private JButton saveButton;
    private JButton loadButton;

    private ToolbarListener toolbarListener;

    public Toolbar() {
        setBorder(BorderFactory.createEtchedBorder());

        saveButton = new JButton("Save");
        saveButton.setIcon(createIcon("/images/Save16.gif"));
        loadButton = new JButton("Refresh");
        loadButton.setIcon(createIcon("/images/Refresh16.gif"));

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

    private ImageIcon createIcon(String path) {
        URL url = getClass().getResource(path);

        if (url == null) {
            System.out.println("Unable to load image " + path);
        }

        return new ImageIcon(url);
    }
}
