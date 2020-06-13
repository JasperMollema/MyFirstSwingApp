package gui;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class MessagePanel extends JPanel {
    private JTree serverTree;

    public MessagePanel() {
        serverTree = new JTree(createTree());

        setLayout(new BorderLayout());

        add(new JScrollPane(serverTree), BorderLayout.CENTER);
    }

    private DefaultMutableTreeNode createTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("service");

        // Dutch servers.
        DefaultMutableTreeNode dutchBranch = new DefaultMutableTreeNode("Netherlands");
        DefaultMutableTreeNode serverAmsterdam = new DefaultMutableTreeNode("Amsterdam");
        DefaultMutableTreeNode serverAlkmaar = new DefaultMutableTreeNode("Alkmaar");
        DefaultMutableTreeNode serverDenBosch = new DefaultMutableTreeNode("Den Bosch");

        dutchBranch.add(serverAmsterdam);
        dutchBranch.add(serverAlkmaar);
        dutchBranch.add(serverDenBosch);

        // UK servers.
        DefaultMutableTreeNode UKBranch = new DefaultMutableTreeNode("UK");
        DefaultMutableTreeNode serverLondon = new DefaultMutableTreeNode("London");
        DefaultMutableTreeNode serverBristol = new DefaultMutableTreeNode("Bristol");
        DefaultMutableTreeNode serverLeeds = new DefaultMutableTreeNode("Leeds");

        UKBranch.add(serverLondon);
        UKBranch.add(serverBristol);
        UKBranch.add(serverLeeds);

        top.add(dutchBranch);
        top.add(UKBranch);

        return top;
    }
}
