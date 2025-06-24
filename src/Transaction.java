import types.TransactionType;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private UUID id;
    private UUID userId;
    private TransactionType type;
    private double value;
    private LocalDateTime carriedOutIn;

    public Transaction(UUID userId, TransactionType type, double value) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.type = type;
        this.value = value;
        this.carriedOutIn = LocalDateTime.now();
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return this.userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public TransactionType getType() {
        return this.type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDateTime getCarriedOutIn() {
        return this.carriedOutIn;
    }

    public void setCarriedOutIn(LocalDateTime carriedOutIn) {
        if (carriedOutIn == null) {
            throw new IllegalArgumentException("A data de realização não pode ser nula");
        }

        this.carriedOutIn = carriedOutIn;
    }
}
