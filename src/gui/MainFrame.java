package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class MainFrame extends JFrame {
    private TextPanel textPanel;
    private Toolbar toolbar;
    private FormPanel formPanel;
    private JFileChooser fileChooser;
    private TablePanel tablePanel;
    private PreferenceDialog preferenceDialog;
    private Controller controller;
    private Preferences preferences;

    public MainFrame() {
        super("Hello World");

        setLayout(new BorderLayout());
        setJMenuBar(createMenuBar());

        textPanel = new TextPanel();
        toolbar = new Toolbar();
        formPanel = new FormPanel();
        tablePanel = new TablePanel();
        preferenceDialog = new PreferenceDialog(this);
        controller = new Controller();
        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new PersonFileFilter());
        preferences = Preferences.userRoot().node("db");

        addTableListener();
        addToolbarListener();
        addFormListener();
        addPreferenceListener();

        setDefaultsPreferenceDialogue();

        add(formPanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);
        add(toolbar, BorderLayout.NORTH);

        setSize(600, 500);
        setMinimumSize(new Dimension(500, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addToolbarListener() {
        toolbar.setToolbarListener(new ToolbarListener() {
            @Override
            public void saveEventOccurred() {
                if (!controller.isTableChanged()) {
                    JOptionPane.showMessageDialog(
                            MainFrame.this,
                            "There ar no changes to save",
                            "No changes made",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }

                if (controller.arePersonsDeleted()) {
                    int action = JOptionPane.showConfirmDialog(
                            MainFrame.this,
                            "You have deleted person, are you sure you want to save these changes?",
                            "Persons deleted",
                            JOptionPane.OK_CANCEL_OPTION);

                    if (action == JOptionPane.CANCEL_OPTION){
                        return;
                    }
                }
                connect();

                try {
                    controller.save();
                } catch (SQLException exception) {
                    JOptionPane.showMessageDialog(
                            MainFrame.this,
                            "Unable to save to database" ,
                            "Database Connection Problem",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void loadEventOccurred() {
                connect();

                try {
                    if (!controller.load()) {
                        JOptionPane.showMessageDialog(
                                MainFrame.this,
                                "There are no persons to load",
                                "No persons available",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }

                } catch (SQLException exception) {
                    JOptionPane.showMessageDialog(
                            MainFrame.this,
                            "Unable to load from database" ,
                            "Database Connection Problem",
                            JOptionPane.ERROR_MESSAGE);
                }

                tablePanel.fillTable(controller.getPersonList());
                tablePanel.refresh();
            }
        });
    }

    private void connect() {
        try {
            controller.connectToDatabase();
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(
                    MainFrame.this,
                    "Cannot connect to database" ,
                    "Database Connection Problem",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addTableListener() {
        tablePanel.setPersonTableListener(
                row -> {
                    controller.deletePerson(row);
                    tablePanel.fillTable(controller.getPersonList());
                    tablePanel.refresh();
                });
    }

    private void addFormListener() {
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

            controller.addPerson(createFormPerson(formEvent));
            tablePanel.fillTable(controller.getPersonList());
            tablePanel.refresh();
        });
    }

    private void addPreferenceListener() {
        preferenceDialog.setPreferencesListener(
                (String user, String password, Integer port) -> {
                    preferences.put("user", user);
                    preferences.put("password", password);
                    preferences.putInt("port", port);
                }
        );
    }

    private void setDefaultsPreferenceDialogue() {
        preferenceDialog.setDefaults(
                preferences.get("user", ""),
                preferences.get("password", ""),
                preferences.getInt("port", 3306)
        );
    }

    private String getIsClubMemberString(FormEvent formEvent) {
        String isClubMemberValue = formEvent.isClubMember() ? "Yes" : "No";
        String isClubMember = "Club member: " + isClubMemberValue;

        if (formEvent.isClubMember()) {
            isClubMember += "\nMember id: " + formEvent.getMemberID();
        }

        return isClubMember;
    }

    private FormPerson createFormPerson(FormEvent formEvent) {
        FormPerson formPerson = new FormPerson();
        formPerson.name = formEvent.getName();
        formPerson.occupation = formEvent.getOccupation();
        formPerson.ageCategory = formEvent.getAgeCategory();
        formPerson.maritalStatus = formEvent.getMaritalStatus();
        formPerson.gender = formEvent.getGender();
        formPerson.isClubMember = formEvent.isClubMember();
        formPerson.memberId = formEvent.getMemberID();

        return formPerson;
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
        JMenuItem preferencesItem = new JMenuItem("Preferences...");


        showMenu.add(showFormItem);
        windowMenu.add(showMenu);
        windowMenu.add(preferencesItem);

        // Action listeners.
        preferencesItem.addActionListener(
                event -> preferenceDialog.setVisible(true)
        );

        importDataItem.addActionListener(
                event -> {
                    if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                        try {
                            tablePanel.fillTable(controller.loadFromFile(fileChooser.getSelectedFile()));
                            tablePanel.refresh();
                        } catch (IOException | ClassNotFoundException e) {
                            JOptionPane.showMessageDialog(
                                    MainFrame.this,
                                    "Could not load data from file",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
        );

        exportDataItem.addActionListener(
                event -> {
                    if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                        try {
                            controller.savePersonsToFile(fileChooser.getSelectedFile());
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(
                                    MainFrame.this,
                                    "Could not save data from file",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
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
        importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        exportDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        preferencesItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));

        // Menu Bar.
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        return menuBar;
    }
}
