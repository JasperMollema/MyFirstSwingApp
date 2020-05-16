import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private TextPanel textPanel;
    private Toolbar toolbar;
    private JButton button;

    public MainFrame() {
        super("Hello World");

        setLayout(new BorderLayout());

        textPanel = new TextPanel();
        button = new JButton("click me");
        toolbar = new Toolbar();

        button.addActionListener(
                event -> textPanel.appendText("Hello\n")
                );

        add(textPanel, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);
        add(toolbar, BorderLayout.NORTH);

        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
