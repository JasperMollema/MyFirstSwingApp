import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
    private TextPanel textPanel;
    private Toolbar toolbar;
    private FormPanel formPanel;
    private JFileChooser fileChooser;

    public MainFrame() {
        super("Hello World");

        setLayout(new BorderLayout());
        setJMenuBar(createMenuBar());

        textPanel = new TextPanel();
        toolbar = new Toolbar();
        formPanel = new FormPanel();
        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new PersonFileFilter());

        toolbar.setStringListener(text -> textPanel.appendText(text));

        formPanel.setFormListener(formEvent -> {
            String name = "Name: " + formEvent.getName();
            String occupation = "Occupation: " + formEvent.getOccupation();
            String ageCategory = "Age category: " + formEvent.getAgeCategory();
            String maritalStatus = "Marital status: " + formEvent.getMaritalStatus();
            String gender = "Gender : " + formEvent.getGender();

            textPanel.appendText(
                    name + "\n"
                            + occupation + "\n"
                            + ageCategory + "\n"
                            + maritalStatus + "\n"
                            + getIsClubMemberString(formEvent) + "\n"
                            + gender + "\n\n"
            );
        });

        add(formPanel, BorderLayout.WEST);
        add(textPanel, BorderLayout.CENTER);
        add(toolbar, BorderLayout.NORTH);

        setSize(600, 500);
        setMinimumSize(new Dimension(500, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private String getIsClubMemberString(FormEvent formEvent) {
        String isClubMemberValue = formEvent.isClubMember() ? "Yes" : "No";
        String isClubMember = "Club member: " + isClubMemberValue;

        if (formEvent.isClubMember()) {
            isClubMember += "\nMember id: " + formEvent.getMemberID();
        }

        return isClubMember;
    }

    private JMenuBar createMenuBar() {
        // File menu.
        JMenu fileMenu = new JMenu("File");
        JMenuItem exportDataItem = new JMenuItem("Export data..");
        JMenuItem importDataItem = new JMenuItem("Import data..");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.add(exitItem);

        // Window menu.
        JMenu windowMenu = new JMenu("Window");
        JMenu showMenu = new JMenu("Show");
        JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person form");
        showFormItem.setSelected(true);

        showMenu.add(showFormItem);
        windowMenu.add(showMenu);

        // Action listeners.
        importDataItem.addActionListener(
                event -> {
                    if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                        System.out.println(fileChooser.getSelectedFile());
                    }
                }
        );

        exportDataItem.addActionListener(
                event -> {
                    if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                        System.out.println(fileChooser.getSelectedFile());
                    }
                }
        );

        showFormItem.addActionListener(
                event -> {
                    JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) event.getSource();
                    formPanel.setVisible(menuItem.isSelected());
                }
        );

        exitItem.addActionListener(
                event -> {
                    int action = JOptionPane.showConfirmDialog(
                            MainFrame.this,
                            "Do you really want to exit?",
                            "Confirm exit",
                            JOptionPane.OK_CANCEL_OPTION);

                    if (action == JOptionPane.OK_OPTION){
                        System.exit(0);
                    }
                }
        );

        // Mnemonics
        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_E);
        windowMenu.setMnemonic(KeyEvent.VK_W);

        // Accelerators.
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        // Menu Bar.
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        return menuBar;
    }
}
