package gui;

import javax.swing.*;
import java.awt.*;

public class ProgressDialog extends JDialog {
    private JButton cancelButton;
    private JProgressBar progressBar;

    public ProgressDialog(Window parent){
        super(parent, "Messages Downloading...", ModalityType.APPLICATION_MODAL);

        cancelButton = new JButton("Cancel");
        progressBar = new JProgressBar();

        setLayout(new FlowLayout());

        Dimension size = cancelButton.getPreferredSize();
        size.width = 400;
        progressBar.setPreferredSize(size);

        add(progressBar);
        add(cancelButton);

        pack();

        setLocationRelativeTo(parent);
    }

    public void setMaximum(int value) {
        progressBar.setMaximum(value);
    }

    public void setValue(int value) {
        progressBar.setValue(value);
    }

    @Override
    public void setVisible(boolean visible) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (visible == false) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    progressBar.setValue(0);
                }
                System.out.println("Showing modal dialog");
                ProgressDialog.super.setVisible(visible);
                System.out.println("Finished showing modal dialog");
            }
        });
    }
}
