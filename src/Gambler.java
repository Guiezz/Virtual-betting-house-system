import types.TransactionType;
import utils.CurrencyFormatter;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Gambler extends User {
    private double balance;
    private LocalDateTime registrationDate;
    private Set<Bet> bets;
    private Set<Transaction> transactions;

    public Gambler(String firstName, String lastName, String email, String password, double initialBalance) {
        super(firstName, lastName, email, password);

        if (initialBalance < 0) {
            throw new IllegalArgumentException("O saldo inicial não pode ser negativo");
        }

        this.balance = initialBalance;
        this.registrationDate = LocalDateTime.now();
        this.bets = new HashSet<>();
        this.transactions = new HashSet<>();
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("O saldo não pode ser negativo");
        }

        this.balance = balance;
    }

    public LocalDateTime getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        if (registrationDate == null) {
            throw new IllegalArgumentException("A data de registro não pode ser nula");
        }

        this.registrationDate = registrationDate;
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

    public Set<Transaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        if (transactions == null) {
            throw new IllegalArgumentException("O conjunto de transações não pode ser nulo");
        }

        this.transactions = transactions;
    }

    public double viewCurrentBalance() {
        return this.balance;
    }

    @Override
    public void authenticate(String password) {
        super.authenticate(password);
        System.out.println("Autenticação bem-sucedida para o apostador: " + this.getFirstName() + " " + this.getLastName());
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser positivo");
        }

        this.balance += amount;

        Transaction transaction = new Transaction(this.getId(), TransactionType.deposit(), amount);
        this.transactions.add(transaction);

        System.out.println("[POST] Depósito realizado com sucesso: " + CurrencyFormatter.format(amount));
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser positivo");
        }

        if (amount > this.balance) {
            throw new IllegalArgumentException("Saldo insuficiente para o saque");
        }

        this.balance -= amount;

        Transaction transaction = new Transaction(this.getId(), TransactionType.withdrawal(), amount);
        this.transactions.add(transaction);

        System.out.println("[POST] Saque realizado com sucesso: " + CurrencyFormatter.format(amount));
    }

    public Bet bet(Event event, String expectedResult, double value, double odd) {
        if (event.getId() == null) {
            throw new IllegalArgumentException("O ID do evento não pode ser nulo");
        }

        if (expectedResult == null || expectedResult.isEmpty()) {
            throw new IllegalArgumentException("O resultado esperado não pode ser nulo ou vazio");
        }

        if (value <= 0) {
            throw new IllegalArgumentException("O valor da aposta deve ser positivo");
        }

        if (odd <= 0) {
            throw new IllegalArgumentException("A odd deve ser positiva");
        }

        if (value > this.balance) {
            throw new IllegalArgumentException("Saldo insuficiente para a aposta");
        }

        Bet bet = new Bet(this.getId(), event.getId(), expectedResult, value, odd);

        this.bets.add(bet);

        this.balance -= value;
        Transaction transaction = new Transaction(this.getId(), TransactionType.bet(), value);
        this.transactions.add(transaction);

        System.out.println("[POST] Aposta realizada com sucesso: " + bet.getExpectedResult() +
                " - Valor: " + CurrencyFormatter.format(bet.getValue()) +
                " - Odd: " + bet.getOdd() + "x" +
                " - Possível retorno: " + CurrencyFormatter.format(bet.calculatePossibleReturn()));

        event.addBet(bet);

        return bet;
    }

    public List<Bet> viewBetHistory() {
        return bets.stream()
                .sorted(Comparator.comparing(Bet::getBetAt).reversed())
                .toList();
    }

    public void viewStatement() {
        System.out.println("[GET] Extrato de transações:");
        System.out.printf("%-36s %-10s %-10s %-20s%n", "ID", "Tipo", "Valor", "Data");
        System.out.println("------------------------------------------------------------");

        for (Transaction transaction : transactions) {
            System.out.printf("%-36s %-10s %-10s %-20s%n",
                    transaction.getId(),
                    transaction.getType(),
                    CurrencyFormatter.format(transaction.getValue()),
                    transaction.getCarriedOutIn().format(
                            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
                    ));
        }

        System.out.println("------------------------------------------------------------");
        System.out.printf("Saldo atual: %s%n", CurrencyFormatter.format(this.balance));
    }
}
