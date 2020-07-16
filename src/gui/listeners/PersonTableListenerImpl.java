package gui.listeners;

import controller.PersonController;
import gui.FormPerson;
import gui.TablePanel;
import gui.Toolbar;

import java.util.List;

public class PersonTableListenerImpl implements PersonTableListener {
    private PersonController personController;
    private TablePanel tablePanel;
    private Toolbar toolbar;

    public PersonTableListenerImpl(PersonController personController, TablePanel tablePanel, Toolbar toolbar) {
        this.personController = personController;
        this.tablePanel = tablePanel;
        this.toolbar = toolbar;
    }

    @Override
    public void rowChanged(int row, List<FormPerson> formPersonList) {
        personController.updatePerson(row, formPersonList.get(row));
        toolbar.setVisibilityUndoButton(true);
        toolbar.setVisibilityRedoButton(false);
    }

    @Override
    public void deleteRow(int row) {
        personController.deletePerson(row);
        tablePanel.fillTable(personController.getFormPersonList());
        tablePanel.refresh();
    }
}
