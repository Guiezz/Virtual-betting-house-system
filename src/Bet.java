import types.BetStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class Bet {
    private UUID id;
    private UUID userId;
    private UUID eventId;
    private String expectedResult;
    private double value;
    private double odd;
    private double possibleReturn;
    private BetStatus status;
    private LocalDateTime betAt;

    public Bet(UUID userId, UUID eventId, String expectedResult, double value, double odd) {
        if (userId == null || eventId == null || expectedResult == null || expectedResult.isEmpty()) {
            throw new IllegalArgumentException("Parâmetros inválidos para a aposta");
        }

        if (value <= 0) {
            throw new IllegalArgumentException("O valor da aposta deve ser positivo");
        }

        if (odd <= 0) {
            throw new IllegalArgumentException("A odd deve ser positiva");
        }

        this.id = UUID.randomUUID();
        this.userId = userId;
        this.eventId = eventId;
        this.expectedResult = expectedResult;
        this.value = value;
        this.odd = odd;
        this.possibleReturn = value * odd;
        this.status = BetStatus.pending(); // Status inicial da aposta
        this.betAt = LocalDateTime.now();
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo");
        }

        this.id = id;
    }

    public UUID getUserId() {
        return this.userId;
    }

    public void setUserId(UUID userId) {
        if (userId == null) {
            throw new IllegalArgumentException("O ID do usuário não pode ser nulo");
        }

        this.userId = userId;
    }

    public UUID getEventId() {
        return this.eventId;
    }

    public void setEventId(UUID eventId) {
        if (eventId == null) {
            throw new IllegalArgumentException("O ID do evento não pode ser nulo");
        }

        this.eventId = eventId;
    }

    public String getExpectedResult() {
        return this.expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        if (expectedResult == null || expectedResult.isEmpty()) {
            throw new IllegalArgumentException("O resultado esperado não pode ser nulo ou vazio");
        }

        this.expectedResult = expectedResult;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("O valor da aposta deve ser positivo");
        }

        this.value = value;
    }

    public double getOdd() {
        return this.odd;
    }

    public void setOdd(double odd) {
        if (odd <= 0) {
            throw new IllegalArgumentException("A odd deve ser positiva");
        }

        this.odd = odd;
    }

    public double getPossibleReturn() {
        return this.possibleReturn;
    }

    public void setPossibleReturn(double possibleReturn) {
        if (possibleReturn < 0) {
            throw new IllegalArgumentException("O retorno possível não pode ser negativo");
        }

        this.possibleReturn = possibleReturn;
    }

    public BetStatus getStatus() {
        return this.status;
    }

    public void setStatus(BetStatus status) {
        if (status == null || status.toString().isEmpty()) {
            throw new IllegalArgumentException("O status não pode ser nulo ou vazio");
        }

        this.status = status;
    }

    public LocalDateTime getBetAt() {
        return this.betAt;
    }

    public void setBetAt(LocalDateTime betAt) {
        if (betAt == null) {
            throw new IllegalArgumentException("A data da aposta não pode ser nula");
        }

        this.betAt = betAt;
    }

    public double calculatePossibleReturn() {
        return this.value * this.odd;
    }

    public void verifyResult(String actualResult) {
        if (actualResult == null || actualResult.isEmpty()) {
            throw new IllegalArgumentException("O resultado real não pode ser nulo ou vazio");
        }

        if (this.expectedResult.equals(actualResult)) {
            this.status = BetStatus.won();
        } else {
            this.status = BetStatus.lost();
        }
    }
}
