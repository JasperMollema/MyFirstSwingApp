package gui;

import controller.MessageServer;
import gui.listeners.ProgressDialogListener;
import model.Message;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;

public class MessagePanel extends JPanel implements ProgressDialogListener {
    private ServerTreeCellRenderer treeCellRenderer;
    private ServerTreeCellEditor serverTreeCellEditor;
    private JTree serverTree;
    private Set<Integer> selectedServers;
    private MessageServer messageServer;
    private ProgressDialog progressDialog;
    private SwingWorker worker;
    private TextPanel textPanel;
    private JList messageList;
    private JSplitPane upperPane;
    private JSplitPane lowerPane;
    private DefaultListModel messageListModel;

    public MessagePanel(JFrame parent) {
        treeCellRenderer = new ServerTreeCellRenderer();
        serverTreeCellEditor = new ServerTreeCellEditor();
        selectedServers = new TreeSet<>();
        messageServer = new MessageServer();
        progressDialog = new ProgressDialog(parent, "Messages Downloading...");
        progressDialog.setProgressDialogListener(this);

        serverTree = new JTree(createTree());
        serverTree.setCellRenderer(treeCellRenderer);
        serverTree.setCellEditor(serverTreeCellEditor);
        serverTree.setEditable(true);
        serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        messageServer.fillSelectedServers(selectedServers);
        messageListModel = new DefaultListModel();

        serverTreeCellEditor.addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent event) {
                ServerInfo serverInfo = (ServerInfo) serverTreeCellEditor.getCellEditorValue();

                int serverId = serverInfo.getId();

                if (serverInfo.isChecked()) {
                    selectedServers.add(serverId);
                }

                else {
                    selectedServers.remove(serverId);
                }

                messageServer.fillSelectedServers(selectedServers);

            }

            @Override
            public void editingCanceled(ChangeEvent event) {

            }
        });

        setLayout(new BorderLayout());

        textPanel = new TextPanel();
        messageList = new JList(messageListModel);
        messageList.setCellRenderer(new MessageListRenderer());
        messageList.addListSelectionListener(
                event -> {
                    Message message = (Message) messageList.getSelectedValue();
                    textPanel.setText(message != null ? message.getContents() : null);
                }
        );

        lowerPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(messageList), textPanel);
        upperPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(serverTree), lowerPane);

        textPanel.setMinimumSize(new Dimension(10, 100));
        messageList.setMinimumSize(new Dimension(10, 100));

        // Bepaalt verhouding splitpane.
        upperPane.setResizeWeight(0.5);
        lowerPane.setResizeWeight(0.5);

        add(upperPane, BorderLayout.CENTER);
    }

    public void refresh() {
        retrieveMessages();
    }

    @Override
    public void cancelProgressDialog() {
        if (worker != null) {
            worker.cancel(true);
        }
    }

    private void retrieveMessages() {

        progressDialog.setMaximum(messageServer.getMessageCount());
        progressDialog.setVisible(true);

        worker = new SwingWorker<List<Message>, Integer>() {

           @Override
           protected List<Message> doInBackground() throws Exception {
               List<Message> retrievedMessages = new ArrayList<>();
               int count = 0;

               for (Message message : messageServer) {

                   if (isCancelled()) {
                       break;
                   }

                   retrievedMessages.add(message);
                   count++;
                   publish(count);
               }
               return retrievedMessages;
           }

           @Override
           protected void done() {
               progressDialog.setVisible(false);

               if (isCancelled()) {
                   return;
               }

               try {
                   List<Message> retrievedMessages = get();

                   messageListModel.removeAllElements();

                   for (Message message : retrievedMessages) {
                       messageListModel.addElement(message);
                   }

               } catch (InterruptedException e) {
                   e.printStackTrace();
               } catch (ExecutionException e) {
                   e.printStackTrace();
               }
           }

           @Override
           protected void process(List<Integer> counts) {
               int retrievedMessages = counts.get(counts.size() - 1);

               progressDialog.setValue(retrievedMessages);
           }
       };

        worker.execute();
    }

    private DefaultMutableTreeNode createTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("service");

        // Create pre selected servers.
        ServerInfo alkmaar = new ServerInfo(ServerInfo.ALKMAAR);
        ServerInfo london = new ServerInfo(ServerInfo.LONDON);
        alkmaar.setIsChecked(true);
        london.setIsChecked(true);
        selectedServers.add(ServerInfo.ALKMAAR);
        selectedServers.add(ServerInfo.LONDON);

        // Dutch servers.
        DefaultMutableTreeNode dutchBranch = new DefaultMutableTreeNode("Netherlands");
        DefaultMutableTreeNode serverAmsterdam = new DefaultMutableTreeNode(new ServerInfo(ServerInfo.AMSTERDAM));
        DefaultMutableTreeNode serverAlkmaar = new DefaultMutableTreeNode(alkmaar);
        DefaultMutableTreeNode serverDenBosch = new DefaultMutableTreeNode(new ServerInfo(ServerInfo.DEN_BOSCH));

        dutchBranch.add(serverAmsterdam);
        dutchBranch.add(serverAlkmaar);
        dutchBranch.add(serverDenBosch);

        // UK servers.
        DefaultMutableTreeNode UKBranch = new DefaultMutableTreeNode("UK");
        DefaultMutableTreeNode serverLondon = new DefaultMutableTreeNode(london);
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


