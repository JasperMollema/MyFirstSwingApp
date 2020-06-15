package gui;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class ServerTreeCellEditor extends AbstractCellEditor implements TreeCellEditor {
    private ServerTreeCellRenderer serverTreeCellRenderer;
    private JCheckBox checkBox;
    private ServerInfo serverInfo;

    public ServerTreeCellEditor() {
        serverTreeCellRenderer = new ServerTreeCellRenderer();
    }

    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded,
                                                boolean leaf, int row) {
        Component component = serverTreeCellRenderer.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, true);

        if (leaf) {
            DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) value;
            serverInfo = (ServerInfo) treeNode.getUserObject();

            // If dealing with a leaf it will be a checkbox.
            checkBox = (JCheckBox) component;

            ItemListener itemListener = new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    // Has to be called to call getCellEditorValue().
                    fireEditingStopped();
                    checkBox.removeItemListener(this);
                }
            };

            checkBox.addItemListener(itemListener);
        }

        return component;
    }

    @Override
    public Object getCellEditorValue() {
        serverInfo.update(checkBox.isSelected());
        return serverInfo;
    }

    @Override
    public boolean isCellEditable(EventObject event) {
        if (event instanceof KeyEvent) {
            return handleKeyEvent((KeyEvent) event);
        }

        if (event instanceof MouseEvent) {
            return handleMouseEvent((MouseEvent) event);
        }

        return false;
    }

    private boolean handleKeyEvent(KeyEvent keyEvent) {return true;}

    private boolean handleMouseEvent(MouseEvent mouseEvent) {
        JTree tree = (JTree) mouseEvent.getSource();
        TreePath treePath = tree.getClosestPathForLocation(mouseEvent.getX(), mouseEvent.getY());

        if (treePath == null) {
            return false;
        }

        Object lastComponent = treePath.getLastPathComponent();

        if (lastComponent == null) {
            return false;
        }

        DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) lastComponent;

        return treeNode.isLeaf();
    }
}
