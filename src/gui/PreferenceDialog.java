package gui;

import javax.swing.*;
import java.awt.*;

public class PreferenceDialog extends JDialog {
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton okButton;
    private JButton cancelButton;
    private JSpinner portSpinner;
    private SpinnerNumberModel spinnerModel;

    public PreferenceDialog(JFrame parent) {
        super(parent, "Preferences", false);

        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());

        userField = new JTextField(10);
        passwordField = new JPasswordField(10);
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");


        spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
        portSpinner = new JSpinner(spinnerModel);

        addActionListeners();
        layoutComponents();
    }

    private void addActionListeners() {
        okButton.addActionListener(
                event -> {
                    Integer value = (Integer) spinnerModel.getValue();
                    System.out.println(value);
                    setVisible(false);
                }
        );

        cancelButton.addActionListener(event -> setVisible(false));
    }

    private void layoutComponents() {
        GridBagConstraints gridBagConstraints= new GridBagConstraints();

        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 3;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;

        /////////// First row /////////////////
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Port: "), gridBagConstraints);

        gridBagConstraints.gridx++;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        add(portSpinner, gridBagConstraints);

        /////////// Next row /////////////////
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("User: "), gridBagConstraints);

        gridBagConstraints.gridx++;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        add(userField, gridBagConstraints);

        /////////// Next row /////////////////
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Password: "), gridBagConstraints);

        gridBagConstraints.gridx++;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        add(passwordField, gridBagConstraints);

        /////////// Next row /////////////////
        gridBagConstraints.gridy++;
        gridBagConstraints.weighty = 12;
        gridBagConstraints.weightx = 6;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        add(okButton, gridBagConstraints);

        gridBagConstraints.gridx++;
        gridBagConstraints.weightx = 2;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        add(cancelButton, gridBagConstraints);
    }
}
