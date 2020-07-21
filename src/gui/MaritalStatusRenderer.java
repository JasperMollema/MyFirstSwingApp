package gui;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class MaritalStatusRenderer implements TableCellRenderer {
    private JComboBox comboBox;

    public MaritalStatusRenderer(JComboBox comboBox) {
        this.comboBox = comboBox;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        comboBox.setSelectedItem(value);
        return comboBox;
    }
}
