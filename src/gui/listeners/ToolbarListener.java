package gui.listeners;

public interface ToolbarListener {
    void saveEventOccurred();

    void loadEventOccurred();

    void retrieveEventOccurred();

    void undoEventOccurred();

    void redoEventOccurred();
}
