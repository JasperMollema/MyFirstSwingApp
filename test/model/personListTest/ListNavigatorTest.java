package model.personListTest;

import model.personList.ListNavigator;
import model.personList.ListUpdate;
import model.personList.NoUpdatesAvailableException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListNavigatorTest {
    private ListNavigator listNavigator = new ListNavigator();
    private ListUpdate firstListUpdate = new ListUpdateTestImpl();
    private ListUpdate secondListUpdate = new ListUpdateTestImpl();
    private ListUpdate thirdListUpdate = new ListUpdateTestImpl();
    private ListUpdate newListUpdate = new ListUpdateTestImpl();

    ListNavigatorTest() {
        addAllUpdates();
    }

    @Test
    void lastUpdateIsCurrentUpdate() {
        assertTrue(currentUpdateIsLastUpdate());
    }

    @Test
    void throwExceptionWhenNoUpdateAvailable() {
        ListNavigator emptyUpdateNavigator = new ListNavigator();
        assertThrows(NoUpdatesAvailableException.class, () -> emptyUpdateNavigator.getCurrentUpdate());
    }

    @Test
    void whenOnLastUpdateThenThereShouldNotBeANextUpdate() {
        assertFalse(listNavigator.hasNextUpdate());
    }

    @Test
    void whenOnLastUpdateThenNextUpdateShouldBeLastUpdate() {
        listNavigator.goToNextUpdate();
        assertTrue(currentUpdateIsLastUpdate());
    }

    @Test
    void whenOnLastUpdateGoingToPreviousThenCurrentUpdateShouldBeSecondUpdate() {
        listNavigator.goToPreviousUpdate();
        assertTrue(isOnSecondUpdate());
    }

    @Test
    void whenOnFirstUpdateGoingToNextThenCurrentUpdateShouldBeSecondUpdate() {
        goToFirstUpdate();
        listNavigator.goToNextUpdate();
        assertTrue(isOnSecondUpdate());
    }

    @Test
    void whenOnFirstUpdateThenPreviousUpdateShouldBeFirstUpdate() {
        goToFirstUpdate();
        listNavigator.goToPreviousUpdate();
        assertTrue(currentUpdateIsFirstUpdate());
    }

    @Test
    void whenOnFirstUpdateThenThereShouldNotBeAPreviousUpdate() {
        goToFirstUpdate();
        assertFalse(listNavigator.hasPreviousUpdate());
    }

    @Test
    void whenOnFirstUpdateAddingNewUpdateThenCurrentUpdateShouldBeNewUpdate() {
        goToFirstUpdate();
        listNavigator.addTableUpdate(newListUpdate);
        assertTrue(currentUpdateIsNewUpdate());
    }

    @Test
    void whenOnFirstUpdateAddingNewUpdateThenPreviousUpdateShouldBeFirstUpdate() {
        goToFirstUpdate();
        listNavigator.addTableUpdate(newListUpdate);
        assertTrue(currentUpdateIsNewUpdate());
    }

    @Test
    void whenOnFirstUpdateAddingNewUpdateThenNextUpdateShouldBeNewUpdate() {
        goToFirstUpdate();
        listNavigator.addTableUpdate(newListUpdate);
        listNavigator.goToNextUpdate();
        assertTrue(currentUpdateIsNewUpdate());
    }

    @Test
    void whenOnFirstUpdateAddingNewUpdateThenThereShouldNotBeANextUpdate() {
        goToFirstUpdate();
        listNavigator.addTableUpdate(newListUpdate);
        assertFalse(listNavigator.hasNextUpdate());
    }

    private void goToFirstUpdate() {
        listNavigator.goToPreviousUpdate();
        listNavigator.goToPreviousUpdate();
    }

    private boolean currentUpdateIsLastUpdate() {
        ListUpdate currentUpdate = listNavigator.getCurrentUpdate();
        return currentUpdate.equals(thirdListUpdate);
    }

    private boolean currentUpdateIsNewUpdate() {
        ListUpdate currentUpdate = listNavigator.getCurrentUpdate();
        return currentUpdate.equals(newListUpdate);
    }

    private boolean currentUpdateIsFirstUpdate() {
        ListUpdate currentUpdate = listNavigator.getCurrentUpdate();
        return currentUpdate.equals(firstListUpdate);
    }

    private boolean isOnSecondUpdate() {
        return listNavigator.getCurrentUpdate().equals(secondListUpdate);
    }

    private void addAllUpdates() {
        listNavigator.addTableUpdate(firstListUpdate);
        listNavigator.addTableUpdate(secondListUpdate);
        listNavigator.addTableUpdate(thirdListUpdate);
    }

    class ListUpdateTestImpl implements ListUpdate{
        @Override
        public int getIndex() {
            return 0;
        }
    }
}
