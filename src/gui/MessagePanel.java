package gui;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

public class MessagePanel extends JPanel {
    private ServerTreeCellRenderer treeCellRenderer;
    private ServerTreeCellEditor serverTreeCellEditor;
    private JTree serverTree;

    public MessagePanel() {
        treeCellRenderer = new ServerTreeCellRenderer();
        serverTreeCellEditor = new ServerTreeCellEditor();

        serverTree = new JTree(createTree());
        serverTree.setCellRenderer(treeCellRenderer);
        serverTree.setCellEditor(serverTreeCellEditor);
        serverTree.setEditable(true);
        serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        serverTree.addTreeSelectionListener(
                event -> {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) serverTree.getLastSelectedPathComponent();
                    Object userObject = node.getUserObject();
                    System.out.println(userObject);
                }
        );
        setLayout(new BorderLayout());

        add(new JScrollPane(serverTree), BorderLayout.CENTER);
    }

    private DefaultMutableTreeNode createTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("service");

        // Dutch servers.
        DefaultMutableTreeNode dutchBranch = new DefaultMutableTreeNode("Netherlands");
        DefaultMutableTreeNode serverAmsterdam = new DefaultMutableTreeNode(new ServerInfo("Amsterdam", 0,true));
        DefaultMutableTreeNode serverAlkmaar = new DefaultMutableTreeNode(new ServerInfo("Alkmaar", 1, false));
        DefaultMutableTreeNode serverDenBosch = new DefaultMutableTreeNode(new ServerInfo("Den Bosch", 2, false));

        dutchBranch.add(serverAmsterdam);
        dutchBranch.add(serverAlkmaar);
        dutchBranch.add(serverDenBosch);

        // UK servers.
        DefaultMutableTreeNode UKBranch = new DefaultMutableTreeNode("UK");
        DefaultMutableTreeNode serverLondon = new DefaultMutableTreeNode(new ServerInfo("London", 3, false));
        DefaultMutableTreeNode serverBristol = new DefaultMutableTreeNode(new ServerInfo("Bristol", 4, true));
        DefaultMutableTreeNode serverLeeds = new DefaultMutableTreeNode(new ServerInfo("Leeds", 5, false));

        UKBranch.add(serverLondon);
        UKBranch.add(serverBristol);
        UKBranch.add(serverLeeds);

        top.add(dutchBranch);
        top.add(UKBranch);

        return top;
    }
}


