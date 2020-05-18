package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FormPanel extends JPanel {
    private JTextField nameField;
    private JTextField occupationField;
    private JList<AgeCategory> ageList;
    private JComboBox maritalStatusBox;
    private JCheckBox clubMemberCheck;
    private JLabel memberIdLabel;
    private JTextField memberIdField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private ButtonGroup genderRadioButtonGroup;
    private JButton submit;

    private FormListener formListener;

    public FormPanel() {
        Dimension dimension = getPreferredSize();
        dimension.width = 250; // The layout manager does not respect the preferred height.
        setPreferredSize(dimension);

        nameField = new JTextField(10);
        occupationField = new JTextField(10);

        // Set up list box.
        ageList = new JList();

        DefaultListModel ageModel = new DefaultListModel();
        ageModel.addElement(new AgeCategory(0,"under 18"));
        ageModel.addElement(new AgeCategory(1,"18 to 65"));
        ageModel.addElement(new AgeCategory(2,"65 or over"));

        ageList.setModel(ageModel);
        ageList.setPreferredSize(new Dimension(115, 60));
        ageList.setBorder(BorderFactory.createEtchedBorder());
        ageList.setSelectedIndex(1);

        // Set up combo box.
        maritalStatusBox = new JComboBox<String>();

        DefaultComboBoxModel maritalStatusModel = new DefaultComboBoxModel();
        maritalStatusModel.addElement("single");
        maritalStatusModel.addElement("married");
        maritalStatusModel.addElement("cohabiting");
        maritalStatusModel.addElement("divorced");
        maritalStatusModel.addElement("widowed");

        maritalStatusBox.setModel(maritalStatusModel);
        maritalStatusBox.setSelectedIndex(0);

        // Set up club member.
        clubMemberCheck = new JCheckBox();
        memberIdLabel = new JLabel("Member ID:");
        memberIdField = new JTextField(10);

        memberIdLabel.setEnabled(false);
        memberIdField.setEnabled(false);

        clubMemberCheck.addActionListener(
                event -> {
                    memberIdLabel.setEnabled(clubMemberCheck.isSelected());
                    memberIdField.setEnabled(clubMemberCheck.isSelected());
                }
        );

        // Set up gender buttons.
        maleRadioButton = new JRadioButton("male");
        femaleRadioButton = new JRadioButton("female");
        genderRadioButtonGroup = new ButtonGroup();

        maleRadioButton.setActionCommand("male");
        femaleRadioButton.setActionCommand("female");
        genderRadioButtonGroup.add(maleRadioButton);
        genderRadioButtonGroup.add(femaleRadioButton);
        maleRadioButton.setSelected(true);

        // Set up submit button.
        submit = new JButton("Submit");

        submit.addActionListener(
                event -> {
                    if (formListener != null) {
                        formListener.formEventOccurred(
                                new FormEvent(
                                        this,
                                        nameField.getText(),
                                        occupationField.getText(),
                                        ageList.getSelectedValue().getId(),
                                        (String) maritalStatusBox.getSelectedItem(),
                                        clubMemberCheck.isSelected(),
                                        memberIdField.getText(),
                                        genderRadioButtonGroup.getSelection().getActionCommand()
                        )
                );
            }
        });

        Border innerBorder = BorderFactory.createTitledBorder("Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));

        layoutComponents();
    }

    public void layoutComponents() {
        setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        /////////////// First Row //////////////////////////////////
        gridBagConstraints.gridy = 0;

        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;

        // Name label.
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        add(new JLabel("name:"), gridBagConstraints);

        // Name value.
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        add(nameField, gridBagConstraints);

        /////////////// Second Row //////////////////////////////////
        gridBagConstraints.gridy++;

        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;

        // Occupation label.
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        add(new JLabel("Occupation:"), gridBagConstraints);

        // Occupation value.
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        add(occupationField, gridBagConstraints);

        /////////////// Third Row //////////////////////////////////
        gridBagConstraints.gridy++;

        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;

        // Age category value.
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        add(new JLabel("Age:"), gridBagConstraints);

        // Age category list box.
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(ageList, gridBagConstraints);

        /////////////// Fourth Row //////////////////////////////////
        gridBagConstraints.gridy++;

        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;

        // Marital status label.
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        add(new JLabel("Marital Status:"), gridBagConstraints);

        // Marital status combo box.
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(maritalStatusBox, gridBagConstraints);

        /////////////// Fifth Row //////////////////////////////////
        gridBagConstraints.gridy++;

        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;

        // Club member label.
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        add(new JLabel("Club member:"), gridBagConstraints);

        // Club member check box.
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(clubMemberCheck, gridBagConstraints);

        /////////////// Sixth Row //////////////////////////////////
        gridBagConstraints.gridy++;

        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;

        // Member id label.
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        add(memberIdLabel, gridBagConstraints);

        // Member id value.
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(memberIdField, gridBagConstraints);

        /////////////// Seventh Row //////////////////////////////////
        gridBagConstraints.gridy++;

        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.002;

        // Gender label.
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        add(new JLabel("Gender:"), gridBagConstraints);

        // Gender is male.
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(maleRadioButton, gridBagConstraints);

        /////////////// Eight Row //////////////////////////////////
        gridBagConstraints.gridy++;

        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;

        // Gender is female.
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(femaleRadioButton, gridBagConstraints);

        /////////////// Ninth Row //////////////////////////////////
        gridBagConstraints.gridy++;

        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 2;

        // Submit button.
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(submit, gridBagConstraints);
    }

    public void setFormListener(FormListener formListener) {
        this.formListener = formListener;
    }
}
