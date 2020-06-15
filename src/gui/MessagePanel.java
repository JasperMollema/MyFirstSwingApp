package gui;

import controller.MessageServer;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.Set;
import java.util.TreeSet;

public class MessagePanel extends JPanel {
    private ServerTreeCellRenderer treeCellRenderer;
    private ServerTreeCellEditor serverTreeCellEditor;
    private JTree serverTree;
    private Set<Integer> selectedServers;
    private MessageServer messageServer;

    public MessagePanel() {
        treeCellRenderer = new ServerTreeCellRenderer();
        serverTreeCellEditor = new ServerTreeCellEditor();
        selectedServers = new TreeSet<>();
        messageServer = new MessageServer();

        serverTree = new JTree(createTree());
        serverTree.setCellRenderer(treeCellRenderer);
        serverTree.setCellEditor(serverTreeCellEditor);
        serverTree.setEditable(true);
        serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        serverTreeCellEditor.addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent event) {
                ServerInfo serverInfo = (ServerInfo) serverTreeCellEditor.getCellEditorValue();

                int serverId = serverInfo.getId();

                if (serverInfo.isChecked()) {
                    selectedServers.add(serverId);
                    System.out.println("Checked server: " + serverInfo);
                }

                else {
                    selectedServers.remove(serverId);
                    System.out.println("Unchecked server: " + serverInfo);
                }

                messageServer.fillSelectedServers(selectedServers);

                System.out.println("Messages waiting: " + messageServer.getMessageCount());

                messageServer.forEach(System.out::println);
            }

            @Override
            public void editingCanceled(ChangeEvent event) {

            }
        });
        setLayout(new BorderLayout());

        add(new JScrollPane(serverTree), BorderLayout.CENTER);
    }



    private DefaultMutableTreeNode createTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("service");

        // Dutch servers.
        DefaultMutableTreeNode dutchBranch = new DefaultMutableTreeNode("Netherlands");
        DefaultMutableTreeNode serverAmsterdam = new DefaultMutableTreeNode(new ServerInfo(ServerInfo.AMSTERDAM));
        DefaultMutableTreeNode serverAlkmaar = new DefaultMutableTreeNode(new ServerInfo(ServerInfo.ALKMAAR));
        DefaultMutableTreeNode serverDenBosch = new DefaultMutableTreeNode(new ServerInfo(ServerInfo.DEN_BOSCH));

        dutchBranch.add(serverAmsterdam);
        dutchBranch.add(serverAlkmaar);
        dutchBranch.add(serverDenBosch);

        // UK servers.
        DefaultMutableTreeNode UKBranch = new DefaultMutableTreeNode("UK");
        DefaultMutableTreeNode serverLondon = new DefaultMutableTreeNode(new ServerInfo(ServerInfo.LONDON));
        DefaultMutableTreeNode serverBristol = new DefaultMutableTreeNode(new ServerInfo(ServerInfo.BRISTOL));
        DefaultMutableTreeNode serverLeeds = new DefaultMutableTreeNode(new ServerInfo(ServerInfo.LEEDS));

        UKBranch.add(serverLondon);
        UKBranch.add(serverBristol);
        UKBranch.add(serverLeeds);

        top.add(dutchBranch);
        top.add(UKBranch);

        return top;
    }
}


