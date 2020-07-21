package gui;

import model.MaritalStatus;

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
            case MARITAL_STATUS_COLUMN:
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
            case MARITAL_STATUS_COLUMN:
                formPerson.maritalStatus = (MaritalStatus) aValue;
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
            case CLUB_MEMBER_COLUMN:
                return Boolean.class;
            case MARITAL_STATUS_COLUMN:
                return MaritalStatus.class;
            default:
                return String.class;
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
        FormPerson formPerson = formPersonList.get(row);

        switch (column) {
            case NAME_COLUMN:
                return  formPerson.name;
            case AGE_CATEGORY_COLUMN:
                return formPerson.ageCategory;
            case OCCUPATION_COLUMN:
                return formPerson.occupation;
            case MARITAL_STATUS_COLUMN:
                return formPerson.maritalStatus;
            case GENDER_COLUMN:
                return formPerson.gender;
            case MEMBER_ID_COLUMN:
                return formPerson.memberId;
            case CLUB_MEMBER_COLUMN:
                return formPerson.isClubMember;
            default:
                return null;
        }
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
