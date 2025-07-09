import utils.CurrencyFormatter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Admin extends User {
    private Set<Event> events;

    public Admin(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
        this.events = new HashSet<>();
    }

    public Set<Event> getEvents() {
        return this.events;
    }

    public void setEvents(Set<Event> events) {
        if (events == null) {
            throw new IllegalArgumentException("O conjunto de eventos não pode ser nulo");
        }

        this.events = events;
    }

    @Override
    public void authenticate(String password) {
        super.authenticate(password);
        System.out.println("[POST] Autenticação bem-sucedida para o administrador: " + this.getFirstName() + " " + this.getLastName());
    }

    public Event createEvent(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("O evento não pode ser nulo");
        }

        if (events.contains(event)) {
            throw new IllegalArgumentException("Evento já existe");
        }

        events.add(event);
        return event;
    }

    public Event editEvent(UUID eventId, String sport, String description, String expectedResult) {
        if (eventId == null) {
            throw new IllegalArgumentException("O ID do evento não pode ser nulo");
        }

        Event event = this.events.stream()
                .filter(e -> e.getId().equals(eventId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Evento não encontrado"));

        if (sport != null && !sport.isEmpty()) {
            event.setSport(sport);
        }

        if (description != null && !description.isEmpty()) {
            event.setDescription(description);
        }

        if (expectedResult != null && !expectedResult.isEmpty()) {
            event.setExpectedResult(expectedResult);
        }

        return event;

    }

    public void finishEvent(UUID eventId, String result) {
        if (eventId == null) {
            throw new IllegalArgumentException("O ID do evento não pode ser nulo");
        }

        Event event = this.events.stream()
                .filter(e -> e.getId().equals(eventId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Evento não encontrado"));

        if (result == null || result.isEmpty()) {
            throw new IllegalArgumentException("O resultado não pode ser nulo ou vazio");
        }

        event.updateResult(result);

        System.out.println("[POST] Evento finalizado com sucesso: " + event.getDescription() + " - Resultado: " + result);

        event.getBets().forEach(bet -> {
            bet.verifyResult(result);
        });

        System.out.println("Todas as apostas do evento foram atualizadas com o resultado: " + result);
        System.out.println("Status das apostas atualizadas:");
        event.getBets().forEach(bet -> {
            System.out.println("Aposta ID: " + bet.getId() + ", Status: " + bet.getStatus() + ", Valor: " + CurrencyFormatter.format(bet.getValue()));
        });
    }

    public List<Bet> viewAllBetsOfEvent(UUID eventId) {
        System.out.println("[GET] Visualizando todas as apostas do evento");

        if (eventId == null) {
            throw new IllegalArgumentException("O ID do evento não pode ser nulo");
        }

        Event event = this.events.stream()
                .filter(e -> e.getId().equals(eventId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Evento não encontrado"));

        List<Bet> bets = event.getBets().stream().toList();

        if (bets.isEmpty()) {
            System.out.println("Nenhuma aposta encontrada para o evento: " + event.getDescription());
        } else {
            System.out.println("Apostas encontradas para o evento: " + event.getDescription());
            bets.forEach(bet -> System.out.println("Aposta ID: " + bet.getId() + ", Valor: " + CurrencyFormatter.format(bet.getValue())));
        }

        return bets;
    }
}
