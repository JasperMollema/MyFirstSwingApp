package gui;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class PersonTableModel extends AbstractTableModel {
    List<FormPerson> formPersonList;
    private String[] columnNames = {"Id", "Name", "Occupation","Age Category",
            "Marital Status", "Gender", "Club Member", "Member Id"};

    public PersonTableModel() {
        formPersonList = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return formPersonList.size();
    }

    @Override
    public int getColumnCount() {
        return FormPerson.COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int row, int column) {
        return formPersonList.get(row).getValue(column);
    }

    @Override
    public String getColumnName(int column) {
       return columnNames[column];
    }

    public void fillPersonTable(List<FormPerson> formPersonList) {
        this.formPersonList = formPersonList;
    }
}
