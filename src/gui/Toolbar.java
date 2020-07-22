package gui;

import gui.listeners.ToolbarListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static utils.Utils.createIcon;

public class Toolbar extends JToolBar implements ActionListener {
    private JButton saveButton;
    private JButton loadButton;
    private JButton retrieveMessageButton;
    private JButton previousButton;
    private JButton nextButton;
    private JButton gameButton;

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

        retrieveMessageButton = new JButton();
        retrieveMessageButton.setToolTipText("Retrieve Messages");
        retrieveMessageButton.setIcon(createIcon("/images/Search16.gif"));

        previousButton = new JButton();
        previousButton.setToolTipText("Undo last change");
        previousButton.setIcon(createIcon("/images/Undo24.gif"));
        previousButton.setVisible(false);

        nextButton = new JButton();
        nextButton.setToolTipText("Redo change");
        nextButton.setIcon(createIcon("/images/Redo24.gif"));
        nextButton.setVisible(false);

        gameButton = new JButton("Play Game");
        gameButton.setToolTipText("Play an awesome game");
        gameButton.setVisible(true);

        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        retrieveMessageButton.addActionListener(this);
        previousButton.addActionListener(this);
        nextButton.addActionListener(this);
        gameButton.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(saveButton);
        add(loadButton);
        add(retrieveMessageButton);
        add(previousButton);
        add(nextButton);
        add(gameButton);
    }

    public void setToolbarListener(ToolbarListener toolbarListener) {
        this.toolbarListener = toolbarListener;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JButton clicked = (JButton)event.getSource();

        if (toolbarListener == null) {
            return;
        }

        if (clicked == saveButton) {
            toolbarListener.saveEventOccurred();
        }

        if (clicked == loadButton) {
            toolbarListener.loadEventOccurred();
        }

        if (clicked == retrieveMessageButton) {
            toolbarListener.retrieveEventOccurred();
        }

        if (clicked == previousButton) {
            toolbarListener.undoEventOccurred();
        }

        if (clicked == nextButton) {
            toolbarListener.redoEventOccurred();
        }

        if (clicked == gameButton) {
            toolbarListener.gameEventOccurred();
        }
    }

    public void setVisibilityUndoButton(boolean isVisible) {
        previousButton.setVisible(isVisible);
    }

    public void setVisibilityRedoButton(boolean isVisible) {
        nextButton.setVisible(isVisible);
    }
}
