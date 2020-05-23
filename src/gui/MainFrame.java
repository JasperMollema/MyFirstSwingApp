package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class MainFrame extends JFrame {
    private TextPanel textPanel;
    private Toolbar toolbar;
    private FormPanel formPanel;
    private JFileChooser fileChooser;
    private TablePanel tablePanel;
    private Controller controller;

    public MainFrame() {
        super("Hello World");

        setLayout(new BorderLayout());
        setJMenuBar(createMenuBar());

        textPanel = new TextPanel();
        toolbar = new Toolbar();
        formPanel = new FormPanel();
        tablePanel = new TablePanel();
        controller = new Controller();
        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new PersonFileFilter());

        toolbar.setStringListener(text -> textPanel.appendText(text));

        tablePanel.setPersonTableListener(
                row -> {
            controller.deletePerson(row);
            tablePanel.fillTable(controller.getPersonList());
            tablePanel.refresh();
        });

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

        add(formPanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);
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

        showMenu.add(showFormItem);
        windowMenu.add(showMenu);

        // Action listeners.
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

        // Menu Bar.
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        return menuBar;
    }
}
