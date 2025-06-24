package types;

public enum TransactionType {
    DEPOSIT("DEPOSIT"),
    WITHDRAWAL("WITHDRAWAL"),
    BET("BET");

    private final String type;

    TransactionType(String type) {
        this.type = type;
    }

    public static TransactionType deposit() {
        return TransactionType.DEPOSIT;
    }

    public boolean isDeposit() {
        return this == DEPOSIT;
    }

    public static TransactionType withdrawal() {
        return TransactionType.WITHDRAWAL;
    }

    public boolean isWithdrawal() {
        return this == WITHDRAWAL;
    }

    public static TransactionType bet() {
        return TransactionType.BET;
    }

    public boolean isBet() {
        return this == BET;
    }

    @Override
    public String toString() {
        return type;
    }
}
