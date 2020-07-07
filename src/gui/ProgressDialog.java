package gui;

import gui.listeners.ProgressDialogListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProgressDialog extends JDialog {
    private JButton cancelButton;
    private JProgressBar progressBar;
    private ProgressDialogListener progressDialogListener;

    public ProgressDialog(Window parent, String title){
        super(parent, title, ModalityType.APPLICATION_MODAL);

        cancelButton = new JButton("Cancel");
        progressBar = new JProgressBar();
        progressBar.setMaximum(10);
        progressBar.setStringPainted(true);
        progressBar.setString("Retrieving messages");

        setLayout(new FlowLayout());

        Dimension size = cancelButton.getPreferredSize();
        size.width = 400;
        progressBar.setPreferredSize(size);

        add(progressBar);
        add(cancelButton);

        cancelButton.addActionListener(
                event -> {
                    if (progressDialogListener != null) {
                        progressDialogListener.cancelProgressDialog();
                    }
                }
        );

        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (progressDialogListener != null) {
                    progressDialogListener.cancelProgressDialog();
                }
            }
        });
        pack();

        setLocationRelativeTo(parent);
    }

    public void setMaximum(int value) {
        progressBar.setMaximum(value);
    }

    public void setValue(int value) {
        int progress = 100  *value/progressBar.getMaximum();
        progressBar.setString(String.format("%d%% complete", progress));
        progressBar.setValue(value);
    }

    @Override
    public void setVisible(boolean visible) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (!visible) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    progressBar.setValue(0);
                }

                if (visible) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                }
                else {
                    setCursor(Cursor.getDefaultCursor());
                }

                ProgressDialog.super.setVisible(visible);
            }
        });
    }

    public void setProgressDialogListener(ProgressDialogListener progressDialogListener) {
        this.progressDialogListener = progressDialogListener;
    }
}
