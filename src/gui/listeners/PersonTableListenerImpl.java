package gui.listeners;

import controller.PersonController;
import gui.FormPerson;
import gui.TablePanel;

import java.util.List;

public class PersonTableListenerImpl implements PersonTableListener {
    private PersonController personController;
    private TablePanel tablePanel;

    public PersonTableListenerImpl(PersonController personController, TablePanel tablePanel) {
        this.personController = personController;
        this.tablePanel = tablePanel;
    }

    @Override
    public void rowChanged(int row, List<FormPerson> formPersonList) {
        personController.update(row, formPersonList.get(row));
    }

    @Override
    public void deleteRow(int row) {
        personController.deletePerson(row);
        tablePanel.fillTable(personController.getFormPersonList());
        tablePanel.refresh();
    }
}
