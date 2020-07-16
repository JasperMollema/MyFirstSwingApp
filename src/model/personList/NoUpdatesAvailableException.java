package model.personList;

public class NoUpdatesAvailableException extends RuntimeException {
    @Override
    public String getMessage() {
        return "No updates available";
    }
}
