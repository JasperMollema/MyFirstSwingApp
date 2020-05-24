package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PreferenceDialog extends JDialog {
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton okButton;
    private JButton cancelButton;
    private JSpinner portSpinner;
    private SpinnerNumberModel spinnerModel;
    private PreferencesListener preferencesListener;

    public PreferenceDialog(JFrame parent) {
        super(parent, "Preferences", false);

        setSize(300, 220);
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
                    if (preferencesListener != null) {
                        preferencesListener.setPreferences(
                                userField.getText(),
                                new String(passwordField.getPassword()),
                                ((Integer)spinnerModel.getValue())
                        );
                    }

                    setVisible(false);
                }
        );

        cancelButton.addActionListener(event -> setVisible(false));
    }

    private void layoutComponents() {
        JPanel controlsPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();

        int space = 15;
        Border titleBorder = BorderFactory.createTitledBorder("Database preferences");
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);

        controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

        ///////////  Controls panel /////////////////
        controlsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints= new GridBagConstraints();

        Insets rightPadding = new Insets(0, 0, 0, 15);
        Insets noPadding = new Insets(0,0,0,0);

        gridBagConstraints.gridy = 0;

        // First row
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;

        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = rightPadding;
        controlsPanel.add(new JLabel("Port: "), gridBagConstraints);

        gridBagConstraints.gridx++;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = noPadding;
        controlsPanel.add(portSpinner, gridBagConstraints);

        // Next row
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = rightPadding;
        controlsPanel.add(new JLabel("User: "), gridBagConstraints);

        gridBagConstraints.gridx++;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = noPadding;
        controlsPanel.add(userField, gridBagConstraints);

        // Next row
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = rightPadding;
        controlsPanel.add(new JLabel("Password: "), gridBagConstraints);

        gridBagConstraints.gridx++;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = noPadding;
        controlsPanel.add(passwordField, gridBagConstraints);

        /////////// Buttons panel /////////////////
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);

        Dimension buttonSize = cancelButton.getPreferredSize();
        okButton.setPreferredSize(buttonSize);

        /////////// Add sub panels /////////////////
        setLayout(new BorderLayout());
        add(controlsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    public void setDefaults(String user, String password, Integer port) {
        userField.setText(user);
        passwordField.setText(password);
        portSpinner.setValue(port);
    }

    public void setPreferencesListener(PreferencesListener preferencesListener) {
        this.preferencesListener = preferencesListener;
    }
}
