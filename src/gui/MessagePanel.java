package gui;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

public class MessagePanel extends JPanel {
    private DefaultTreeCellRenderer treeCellRenderer;
    private JTree serverTree;

    public MessagePanel() {
        treeCellRenderer = new DefaultTreeCellRenderer();
        treeCellRenderer.setLeafIcon(Utils.createIcon("/images/Server16.gif"));
        treeCellRenderer.setOpenIcon(Utils.createIcon("/images/WebComponent16.gif"));
        treeCellRenderer.setClosedIcon(Utils.createIcon("/images/WebComponentAdd16.gif"));

        serverTree = new JTree(createTree());
        serverTree.setCellRenderer(treeCellRenderer);
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
        DefaultMutableTreeNode serverAmsterdam = new DefaultMutableTreeNode(new ServerInfo("Amsterdam", 0));
        DefaultMutableTreeNode serverAlkmaar = new DefaultMutableTreeNode(new ServerInfo("Alkmaar", 1));
        DefaultMutableTreeNode serverDenBosch = new DefaultMutableTreeNode(new ServerInfo("Den Bosch", 2));

        dutchBranch.add(serverAmsterdam);
        dutchBranch.add(serverAlkmaar);
        dutchBranch.add(serverDenBosch);

        // UK servers.
        DefaultMutableTreeNode UKBranch = new DefaultMutableTreeNode("UK");
        DefaultMutableTreeNode serverLondon = new DefaultMutableTreeNode(new ServerInfo("London", 3));
        DefaultMutableTreeNode serverBristol = new DefaultMutableTreeNode(new ServerInfo("Bristol", 4));
        DefaultMutableTreeNode serverLeeds = new DefaultMutableTreeNode(new ServerInfo("Leeds", 5));

        UKBranch.add(serverLondon);
        UKBranch.add(serverBristol);
        UKBranch.add(serverLeeds);

        top.add(dutchBranch);
        top.add(UKBranch);

        return top;
    }
}

class ServerInfo {
    private String name;
    private int id;

    public ServerInfo(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
