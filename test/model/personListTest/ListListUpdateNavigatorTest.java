package model.personListTest;

import model.personList.ListUpdate;
import model.personList.ListUpdateNavigator;
import model.personList.NoUpdatesAvailableException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListListUpdateNavigatorTest {
    private ListUpdateNavigator<Integer> listUpdateNavigator = new ListUpdateNavigator();
    private final Integer FIRST_UPDATE = 1;
    private final Integer SECOND_UPDATE = 1;
    private final Integer THIRD_UPDATE = 1;
    private final Integer NEW_UPDATE = 1;
    private ListUpdate<Integer> firstListUpdate;

    private ListUpdate<Integer> secondListUpdate;
    private ListUpdate<Integer> thirdListUpdate;
    private ListUpdate<Integer> newListUpdate;

    ListListUpdateNavigatorTest() {
        firstListUpdate = new ListUpdateTestImpl(FIRST_UPDATE);
        secondListUpdate = new ListUpdateTestImpl(SECOND_UPDATE);
        thirdListUpdate = new ListUpdateTestImpl(THIRD_UPDATE);
        newListUpdate = new ListUpdateTestImpl(NEW_UPDATE);
        addAllUpdates();
    }

    @Test
    void ThirdUpdateIsCurrentUpdate() {
        assertTrue(currentUpdateIsThirdUpdate());
    }

    @Test
    void throwExceptionWhenNoUpdateAvailable() {
        ListUpdateNavigator emptyListUpdateNavigator = new ListUpdateNavigator();
        assertThrows(NoUpdatesAvailableException.class, () -> emptyListUpdateNavigator.getCurrentUpdate());
    }

    @Test
    void whenOnThirdUpdateThenThereShouldNotBeANextUpdate() {
        assertFalse(listUpdateNavigator.hasNextUpdate());
    }

    @Test
    void whenOnThirdUpdateThenNextUpdateShouldBeThirdUpdate() {
        listUpdateNavigator.goToNextUpdate();
        assertTrue(currentUpdateIsThirdUpdate());
    }

    @Test
    void whenOnThirdUpdateGoingToPreviousThenCurrentUpdateShouldBeSecondUpdate() {
        listUpdateNavigator.goToPreviousUpdate();
        assertTrue(isOnSecondUpdate());
    }

    @Test
    void whenOnFirstUpdateGoingToNextThenCurrentUpdateShouldBeSecondUpdate() {
        goToFirstUpdate();
        listUpdateNavigator.goToNextUpdate();
        assertTrue(isOnSecondUpdate());
    }

    @Test
    void whenOnFirstUpdateThenPreviousUpdateShouldBeFirstUpdate() {
        goToFirstUpdate();
        listUpdateNavigator.goToPreviousUpdate();
        assertTrue(currentUpdateIsFirstUpdate());
    }

    @Test
    void whenOnFirstUpdateThenThereShouldNotBeAPreviousUpdate() {
        goToFirstUpdate();
        assertFalse(listUpdateNavigator.hasPreviousUpdate());
    }

    @Test
    void whenOnFirstUpdateAddingNewUpdateThenCurrentUpdateShouldBeNewUpdate() {
        goToFirstUpdate();
        listUpdateNavigator.addTableUpdate(newListUpdate);
        assertTrue(currentUpdateIsNewUpdate());
    }

    @Test
    void whenOnFirstUpdateAddingNewUpdateThenPreviousUpdateShouldBeFirstUpdate() {
        goToFirstUpdate();
        listUpdateNavigator.addTableUpdate(newListUpdate);
        assertTrue(currentUpdateIsNewUpdate());
    }

    @Test
    void whenOnFirstUpdateAddingNewUpdateThenNextUpdateShouldBeNewUpdate() {
        goToFirstUpdate();
        listUpdateNavigator.addTableUpdate(newListUpdate);
        listUpdateNavigator.goToNextUpdate();
        assertTrue(currentUpdateIsNewUpdate());
    }

    @Test
    void whenOnFirstUpdateAddingNewUpdateThenThereShouldNotBeANextUpdate() {
        goToFirstUpdate();
        listUpdateNavigator.addTableUpdate(newListUpdate);
        assertFalse(listUpdateNavigator.hasNextUpdate());
    }

    @Test
    void whenRemovingUpdatesAndAddingNewUpdatesCurrentUpdateShouldBeThirdUpdate() {
        listUpdateNavigator.removeAllUpdates();
        addAllUpdates();
        assertTrue(this::currentUpdateIsThirdUpdate);
    }

    @Test
    void whenRemovingAllUpdatesGetCurrentUpdateShouldThrowException() {
        listUpdateNavigator.removeAllUpdates();
        assertThrows(NoUpdatesAvailableException.class, () -> listUpdateNavigator.getCurrentUpdate());
    }

    private void goToFirstUpdate() {
        listUpdateNavigator.goToPreviousUpdate();
        listUpdateNavigator.goToPreviousUpdate();
    }

    private boolean currentUpdateIsThirdUpdate() {
        Integer currentUpdate = listUpdateNavigator.getCurrentUpdate().get(0);
        return currentUpdate.equals(THIRD_UPDATE);
    }

    private boolean currentUpdateIsNewUpdate() {
        Integer currentUpdate = listUpdateNavigator.getCurrentUpdate().get(0);
        return currentUpdate.equals(NEW_UPDATE);
    }

    private boolean currentUpdateIsFirstUpdate() {
        Integer currentUpdate = listUpdateNavigator.getCurrentUpdate().get(0);
        return currentUpdate.equals(FIRST_UPDATE);
    }

    private boolean isOnSecondUpdate() {
        Integer currentUpdate = listUpdateNavigator.getCurrentUpdate().get(0);
        return currentUpdate.equals(SECOND_UPDATE);
    }

    private void addAllUpdates() {
        listUpdateNavigator.addTableUpdate(firstListUpdate);
        listUpdateNavigator.addTableUpdate(secondListUpdate);
        listUpdateNavigator.addTableUpdate(thirdListUpdate);
    }

    class ListUpdateTestImpl implements ListUpdate<Integer> {
        private List<Integer> integerList;

        public ListUpdateTestImpl(Integer integer) {
            integerList = new ArrayList<>();
            integerList.add(integer);
        }

        @Override
        public List<Integer> getList() {
            return integerList;
        }
    }
}
