package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static gui.Utils.createIcon;

public class Toolbar extends JToolBar implements ActionListener {
    private JButton saveButton;
    private JButton loadButton;

    private ToolbarListener toolbarListener;

    public Toolbar() {
        // Do not set border if you want toolbar to be draggable.
        setBorder(BorderFactory.createEtchedBorder());

        saveButton = new JButton();
        saveButton.setToolTipText("Save");
        saveButton.setIcon(createIcon("/images/Save16.gif"));
        loadButton = new JButton();
        loadButton.setToolTipText("Refresh");
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
}
