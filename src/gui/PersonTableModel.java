package gui;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class PersonTableModel extends AbstractTableModel {
    private List<FormPerson> formPersonList;
    private TableModelListener tableModelListener;

    public final int NAME_COLUMN = 0;
    public final int OCCUPATION_COLUMN = 1;
    public final int AGE_CATEGORY_COLUMN = 2;
    public final int MARITAL_STATUS_COLUMN = 3;
    public final int GENDER_COLUMN = 4;
    public final int CLUB_MEMBER_COLUMN = 5;
    public final int MEMBER_ID_COLUMN = 6;

    private String[] columnNames = {"Name", "Occupation","Age Category",
            "Marital Status", "Gender", "Club Member", "Member Id"};

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case NAME_COLUMN:
            case CLUB_MEMBER_COLUMN:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (formPersonList == null) {
            return;
        }

        FormPerson formPerson = formPersonList.get(rowIndex);

        switch (columnIndex) {
            case NAME_COLUMN:
                formPerson.name = aValue.toString();
                break;
            case CLUB_MEMBER_COLUMN:
                formPerson.isClubMember = (Boolean) aValue;
                break;
        }

        tableModelListener.tableChanged(new TableModelEvent(this, rowIndex));
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case NAME_COLUMN:
            case AGE_CATEGORY_COLUMN:
            case OCCUPATION_COLUMN:
            case MARITAL_STATUS_COLUMN:
            case GENDER_COLUMN:
            case MEMBER_ID_COLUMN:
                return String.class;
            case CLUB_MEMBER_COLUMN:
                return Boolean.class;
            default:
                return null;
        }
    }

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

    public void setTableModelListener(TableModelListener tableModelListener) {
        this.tableModelListener = tableModelListener;
    }

    public List<FormPerson> getFormPersonList() {
        return formPersonList;
    }
}
