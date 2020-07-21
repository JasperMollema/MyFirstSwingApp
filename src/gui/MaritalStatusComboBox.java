package gui;

import model.MaritalStatus;

import javax.swing.*;

public class MaritalStatusComboBox {
    private JComboBox comboBox;

    public MaritalStatusComboBox() {
        comboBox = new JComboBox(MaritalStatus.values());
    }

    public JComboBox getComboBox() {
        return comboBox;
    }
}
