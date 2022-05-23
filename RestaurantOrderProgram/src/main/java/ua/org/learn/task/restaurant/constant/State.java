package ua.org.learn.task.restaurant.constant;

public enum State {
    COMPLETE,
    IN_PROGRESS,
    NEW,
    READY,
    UNDEFINED;

    public State getStateByName(String name) {
        try {
            return State.valueOf(name);
        } catch (Exception e) {
            return UNDEFINED;
        }
    }
}
