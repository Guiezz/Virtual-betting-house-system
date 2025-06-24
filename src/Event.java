import types.EventStatus;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Event {
    private UUID id;
    private String sport;
    private String description;
    private String expectedResult;
    private EventStatus status;
    private String result;
    private LocalDateTime startedOn;
    private LocalDateTime finishedOn;
    private Set<Bet> bets;

    public Event(String sport, String description, String expectedResult) {
        this.id = UUID.randomUUID();
        this.sport = sport;
        this.description = description;
        this.expectedResult = expectedResult;
        this.status = EventStatus.pending();
        this.startedOn = LocalDateTime.now();
        this.finishedOn = null;
        this.bets = new HashSet<>();
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

    public String getSport() {
        return this.sport;
    }

    public void setSport(String sport) {
        if (sport == null || sport.isEmpty()) {
            throw new IllegalArgumentException("O esporte não pode ser nulo ou vazio");
        }

        this.sport = sport;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("A descrição não pode ser nula ou vazia");
        }

        this.description = description;
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

    public EventStatus getStatus() {
        return this.status;
    }

    public void setStatus(EventStatus status) {
        if (status == null || status.toString().isEmpty()) {
            throw new IllegalArgumentException("O status não pode ser nulo ou vazio");
        }

        this.status = status;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        if (result == null || result.isEmpty()) {
            throw new IllegalArgumentException("O resultado não pode ser nulo ou vazio");
        }

        this.result = result;
    }

    public LocalDateTime getStartedOn() {
        return this.startedOn;
    }

    public void setStartedOn(LocalDateTime startedOn) {
        if (startedOn == null) {
            throw new IllegalArgumentException("A data de início não pode ser nula");
        }

        this.startedOn = startedOn;
    }

    public LocalDateTime getFinishedOn() {
        return this.finishedOn;
    }

    public void setFinishedOn(LocalDateTime finishedOn) {
        if (finishedOn == null) {
            throw new IllegalArgumentException("A data de término não pode ser nula");
        }

        this.finishedOn = finishedOn;
    }

    public Set<Bet> getBets() {
        return this.bets;
    }

    public void setBets(Set<Bet> bets) {
        if (bets == null) {
            throw new IllegalArgumentException("O conjunto de apostas não pode ser nulo");
        }

        this.bets = bets;
    }

    public void addBet(Bet bet) {
        if (bet == null) {
            throw new IllegalArgumentException("A aposta não pode ser nula");
        }

        if (this.status.isCompleted()) {
            throw new IllegalStateException("Não é possível adicionar apostas a um evento já finalizado");
        }

        this.bets.add(bet);
    }

    public void updateResult(String result) {
        if (this.status.isCompleted()) {
            throw new IllegalStateException("Não é possível atualizar o resultado de um evento já finalizado");
        }

        this.result = result;
        this.finish();
    }

    public void finish() {
        if (this.status.isCompleted()) {
            throw new IllegalStateException("O evento já está finalizado");
        }

        this.status = EventStatus.completed();
        this.finishedOn = LocalDateTime.now();
    }
}
