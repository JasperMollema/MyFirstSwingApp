import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private TextPanel textPanel;
    private Toolbar toolbar;
    private FormPanel formPanel;

    public MainFrame() {
        super("Hello World");

        setLayout(new BorderLayout());

        textPanel = new TextPanel();
        toolbar = new Toolbar();
        formPanel = new FormPanel();

        toolbar.setStringListener(text -> textPanel.appendText(text));

        add(formPanel, BorderLayout.WEST);
        add(textPanel, BorderLayout.CENTER);
        add(toolbar, BorderLayout.NORTH);

        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
