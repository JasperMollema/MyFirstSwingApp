package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TablePanel extends JPanel {
    private PersonTableModel tableModel;
    private JTable table;
    private JPopupMenu popupMenu;
    private PersonTableListener personTableListener;

    public TablePanel() {
        tableModel = new PersonTableModel();
        table = new JTable(tableModel);
        popupMenu = new JPopupMenu();

        JMenuItem removeItem = new JMenuItem("Delete row");
        popupMenu.add(removeItem);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                int row = table.rowAtPoint(event.getPoint());
                table.getSelectionModel().setSelectionInterval(row, row);

                if (event.getButton() == MouseEvent.BUTTON3) {
                    popupMenu.show(table, getX(), getY());
                }
            }
        });

        removeItem.addActionListener(
                event -> {
                    if (personTableListener == null) {
                        return;
                    }


                    personTableListener.deleteRow(table.getSelectedRow());
                }
        );

        setLayout(new BorderLayout());

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void fillTable(List<FormPerson> formPersonList) {
        tableModel.fillPersonTable(formPersonList);
    }

    public void refresh() {
        tableModel.fireTableDataChanged();
    }

    public void setPersonTableListener(PersonTableListener personTableListener) {
        this.personTableListener = personTableListener;
    }
}
