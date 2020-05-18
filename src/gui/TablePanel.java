package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TablePanel extends JPanel {
    private PersonTableModel tableModel;
    private JTable table;

    public TablePanel() {
        tableModel = new PersonTableModel();
        table = new JTable(tableModel);

        setLayout(new BorderLayout());

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void fillTable(List<FormPerson> formPersonList) {
        tableModel.fillPersonTable(formPersonList);
    }

    public void refresh() {
        tableModel.fireTableDataChanged();
    }
}
