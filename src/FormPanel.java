import javax.swing.*;
import java.awt.*;

public class FormPanel extends JPanel {

    public FormPanel() {
        Dimension dimension = getPreferredSize();
        dimension.width = 250; // The layout manager does not respect the preferred height.
        setPreferredSize(dimension);
    }

}
