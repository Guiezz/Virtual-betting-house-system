package types;

public enum BetStatus {
    PENDING("PENDING"),
    WON("WON"),
    LOST("LOST");

    private final String status;

    BetStatus(String status) {
        this.status = status;
    }

    public static BetStatus pending() {
        return BetStatus.PENDING;
    }

    public boolean isPending() {
        return this == PENDING;
    }

    public static BetStatus won() {
        return BetStatus.WON;
    }

    public boolean isWon() {
        return this == WON;
    }

    public static BetStatus lost() {
        return BetStatus.LOST;
    }

    public boolean isLost() {
        return this == LOST;
    }

    @Override
    public String toString() {
        return status;
    }
}
