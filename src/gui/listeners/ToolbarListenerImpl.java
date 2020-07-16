package gui.listeners;

import controller.PersonController;
import gui.MainFrame;
import gui.MessagePanel;
import gui.TablePanel;
import gui.Toolbar;

import javax.swing.*;
import java.sql.SQLException;

public class ToolbarListenerImpl implements ToolbarListener {
    private PersonController personController;
    private TablePanel tablePanel;
    private MessagePanel messagePanel;
    private Toolbar toolbar;

    public ToolbarListenerImpl(PersonController personController, TablePanel tablePanel, MessagePanel messagePanel, Toolbar toolbar) {
        this.personController = personController;
        this.tablePanel = tablePanel;
        this.messagePanel = messagePanel;
        this.toolbar = toolbar;
    }

    public void saveEventOccurred() {
        if (!personController.isTableChanged()) {
            JOptionPane.showMessageDialog(
                    MainFrame.class.cast(MainFrame.getFrames()),
                    "There are no changes to save",
                    "No changes made",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }

        if (personController.arePersonsDeleted()) {
            int action = JOptionPane.showConfirmDialog(
                    MainFrame.class.cast(MainFrame.getFrames()),
                    "You have deleted a person, are you sure you want to save these changes?",
                    "Persons deleted",
                    JOptionPane.OK_CANCEL_OPTION);

            if (action == JOptionPane.CANCEL_OPTION){
                return;
            }
        }

        try {
            personController.savePersons();
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(
                    MainFrame.class.cast(MainFrame.getFrames()),
                    "Unable to save to database" ,
                    "Database Connection Problem",
                    JOptionPane.ERROR_MESSAGE);
        }
        tablePanel.refresh();
    }

    @Override
    public void loadEventOccurred() {
        refreshPersonTable();
    }

    @Override
    public void retrieveEventOccurred() {
        messagePanel.refresh();
    }

    @Override
    public void undoEventOccurred() {
        personController.undo();
        toolbar.setVisibilityUndoButton(personController.hasPreviousUpdate());
        toolbar.setVisibilityRedoButton(personController.hasNextUpdate());
        tablePanel.fillTable(personController.getFormPersonList());
        tablePanel.refresh();
    }

    @Override
    public void redoEventOccurred() {
        personController.redo();
        tablePanel.fillTable(personController.getFormPersonList());
        tablePanel.refresh();
    }

    private void refreshPersonTable() {
        try {
            if (!personController.loadPersons()) {
                JOptionPane.showMessageDialog(
                        MainFrame.class.cast(MainFrame.getFrames()),
                        "There are no persons to load",
                        "No persons available",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(
                    MainFrame.class.cast(MainFrame.getFrames()),
                    "Unable to load from database" ,
                    "Database Connection Problem",
                    JOptionPane.ERROR_MESSAGE);
        }

        tablePanel.fillTable(personController.getFormPersonList());
        tablePanel.refresh();
    }
}
