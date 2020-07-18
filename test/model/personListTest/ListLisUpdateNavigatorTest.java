package model.personListTest;

import model.personList.LisUpdateNavigator;
import model.personList.ListUpdate;
import model.personList.NoUpdatesAvailableException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListLisUpdateNavigatorTest {
    private LisUpdateNavigator<Integer> lisUpdateNavigator = new LisUpdateNavigator();
    private final Integer FIRST_UPDATE = 1;
    private final Integer SECOND_UPDATE = 1;
    private final Integer THIRD_UPDATE = 1;
    private final Integer NEW_UPDATE = 1;
    private ListUpdate<Integer> firstListUpdate;

    private ListUpdate<Integer> secondListUpdate;
    private ListUpdate<Integer> thirdListUpdate;
    private ListUpdate<Integer> newListUpdate;

    ListLisUpdateNavigatorTest() {
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
        LisUpdateNavigator emptyLisUpdateNavigator = new LisUpdateNavigator();
        assertThrows(NoUpdatesAvailableException.class, () -> emptyLisUpdateNavigator.getCurrentUpdate());
    }

    @Test
    void whenOnThirdUpdateThenThereShouldNotBeANextUpdate() {
        assertFalse(lisUpdateNavigator.hasNextUpdate());
    }

    @Test
    void whenOnThirdUpdateThenNextUpdateShouldBeThirdUpdate() {
        lisUpdateNavigator.goToNextUpdate();
        assertTrue(currentUpdateIsThirdUpdate());
    }

    @Test
    void whenOnThirdUpdateGoingToPreviousThenCurrentUpdateShouldBeSecondUpdate() {
        lisUpdateNavigator.goToPreviousUpdate();
        assertTrue(isOnSecondUpdate());
    }

    @Test
    void whenOnFirstUpdateGoingToNextThenCurrentUpdateShouldBeSecondUpdate() {
        goToFirstUpdate();
        lisUpdateNavigator.goToNextUpdate();
        assertTrue(isOnSecondUpdate());
    }

    @Test
    void whenOnFirstUpdateThenPreviousUpdateShouldBeFirstUpdate() {
        goToFirstUpdate();
        lisUpdateNavigator.goToPreviousUpdate();
        assertTrue(currentUpdateIsFirstUpdate());
    }

    @Test
    void whenOnFirstUpdateThenThereShouldNotBeAPreviousUpdate() {
        goToFirstUpdate();
        assertFalse(lisUpdateNavigator.hasPreviousUpdate());
    }

    @Test
    void whenOnFirstUpdateAddingNewUpdateThenCurrentUpdateShouldBeNewUpdate() {
        goToFirstUpdate();
        lisUpdateNavigator.addTableUpdate(newListUpdate);
        assertTrue(currentUpdateIsNewUpdate());
    }

    @Test
    void whenOnFirstUpdateAddingNewUpdateThenPreviousUpdateShouldBeFirstUpdate() {
        goToFirstUpdate();
        lisUpdateNavigator.addTableUpdate(newListUpdate);
        assertTrue(currentUpdateIsNewUpdate());
    }

    @Test
    void whenOnFirstUpdateAddingNewUpdateThenNextUpdateShouldBeNewUpdate() {
        goToFirstUpdate();
        lisUpdateNavigator.addTableUpdate(newListUpdate);
        lisUpdateNavigator.goToNextUpdate();
        assertTrue(currentUpdateIsNewUpdate());
    }

    @Test
    void whenOnFirstUpdateAddingNewUpdateThenThereShouldNotBeANextUpdate() {
        goToFirstUpdate();
        lisUpdateNavigator.addTableUpdate(newListUpdate);
        assertFalse(lisUpdateNavigator.hasNextUpdate());
    }

    @Test
    void whenRemovingUpdatesAndAddingNewUpdatesCurrentUpdateShouldBeThirdUpdate() {
        lisUpdateNavigator.removeAllUpdates();
        addAllUpdates();
        assertTrue(this::currentUpdateIsThirdUpdate);
    }

    @Test
    void whenRemovingAllUpdatesGetCurrentUpdateShouldThrowException() {
        lisUpdateNavigator.removeAllUpdates();
        assertThrows(NoUpdatesAvailableException.class, () -> lisUpdateNavigator.getCurrentUpdate());
    }

    private void goToFirstUpdate() {
        lisUpdateNavigator.goToPreviousUpdate();
        lisUpdateNavigator.goToPreviousUpdate();
    }

    private boolean currentUpdateIsThirdUpdate() {
        Integer currentUpdate = lisUpdateNavigator.getCurrentUpdate().get(0);
        return currentUpdate.equals(THIRD_UPDATE);
    }

    private boolean currentUpdateIsNewUpdate() {
        Integer currentUpdate = lisUpdateNavigator.getCurrentUpdate().get(0);
        return currentUpdate.equals(NEW_UPDATE);
    }

    private boolean currentUpdateIsFirstUpdate() {
        Integer currentUpdate = lisUpdateNavigator.getCurrentUpdate().get(0);
        return currentUpdate.equals(FIRST_UPDATE);
    }

    private boolean isOnSecondUpdate() {
        Integer currentUpdate = lisUpdateNavigator.getCurrentUpdate().get(0);
        return currentUpdate.equals(SECOND_UPDATE);
    }

    private void addAllUpdates() {
        lisUpdateNavigator.addTableUpdate(firstListUpdate);
        lisUpdateNavigator.addTableUpdate(secondListUpdate);
        lisUpdateNavigator.addTableUpdate(thirdListUpdate);
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
