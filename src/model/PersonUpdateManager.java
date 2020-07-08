package model;

import java.util.ArrayList;
import java.util.List;

public class PersonUpdateManager {
    private List<PersonUpdate> personUpdates;

    /** Index of the current update. */
    private int currentPosition;


    public PersonUpdateManager() {
        personUpdates = new ArrayList<>();
        // Start with -1 as personUpdates is still empty.
        currentPosition = -1;
    }

    public void addPersonUpdate(PersonUpdate personUpdate) {
        personUpdates.add(personUpdate);
        currentPosition++;
    }

    public PersonUpdate getPreviousUpdate() {
        if (personUpdates.isEmpty()) {
            System.out.println("PersonUpdateManager getPreviousUpdate() : No updates found!");
            return null;
        }

        if (currentPosition == 0) {
            System.out.println("PersonUpdateManager getPreviousUpdate() : No previous update found!");
            return null;
        }

        currentPosition--;

        return personUpdates.get(currentPosition);
    }

    public PersonUpdate getNextUpdate() {
        if (personUpdates.isEmpty()) {
            System.out.println("PersonUpdateManager getNextUpdate() : No updates found!");
            return null;
        }

        if (currentPosition == (personUpdates.size() - 1)) {
            System.out.println("PersonUpdateManager getNextUpdate() : No next update found!");
            return null;
        }

        currentPosition++;

        return personUpdates.get(currentPosition);
    }

    public boolean hasPreviousUpdate() {
        return currentPosition > 0;
    }

    public boolean hasNextUpdate() {
        return currentPosition > (personUpdates.size() - 1);
    }
}
