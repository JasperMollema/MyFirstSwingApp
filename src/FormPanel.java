import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FormPanel extends JPanel {
    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okButton;

    private FormListener formListener;

    public FormPanel() {
        Dimension dimension = getPreferredSize();
        dimension.width = 250; // The layout manager does not respect the preferred height.
        setPreferredSize(dimension);

        nameLabel = new JLabel("name");
        occupationLabel = new JLabel("occupation");
        nameField = new JTextField(10);
        occupationField = new JTextField(10);

        okButton = new JButton("Submit");

        okButton.addActionListener(
                event -> {
            String name = nameField.getText();
            String occupation = occupationField.getText();

            FormEvent formEvent = new FormEvent(this, name, occupation);

            if (formListener != null) {
                formListener.formEventOccurred(formEvent);
            }
        });

        Border innerBorder = BorderFactory.createTitledBorder("Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));

        setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        /////////////// First Row //////////////////////////////////
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        add(nameLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        add(nameField, gridBagConstraints);

        /////////////// Second Row //////////////////////////////////
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0,0,0,5);
        add(occupationLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        add(occupationField, gridBagConstraints);

        /////////////// Third Row //////////////////////////////////
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 2;

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(okButton, gridBagConstraints);
    }

    public void setFormListener(FormListener formListener) {
        this.formListener = formListener;
    }
}
