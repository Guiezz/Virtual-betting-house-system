import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Admin admin = new Admin(
                "John", "Doe",
                "joedoe@admin.com", "password123"
        );

        Gambler gambler = new Gambler(
                "Jane", "Doe",
                "janedoe@gambler.com", "password456",
                1000.0
        );

        List<User> users = new ArrayList<>();
        List<String> passwords = new ArrayList<>();

        users.add(admin);
        passwords.add("password123");
        users.add(gambler);
        passwords.add("password456");

        try {
            for (User user : users) {
                user.authenticate(passwords.get(users.indexOf(user)));
                System.out.println();
            }


            gambler.deposit(200.0);

            System.out.println();

            gambler.viewStatement();

            System.out.println();

            Event event = admin.createEvent(new Event(
                    "Futebol", "Campeonato Brasileiro",
                    "Vitoria do time A"
            ));

            System.out.println("[POST] Evento criado com sucesso: " + event.getDescription());

            System.out.println();

            gambler.bet(event, "Vitoria do time A", 50.0, 2.0);

            System.out.println();

            admin.viewAllBetsOfEvent(event.getId());

            System.out.println();

            admin.finishEvent(event.getId(), "Vitoria do time A");
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}