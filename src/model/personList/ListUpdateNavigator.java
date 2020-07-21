package model.personList;

import java.util.ArrayList;
import java.util.List;

public class ListUpdateNavigator<T> {
    private List<ListUpdate<T>> listUpdates;
    private int currentUpdateIndex;

    public ListUpdateNavigator() {
        listUpdates = new ArrayList<>();
        currentUpdateIndex = -1;
    }

    public void addTableUpdate(ListUpdate listUpdate) {
        if (hasNextUpdate()) {
            removeNextUpdates();
        }
        listUpdates.add(listUpdate);
        currentUpdateIndex++;
    }

    private void removeNextUpdates() {
        int lastUpdateIndex = listUpdates.size() - 1;
        for (;lastUpdateIndex > currentUpdateIndex; lastUpdateIndex--) {
            listUpdates.remove(lastUpdateIndex);
        }
    }

    public void goToNextUpdate() {
        if (hasNextUpdate()) {
            currentUpdateIndex++;
        }
    }

    public boolean hasNextUpdate() {
        return currentUpdateIndex < listUpdates.size() - 1;
    }

    public void goToPreviousUpdate() {
        if (hasPreviousUpdate()) {
            currentUpdateIndex--;
        }
    }

    public boolean hasPreviousUpdate() {
        return currentUpdateIndex > 0;
    }

    public void removeAllUpdates() {
        listUpdates.clear();
        currentUpdateIndex = -1;
    }

    public List<T> getCurrentUpdate() {
        throwExceptionWhenUpdatesIsEmpty();
        return listUpdates.get(currentUpdateIndex).getList();
    }

    private void throwExceptionWhenUpdatesIsEmpty() {
        if (listUpdates.isEmpty()) {
            throw new NoUpdatesAvailableException();
        }
    }
}
