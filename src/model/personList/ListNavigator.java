package model.personList;

import java.util.ArrayList;
import java.util.List;

public class ListNavigator {
    private List<ListUpdate> updates;
    private int currentUpdateIndex;

    public ListNavigator() {
        updates = new ArrayList<>();
        currentUpdateIndex = -1;
    }

    public void addTableUpdate(ListUpdate listUpdate) {
        if (hasNextUpdate()) {
            removeNextUpdates();
        }
        updates.add(listUpdate);
        currentUpdateIndex++;
    }

    private void removeNextUpdates() {
        int lastUpdateIndex = updates.size() - 1;
        for (;lastUpdateIndex > currentUpdateIndex; lastUpdateIndex--) {
            updates.remove(lastUpdateIndex);
        }
    }

    public void goToNextUpdate() {
        if (hasNextUpdate()) {
            currentUpdateIndex++;
        }
    }

    public boolean hasNextUpdate() {
        return currentUpdateIndex < updates.size() - 1;
    }

    public void goToPreviousUpdate() {
        if (hasPreviousUpdate()) {
            currentUpdateIndex--;
        }
    }

    public boolean hasPreviousUpdate() {
        return currentUpdateIndex > 0;
    }

    public ListUpdate getCurrentUpdate() {
        throwExceptionWhenUpdatesIsEmpty();
        return updates.get(currentUpdateIndex);
    }

    private void throwExceptionWhenUpdatesIsEmpty() {
        if (updates.isEmpty()) {
            throw new NoUpdatesAvailableException();
        }
    }
}
