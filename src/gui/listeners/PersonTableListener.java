package gui.listeners;

import gui.FormPerson;

import java.util.List;

public interface PersonTableListener {
    void rowChanged(int row, List<FormPerson> formPersonList);
    void deleteRow(int row);
}
