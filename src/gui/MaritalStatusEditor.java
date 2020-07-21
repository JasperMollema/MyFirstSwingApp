package gui;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.EventObject;

public class MaritalStatusEditor extends AbstractCellEditor implements TableCellEditor {
    private JComboBox comboBox;

    public MaritalStatusEditor(JComboBox comboBox) {
        this.comboBox = comboBox;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        comboBox.setSelectedItem(value);
        comboBox.addActionListener(e -> fireEditingStopped());
        return comboBox;
    }

    @Override
    public Object getCellEditorValue() {
        return comboBox.getSelectedItem();
    }

    @Override
    public boolean isCellEditable(EventObject e) {
        return true;
    }
}
