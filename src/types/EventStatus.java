package types;

public enum EventStatus {
    PENDING("PENDING"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED"),
    CANCELLED("CANCELLED");

    private final String status;

    EventStatus(String status) {
        this.status = status;
    }

    public static EventStatus pending() {
        return EventStatus.PENDING;
    }

    public boolean isPending() {
        return this == PENDING;
    }

    public static EventStatus inProgress() {
        return EventStatus.IN_PROGRESS;
    }

    public boolean isInProgress() {
        return this == IN_PROGRESS;
    }

    public static EventStatus completed() {
        return EventStatus.COMPLETED;
    }

    public boolean isCompleted() {
        return this == COMPLETED;
    }

    public static EventStatus cancelled() {
        return EventStatus.CANCELLED;
    }

    public boolean isCancelled() {
        return this == CANCELLED;
    }

    @Override
    public String toString() {
        return status;
    }
}
